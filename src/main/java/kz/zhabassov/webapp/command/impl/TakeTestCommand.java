package kz.zhabassov.webapp.command.impl;

import kz.zhabassov.webapp.command.ActionCommand;
import kz.zhabassov.webapp.entity.Test;
import kz.zhabassov.webapp.service.TestService;
import kz.zhabassov.webapp.service.impl.TestServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class TakeTestCommand implements ActionCommand {
    private static final String PARAM_TEST_NAME = "testName";
    private static final String PARAM_TEACHER_NAME = "teacherName";
    private static final String PARAM_QUESTION_QUANTITY = "questionQuantity";
    private static final String PARAM_TEST_ID = "testId";
    private static final String PARAM_LANGUAGE = "language";
    private static final String PARAM_IS_TEST_PUBLISHED = "isTestPublished";
    private static final String PAGE_MY_MARK = "path.page.pass.test";

    private TestService service = new TestServiceImpl();
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String page ;
        int testId = Integer.parseInt(request.getParameter(PARAM_TEST_ID));
        String testName = request.getParameter(PARAM_TEST_NAME);
        int questionQuantity = Integer.parseInt(request.getParameter(PARAM_QUESTION_QUANTITY));
        String language = request.getParameter(PARAM_LANGUAGE);
        String teacherName = request.getParameter(PARAM_TEACHER_NAME);
        Boolean isTestPublished = Boolean.parseBoolean(request.getParameter(PARAM_IS_TEST_PUBLISHED));
        Test test = new Test(testId,testName,questionQuantity,language,teacherName,isTestPublished);
        test.initQuestions();
        test=service.getTest(test);
        page = PAGE_MY_MARK;
        request.getSession().setAttribute("test",test);
        return page;
    }
}
