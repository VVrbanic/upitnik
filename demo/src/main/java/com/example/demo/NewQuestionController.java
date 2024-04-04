package com.example.demo;

import FileBase.FileBase;
import entities.Category;
import entities.CorrectAnwser;
import entities.Question;
import exceptions.NoNewQuestionException;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewQuestionController {
    @FXML
    TableView<Question> questionTableView;
    @FXML
    TableColumn<Question, String> questionC;
    @FXML
    TableColumn<Question, String> aC;
    @FXML
    TableColumn<Question, String> bC;
    @FXML
    TableColumn<Question, String> cC;
    @FXML
    TableColumn<Question, String> dC;
    @FXML
    TableColumn<Question, String> categoryC;
    @FXML
    TableColumn<Question, String> correctAnwserC;
    public static final String SERIALIZATION_FILE_NAME = "change.dat";

    private static final Logger logger = LoggerFactory.getLogger(QuestionsController.class);

    List<Question> questionList = new ArrayList<>(getNewQuestions());

    public List<Question> getNewQuestions(){
        System.out.println("Uslo");
        List<Question> questionList = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(SERIALIZATION_FILE_NAME))) {
            try {
                while (true) {
                    Question q = (Question) in.readObject();
                    questionList.add(q);
                }
            }catch (EOFException eo){
                System.out.println("Dosli smo do kraja datoteke!");
            }
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        }
      return questionList;
    }

    public void initialize() {
        try{
            isNewQuestion();
        }catch (NoNewQuestionException nn){
            System.out.println("Nema novih pitanja!");
            logger.error("Nema novih pitanja!");

        }
        questionC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuestion()));
        aC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getA()));
        bC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getB()));
        cC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getC()));
        dC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getD()));
        categoryC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getCategoryName()));
        correctAnwserC.setCellValueFactory(cellData -> new SimpleStringProperty(correctAwnserToString(cellData.getValue().getCorrectAnwser())));
        questionTableView.setItems(FXCollections.observableList(questionList));
    }

    private void isNewQuestion() {
        if(questionList.size() == 0){
            throw new NoNewQuestionException();
        }
    }

    private String correctAwnserToString (Integer correctAnwser){
        if (correctAnwser == 1) {
            return "a";
        } else if (correctAnwser == 2) {
            return "b";
        } else if (correctAnwser == 3) {
            return "c";
        } else {
            return "d";
        }
    }


}
