package com.example.demo;

import FileBase.FileBase;
import entities.Category;
import entities.CorrectAnwser;
import entities.Question;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class QuestionInputController {
    @FXML
    TextField question;
    @FXML
    TextField a;
    @FXML
    TextField b;
    @FXML
    TextField c;
    @FXML
    TextField d;
    @FXML
    ChoiceBox category;
    @FXML
    ChoiceBox correctAwnser;
    Category categories;
    List<String> correctAnwserList;
    public static final String SERIALIZATION_FILE_NAME = "change.dat";

    public void initialize() {
        correctAnwserList = getCorrectAnwserList();
        category.setItems(FXCollections.observableArrayList(categories.values()));
        correctAwnser.setItems(FXCollections.observableList(correctAnwserList));
    }
    public List<String> getCorrectAnwserList(){
        List<String> correctAnwsers = new ArrayList<>();
        CorrectAnwser a = new CorrectAnwser("a");
        CorrectAnwser b = new CorrectAnwser("b");
        CorrectAnwser c = new CorrectAnwser("c");
        CorrectAnwser d = new CorrectAnwser("d");
        correctAnwsers.add(a.anwser());
        correctAnwsers.add(b.anwser());
        correctAnwsers.add(c.anwser());
        correctAnwsers.add(d.anwser());

        return correctAnwsers;
    }

    public void saveQuestion(){
        ArrayList<String> messages = new ArrayList<>();

        if (question.getText().isBlank()){
            messages.add("Unesite pitanje!");
        }
        if (a.getText().isBlank()){
            messages.add("Unesite odgovor a!");
        }
        if (b.getText().isBlank()){
            messages.add("Unesite odgovor b!");
        }
        if (c.getText().isBlank()){
            messages.add("Unesite odgovor c!");
        }
        if (d.getText().isBlank()){
            messages.add("Unesite odgovor d!");
        }
        if(category.getSelectionModel().getSelectedItem() == null) {
            messages.add("Unesite kategoriju!");
        }
        if(correctAwnser.getSelectionModel().getSelectedItem() == null) {
            messages.add("Unesite točan odgovor!");
        }
        if (messages.size() == 0){
            Integer correctAwnserInteger = getCorrectAnwserInteger((String) correctAwnser.getSelectionModel().getSelectedItem());
            if (correctAwnserInteger == -1){
                String m = "Nedozvoljen točan odgovor";
                var alert = new Alert(Alert.AlertType.ERROR, m);
                alert.setTitle("Greška pri unosu!");
                alert.show();
            }
            Category categoryTmp = (Category) category.getSelectionModel().getSelectedItem();
            Integer categoryTmpInteger = categoryTmp.getCategoryNumber();

            Question newQuestion = new Question(1L, categoryTmp, correctAwnserInteger, a.getText(), b.getText(), c.getText(), d.getText(), question.getText());
            FileBase.saveQuestion(newQuestion);
            var alert = new Alert(Alert.AlertType.INFORMATION, "Uspjeh");
            alert.setTitle("Uneseno novo pitanje!");
            alert.show();
            serialziation(newQuestion);
            clearAll();

        }
        else{
            String m = String.join("\n", messages);

            var alert = new Alert(Alert.AlertType.ERROR, m);
            alert.setTitle("Greška pri unosu!");
            alert.show();
        }
    }

    private void serialziation(Question newQuestion) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(SERIALIZATION_FILE_NAME, true))) {
            Question q = newQuestion;
            out.writeObject(q);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void clearAll() {
        question.setText(null);
        a.setText(null);
        b.setText(null);
        c.setText(null);
        d.setText(null);
        category.setValue(null);
        correctAwnser.setValue(null);

    }

    private Integer getCorrectAnwserInteger(String selectedItem) {
        if(selectedItem == "a"){
            return 1;
        }
        if(selectedItem == "b"){
            return 2;
        }
        if(selectedItem == "c"){
            return 3;
        }
        if(selectedItem == "d"){
            return 4;
        }
        else{
            return -1;
        }
    }
}