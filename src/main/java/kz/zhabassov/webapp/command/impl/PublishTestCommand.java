package kz.zhabassov.webapp.command.impl;

import kz.zhabassov.webapp.command.ActionCommand;
import kz.zhabassov.webapp.entity.Answer;
import kz.zhabassov.webapp.entity.Test;
import kz.zhabassov.webapp.service.TestService;
import kz.zhabassov.webapp.service.impl.TestServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class PublishTestCommand implements ActionCommand {
    private static final String PARAM_QUESTION_TEXT = "questionText";
    private static final String PARAM_ANSWER_TEXT = "answerText";
    private static final String PARAM_RIGHT_ANSWER_TEXT = "correctAnswerText";
    private static final String PAGE_CRATED_TEST = "path.page.created.test";
    private TestService service = new TestServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String page;
        Test  test = (Test) request.getSession().getAttribute("test");
        String[] questionText = request.getParameterValues(PARAM_QUESTION_TEXT);
        String[] answerText = request.getParameterValues(PARAM_ANSWER_TEXT);
        String[] rightAnswerText = request.getParameterValues(PARAM_RIGHT_ANSWER_TEXT);

        for (int i = 0; i <test.getQuestions().length ; i++) {
            test.getQuestions()[i].setQuestionText(questionText[i]);
            test.getQuestions()[i].setCorrectAnswer(new Answer(questionText[i], test.getTestName(), test.getTeacherName(),rightAnswerText[i],true));
            for (int j = 0; j < test.getQuestions()[i].getAnswers().length; j++) {
                test.getQuestions()[i].getAnswers()[j].setAnswerText(answerText[i+j]);
                test.getQuestions()[i].getAnswers()[j].setQuestionText(questionText[i]);
            }
        }

        service.publish(test);

        request.getSession().setAttribute("test",test);
        page=PAGE_CRATED_TEST;
        return page;
    }
}
