package com.example.demo;

import FileBase.FileBase;
import entities.Question;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionsController {

    @FXML
    Label questionNumber;
    @FXML
    Label question;
    @FXML
    RadioButton a;
    @FXML
    RadioButton b;
    @FXML
    RadioButton c;
    @FXML
    RadioButton d;
    @FXML
    Button next;

    public static Integer i = 0;
    public static Integer result = 0;
    ToggleGroup anwser = new ToggleGroup();
    List<Question> questionList = new ArrayList<>(FileBase.getQuiz(QuizConditionController.getIndex()));

    public void initialize() {
        a.setToggleGroup(anwser);
        b.setToggleGroup(anwser);
        c.setToggleGroup(anwser);
        d.setToggleGroup(anwser);

        questionNumber.setText("Pitanje " + (i+1) +"/5");
        question.setText(questionList.get(i).getQuestion());
        a.setText(questionList.get(i).getA());
        b.setText(questionList.get(i).getB());
        c.setText(questionList.get(i).getC());
        d.setText(questionList.get(i).getD());
    }

    public void nextQuestion() throws IOException {
        checkIfCorrect();
        if (i < 4){
            i++;
            initialize();

        }
        else{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("results-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            HelloApplication.getMainStage().setTitle("Rezultati");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        }
    }

    private void checkIfCorrect() {
        if (questionList.get(i).getCorrectAnwser() == 1){
            if(a.isSelected()){
                result++;
            }
        }
        if (questionList.get(i).getCorrectAnwser() == 2){
            if(b.isSelected()){
                result++;
            }
        }
        if (questionList.get(i).getCorrectAnwser() == 3){
            if(c.isSelected()){
                result++;
            }
        }
        if (questionList.get(i).getCorrectAnwser() == 4){
            if(d.isSelected()){
                result++;
            }
        }
        anwser.selectToggle(null);
        System.out.println(questionList.get(i).getCorrectAnwser());
        System.out.println(result);
    }

    public static Integer getResult(){
        System.out.println(result);
        return result;
    }
}
