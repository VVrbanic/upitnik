package com.example.demo;

import FileBase.FileBase;
import entities.Question;
import exceptions.NoAnwserException;
import exceptions.NoQuestionsFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public Integer i = 0;
    public static Integer result = 0;
    ToggleGroup anwser = new ToggleGroup();
    List<Question> questionList = new ArrayList<>(FileBase.getQuiz(QuizConditionController.getIndex()));
    private static final Logger logger = LoggerFactory.getLogger(QuestionsController.class);

    public void initialize() throws IOException {
        try {
            checkQuestionNumber();
        }catch (NoQuestionsFoundException ex){
            System.out.println("Nedovoljno pitanja za kviz!");
            logger.error("Nedovoljno pitanja za kviz!");

        }
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

    private void checkQuestionNumber() throws NoQuestionsFoundException {
        if (questionList.size() < 5){
            throw new NoQuestionsFoundException();
        }
    }

    public void nextQuestion() throws IOException {
        try{
            doesAnwserExist();
        }catch (NoAnwserException na) {
            System.out.println("Pitanje nije odgovoreno!");
            logger.error("Pitanje nije odgovoreno!");

        }
        checkIfCorrect();
        if (i < 4){
            i++;
            initialize();

        }
        else{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("results-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 300);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            HelloApplication.getMainStage().setTitle("Rezultati");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        }
    }

    private void doesAnwserExist() {
        if(!(a.isSelected() || b.isSelected() || c.isSelected() || d.isSelected())){
            throw new NoAnwserException();
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
