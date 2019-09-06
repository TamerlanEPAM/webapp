package kz.zhabassov.webapp.command.impl;

import kz.zhabassov.webapp.command.ActionCommand;
import kz.zhabassov.webapp.entity.Test;
import kz.zhabassov.webapp.entity.User;
import kz.zhabassov.webapp.service.TestService;
import kz.zhabassov.webapp.service.impl.TestServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class CreateTestCommand implements ActionCommand {
    private static final String PARAM_TEST_NAME = "testName";
    private static final String PARAM_QUESTION_QUANTITY = "questionQuantity";
    private static final String PARAM_LANGUAGE = "language";
    private static final String PAGE_FILL_TEST = "path.page.fill.test";
    private static final String PAGE_404 = "path.page.404";

    private TestService service = new TestServiceImpl();


    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String page;
        String testName = request.getParameter(PARAM_TEST_NAME);
        int questionQuantity = Integer.parseInt(request.getParameter(PARAM_QUESTION_QUANTITY));
        String language = request.getParameter(PARAM_LANGUAGE);
        User user = (User) request.getSession().getAttribute("user");
        String teacherName = user.getUsername();
        Test test = service.create(new Test(testName, questionQuantity, language, teacherName));
        if (test != null) {
            test.initQuestions();
            request.getSession().setAttribute("test", test);
            page = PAGE_FILL_TEST;
        } else {
            //already exist
            page=PAGE_404;
        }
        return page;
    }
}
