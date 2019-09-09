package kz.zhabassov.webapp.pool;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    //DB anyway will work with DB, so non-lazy initialization will be the best way
    private static final ConnectionPool instance = new ConnectionPool();
    private static BlockingQueue<PooledConnection> freeConnections;
    private static BlockingQueue<PooledConnection> givenAwayConnections;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private ConnectionPool() {
        driverName = ConnectorDB.getProperty(ConnectorDB.DRIVER_NAME);
        url = ConnectorDB.getProperty(ConnectorDB.URL);
        user = ConnectorDB.getProperty(ConnectorDB.USER);
        password = ConnectorDB.getProperty(ConnectorDB.PASSWORD);
        try {
            poolSize = Integer.parseInt(ConnectorDB.getProperty(ConnectorDB.POOL_SIZE));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
            freeConnections = new ArrayBlockingQueue<>(poolSize);
            givenAwayConnections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize ; i++) {
                try {
                    Connection conn = DriverManager.getConnection(url,user,password);
                    PooledConnection connection = new PooledConnection(conn);
                    freeConnections.offer(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


    }


    public static ConnectionPool getInstance() {
        return instance;
    }

    public PooledConnection getConnection(){
        PooledConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void releaseConnection(PooledConnection connection){
        givenAwayConnections.remove(connection);
        freeConnections.offer(connection);
    }

    public void destroyPool(){
        for (int i = 0; i < poolSize ; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        deregisterDrivers();
    }
    private void deregisterDrivers(){
        while (DriverManager.getDrivers().hasMoreElements()){
            try {
                DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
            } catch (SQLException e) {
                // log
            }
        }
    }
}
