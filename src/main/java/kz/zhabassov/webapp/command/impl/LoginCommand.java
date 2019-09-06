package kz.zhabassov.webapp.command.impl;

import kz.zhabassov.webapp.command.ActionCommand;
import kz.zhabassov.webapp.entity.User;
import kz.zhabassov.webapp.service.UserService;
import kz.zhabassov.webapp.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PAGE_MAIN_STUDENT = "path.page.main.student";
    private static final String PAGE_MAIN_TEACHER = "path.page.main.teacher";
    private static final String PAGE_LOGIN = "path.page.login";
    private static final String PAGE_404 = "/404.jsp";

    private UserService service = new UserServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        User user = (User) service.login(login,password);
        if(user!=null){
            request.getSession().setAttribute("user", user);
            if ("student".equals(user.getRole())){
                page = PAGE_MAIN_STUDENT;
            }else if("teacher".equals(user.getRole())) {
                page = PAGE_MAIN_TEACHER;
            }else {
                page = PAGE_404;
            }
            request.setAttribute("registered", "Congratulations you are part of team!");

        }else {
            request.setAttribute("errorLoginPassMessage", "incorrect login or password");
            page=PAGE_LOGIN;
    }
        return page;
}
}
