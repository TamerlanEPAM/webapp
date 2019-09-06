package kz.zhabassov.webapp.command.impl;

import kz.zhabassov.webapp.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class ChangeLanguageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String language = request.getParameter("language");
        request.getSession().setAttribute("language", language);
        return request.getParameter("page");
    }
}
