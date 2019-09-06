package kz.zhabassov.webapp.command.impl;

import kz.zhabassov.webapp.command.ActionCommand;
import kz.zhabassov.webapp.service.TestService;
import kz.zhabassov.webapp.service.impl.TestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class PassTestCommand implements ActionCommand {
    private static final String PARAM_TEST_NAME = "testName";
    private static final String PARAM_TEACHER_NAME = "teacherName";
    private static final String PARAM_QUESTION_TEXT = "questionText";
    private static final String PARAM_SELECTED_ANSWER = "selectedAnswer";
    private static final String PAGE_MY_MARK = "path.page.my.mark";
    private LocalDate date = LocalDate.now();
    private TestService service = new TestServiceImpl();
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String page;
        String testName = request.getParameter(PARAM_TEST_NAME);
        String teacherName = request.getParameter(PARAM_TEACHER_NAME);
        String[] questionText = request.getParameterValues(PARAM_QUESTION_TEXT);
        String[] selectedAnswer = request.getParameterValues(PARAM_SELECTED_ANSWER);
        int mark = service.getMark(testName, teacherName,questionText, selectedAnswer);
        request.getSession().removeAttribute("test");
        request.setAttribute("testName", testName);
        request.setAttribute("teacherName", teacherName);
        request.setAttribute("mark", mark);
        request.setAttribute("date",date);

        page = PAGE_MY_MARK;

        return page;
    }
}
