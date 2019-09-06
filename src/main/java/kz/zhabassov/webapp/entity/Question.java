package kz.zhabassov.webapp.entity;


import java.util.HashSet;
import java.util.Set;

public class Question {
    private String questionText;
    private String testName;
    private String teacherName;
    private Answer[] wrongAnswers;
    private Answer correctAnswer;

    public Question(Test test) {
        this.questionText = "";
        this.testName = test.getTestName();
        this.teacherName = test.getTeacherName();
        wrongAnswers = new Answer[4];
        this.initAnswers(test);
        correctAnswer = new Answer();
    }

//    public Question(String questionText, String testName, String teacherName) {

//        wrongAnswers = new Answer[5];
//        this.initAnswers();
//    }

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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Answer[] getAnswers() {
        return wrongAnswers;
    }

    public void setAnswers(Answer[] answers) {
        this.wrongAnswers = answers;
    }

    public void setAnswerTextRight(int i, String answerText, boolean isRight){
        for (; i <wrongAnswers.length; ) {
            wrongAnswers[i].setAnswerText(answerText);
            wrongAnswers[i].setRight(isRight);
        }
    }

    public void initAnswers(Test test){
        for (int i = 0; i <4 ; i++) {
            wrongAnswers[i] = new Answer("",test.getTestName(),test.getTeacherName(),"",false );
        }
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public Set<Answer> getAllAnswers(){
        Set<Answer> allAnswers = new HashSet<>();
        for (Answer answer : wrongAnswers) {
            allAnswers.add(answer);
        }
        allAnswers.add(correctAnswer);

        return allAnswers;
    }
}
