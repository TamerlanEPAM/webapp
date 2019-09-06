package kz.zhabassov.webapp.entity;

public class Test {
    private int id;
    private String testName;
    private int questionQuantity;
    private String language;
    private String teacherName;
    private boolean isTestPublished;
    private Question[] questions ;

    public Test(String testName, int questionQuantity, String language, String teacherName, boolean isTestPublished) {
        this.testName = testName;
        this.questionQuantity = questionQuantity;
        this.language = language;
        this.teacherName = teacherName;
        this.isTestPublished = isTestPublished;
        questions = new Question[questionQuantity];
    }

    public Test(int id, String testName, int questionQuantity, String language, String teacherName, boolean isTestPublished) {
        this.id = id;
        this.testName = testName;
        this.questionQuantity = questionQuantity;
        this.language = language;
        this.teacherName = teacherName;
        this.isTestPublished = isTestPublished;
        questions = new Question[questionQuantity];
    }

    public Test(String testName, int questionQuantity, String language, String teacherName) {
        this.testName = testName;
        this.questionQuantity = questionQuantity;
        this.language = language;
        this.teacherName = teacherName;
        questions = new Question[questionQuantity];
    }

    public Test() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getQuestionQuantity() {
        return questionQuantity;
    }

    public void setQuestionQuantity(int questionQuantity) {
        this.questionQuantity = questionQuantity;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public boolean isTestPublished() {
        return isTestPublished;
    }

    public void setTestPublished(boolean testPublished) {
        isTestPublished = testPublished;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    public void initQuestions(){
        for (int i = 0; i <questions.length ; i++) {
            questions[i] = new Question(this);
        }
    }
    public void setQuestionText(int i, String questionText){
        for (; i <questions.length; ) {
            questions[i].setQuestionText(questionText);
        }
    }

}
