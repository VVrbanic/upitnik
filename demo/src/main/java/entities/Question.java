package entities;

import java.io.Serializable;

public class Question implements Serializable {
    Long id;
    Category category;
    Integer correctAnwser;
    String a;
    String b;
    String c;
    String d;
    String question;

    public Question(Long id, Category category, Integer correctAnwser, String a, String b, String c, String d, String question) {
        this.id = id;
        this.category = category;
        this.correctAnwser = correctAnwser;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.question = question;
    }

    public Integer getcorrectAnwser() {
        return correctAnwser;
    }

    public void setcorrectAnwser(Integer correctQuestion) {
        this.correctAnwser = correctQuestion;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCorrectAnwser() {
        return correctAnwser;
    }

    public void setCorrectAnwser(Integer correctAnwser) {
        this.correctAnwser = correctAnwser;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", category=" + category +
                ", correctAnwser=" + correctAnwser +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", question='" + question + '\'' +
                '}';
    }

    public static class Builder{
        Long id;
        Category category;
        Integer correctAnwser;
        String a;
        String b;
        String c;
        String d;
        String question;

        public Builder(long id){
            this.id = id;
        }

        public Builder withCategory(Category category){
            this.category = category;
            return this;
        }

        public Builder withCorrectAnwser(Integer correctAnwser){
            this.correctAnwser = correctAnwser;
            return this;
        }
        public Builder withA(String a){
            this.a = a;
            return this;
        }
        public Builder withB(String b){
            this.b = b;
            return this;
        }
        public Builder withC(String c){
            this.c = c;
            return this;
        }
        public Builder withD(String d){
            this.d = d;
            return this;
        }

        public Builder withQuestion(String question){
            this.question = question;
            return this;
        }

        public Question build(){
            Question q = new
                    Question();
            q.category = this.category;
            q.correctAnwser = this.correctAnwser;
            q.a = this.a;
            q.b = this.b;
            q.c = this.c;
            q.d = this.d;
            q.question = this.question;

            return q;
        }
    }
    private Question(){

    }
}
