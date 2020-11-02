package com.example.yellowochre;

/* Question main base class which has get set functions*/
public class Question {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int answer;
    private int questionNo;
    private boolean credit;
    private int id;

    public Question(String question, String option1, String option2, String option3, String option4, int answer, int id) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.id = id;
        this.credit = false;
    }


    public Question() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public boolean isCredit() {
        return credit;
    }

    public void setCredit(boolean credit) {
        this.credit = credit;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAnswerCorrect(String chosenAnswer){
        return (chosenAnswer.equals(answer));
    }

    public String toString(){
        return question;
    }
}