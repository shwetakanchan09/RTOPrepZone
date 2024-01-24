package com.rto.prepzone;

import com.google.gson.annotations.SerializedName;

public class PracticeModel {

    boolean isAnswered=false;

    @SerializedName("id")
    String id;

    @SerializedName("question")
    String question;

    @SerializedName("option_1")
    String option1;

    @SerializedName("option_2")
    String option2;

    @SerializedName("option_3")
    String option3;

    @SerializedName("answer")
    String answer;


    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    public String getQuestion() {
        return question;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }
    public String getOption1() {
        return option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }
    public String getOption2() {
        return option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }
    public String getOption3() {
        return option3;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getAnswer() {
        return answer;
    }

}

