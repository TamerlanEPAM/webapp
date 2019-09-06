package kz.zhabassov.webapp.command.impl;

import kz.zhabassov.webapp.command.ActionCommand;
import kz.zhabassov.webapp.entity.Test;
import kz.zhabassov.webapp.service.TestService;
import kz.zhabassov.webapp.service.impl.TestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class GetTestsCommand implements ActionCommand {
    private static final String TITLE_ALL_TESTS = "All Tests";
    private static final String NAME_OF_TEST = "Name of test:";
    private static final String NAME_OF_TEACHER = "Name of teacher:";
    private static final String PAGE_ALL_TESTS = "path.page.all.tests";
    private static final String PAGE_404 = "path.page.404";

    private TestService service = new TestServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String page;
        ArrayList<Test> tests = new ArrayList<>();
        tests.addAll(service.getTests());
        if(tests!=null){
            request.getSession().setAttribute("tests",tests);
            page=PAGE_ALL_TESTS;
        }else {
            page = PAGE_404;
        }

        return page;
    }
}
