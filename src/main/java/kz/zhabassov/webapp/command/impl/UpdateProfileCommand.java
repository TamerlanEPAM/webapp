package kz.zhabassov.webapp.command.impl;

import kz.zhabassov.webapp.entity.User;
import kz.zhabassov.webapp.service.UserService;
import kz.zhabassov.webapp.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class UpdateProfileCommand implements kz.zhabassov.webapp.command.ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_OLD_PASSWORD = "oldPassword";
    private static final String PARAM_NAME_NEW_PASSWORD = "newPassword";
    private static final String PARAM_NAME_NEW_PASSWORD_REPEAT = "repeatNewPassword";
    private static final String PAGE_MAIN_STUDENT = "path.page.main.student";
    private static final String PAGE_MAIN_TEACHER = "path.page.main.teacher";
    private static final String PAGE_LOGIN = "path.page.login";
    private static final String PAGE_404 = "/404.jsp";
    private UserService service = new UserServiceImpl();
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String oldPassword = request.getParameter(PARAM_NAME_OLD_PASSWORD);
        String newPassword = request.getParameter(PARAM_NAME_NEW_PASSWORD);
        String repeatNewPassword =  request.getParameter(PARAM_NAME_NEW_PASSWORD_REPEAT);
        User updatedUser = service.updatePassword(login, oldPassword, newPassword, repeatNewPassword);

        if(updatedUser != null){
            request.setAttribute("updated","Password successfully updated!");
            request.getSession().setAttribute("user",updatedUser);
            if ("student".equals(updatedUser.getRole())){
                page = PAGE_MAIN_STUDENT;
            }else if("teacher".equals(updatedUser.getRole())) {
                page = PAGE_MAIN_TEACHER;
            }else {
                page = PAGE_404;
            }
        }else {
            request.setAttribute("errorLoginPassMessage", "incorrect login or password");
            page=PAGE_LOGIN;
        }
        return page;
    }
}
