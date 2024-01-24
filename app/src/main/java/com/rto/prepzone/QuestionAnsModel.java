package com.rto.prepzone;

import com.google.gson.annotations.SerializedName;

public class QuestionAnsModel {
    @SerializedName("id")
    String id;

    @SerializedName("question")
    String question;

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

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getAnswer() {
        return answer;
    }

}
