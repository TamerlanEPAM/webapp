package kz.zhabassov.webapp.controller;

import kz.zhabassov.webapp.command.ActionCommand;
import kz.zhabassov.webapp.command.ActionFactory;
import kz.zhabassov.webapp.command.ConfigurationManager;
import kz.zhabassov.webapp.pool.ConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "controller",urlPatterns = "/controller")
public class Controller extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            processPostRequest(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            processGetRequest(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
      // ConnectionPool.getInstance();
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }

    private void processGetRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pagePath;
        String page;
        if(request.getParameterMap().containsKey("pagePath")){
            pagePath = request.getParameter("pagePath");
        }else {
            ActionCommand command = ActionFactory.defineCommand(request.getParameter("command"));
            pagePath = command.execute(request);
        }
        if(pagePath!=null){
            page = ConfigurationManager.getString(pagePath);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request,response);
        }else {
            page="/index.jsp";
            request.getSession().setAttribute("nullPage","message nullpage");
            response.sendRedirect(request.getContextPath()+page);
        }
    }

    private void processPostRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pagePath;
        String page;
        ActionCommand command = ActionFactory.defineCommand(request.getParameter("command"));
        pagePath = command.execute(request);
        if(pagePath != null){
            response.sendRedirect("controller?pagePath=" + pagePath);
        }else {
            page = "/index.jsp";
            request.getSession().setAttribute("nullPage","message nullpage");
            response.sendRedirect(request.getContextPath()+page);
        }
    }
}
