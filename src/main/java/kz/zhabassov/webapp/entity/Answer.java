package kz.zhabassov.webapp.entity;

public class Answer {
    private String questionText;
    private String testName;
    private String teacherName;
    private String answerText;
    private boolean isRight;

    public Answer() {
    }

    public Answer(String questionText, String testName, String teacherName, String answerText, boolean isRight) {
        this.questionText = questionText;
        this.testName = testName;
        this.teacherName = teacherName;
        this.answerText = answerText;
        this.isRight = isRight;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String teacherName() {
        return teacherName;
    }

    public void setTeacherNmae(String teacherNmae) {
        this.teacherName = teacherNmae;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }
}
