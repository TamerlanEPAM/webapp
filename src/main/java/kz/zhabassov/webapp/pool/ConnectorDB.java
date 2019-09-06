package kz.zhabassov.webapp.pool;

import java.util.ResourceBundle;

public class ConnectorDB {
    public static final String DRIVER_NAME = "driver";
    public static final String URL = "url";
    public static final String USER = "user";
    public static final  String PASSWORD = "password";
    public static final String POOL_SIZE = "poolSize";
    public static final String RESOURCE_NAME = "db";
    private static final ResourceBundle resource = ResourceBundle.getBundle(RESOURCE_NAME);

    private ConnectorDB() {
    }

    public static String getProperty(String key){
        return resource.getString(key);
    }
}
