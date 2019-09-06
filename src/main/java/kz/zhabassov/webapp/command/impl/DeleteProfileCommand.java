package kz.zhabassov.webapp.command.impl;

import kz.zhabassov.webapp.command.ActionCommand;
import kz.zhabassov.webapp.entity.User;
import kz.zhabassov.webapp.service.UserService;
import kz.zhabassov.webapp.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class DeleteProfileCommand implements ActionCommand {
    private static final String PAGE_LOGIN = "path.page.login";
    private static final String PAGE_404 = "path.page.404";
    UserService service = new UserServiceImpl();
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String page;
        User user = (User) request.getSession().getAttribute("user");
        if (service.deleteProfile(user)){
            page = PAGE_LOGIN;
        }else {
            page=PAGE_404;
        }
        return page;
    }
}
