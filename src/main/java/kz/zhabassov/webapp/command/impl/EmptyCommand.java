package kz.zhabassov.webapp.command.impl;

import kz.zhabassov.webapp.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    private static final String PAGE_LOGIN = "path.page.login";

    @Override
    public String execute(HttpServletRequest request) {
        String page = PAGE_LOGIN;
        return page;
    }
}
