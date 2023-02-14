package com.example.demo;

import FileBase.FileBase;
import entities.Category;
import entities.CorrectAnwser;
import entities.Question;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class QuestionEditController {
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

    @FXML
    TableView<Question> questionTableView;
    @FXML
    private TableColumn<Question, String> questionC;
    @FXML
    private TableColumn<Question, String> aC;
    @FXML
    private TableColumn<Question, String> bC;
    @FXML
    private TableColumn<Question, String> cC;
    @FXML
    private TableColumn<Question, String> dC;
    @FXML
    private TableColumn<Question, String> categoryC;
    @FXML
    private TableColumn<Question, String> correctAnwserC;

    List<Question> questionList = new ArrayList<>(FileBase.getQuestion());

    Category categories;
    List<String> correctAnwserList;

    public void initialize() {
        List<Question> sortedQuestionList = questionList.stream().sorted(Comparator.comparing(Question::getCategory)).collect(Collectors.toList());
        correctAnwserList = getCorrectAnwserList();
        category.setItems(FXCollections.observableArrayList(categories.values()));
        correctAwnser.setItems(FXCollections.observableList(correctAnwserList));
        questionC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuestion()));
        aC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getA()));
        bC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getB()));
        cC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getC()));
        dC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getD()));
        categoryC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getCategoryName()));
        correctAnwserC.setCellValueFactory(cellData -> new SimpleStringProperty(correctAwnserToString(cellData.getValue().getCorrectAnwser())));
        questionTableView.setItems(FXCollections.observableList(sortedQuestionList));
        ObservableList<Question> selectedItems = questionTableView.getSelectionModel().getSelectedItems();
        selectedItems.addListener(new ListChangeListener<Question>() {
            @Override
            public void onChanged(
                    Change<? extends Question> change) {
                if(change.getList().size() == 0) return;
                Question questionSelected = change.getList().get(0);
                question.setText(questionSelected.getQuestion());
                a.setText(questionSelected.getA());
                b.setText(questionSelected.getB());
                c.setText(questionSelected.getC());
                d.setText(questionSelected.getD());
                category.setValue(questionSelected.getCategory());
                correctAwnser.setValue(correctAwnserToString(questionSelected.getCorrectAnwser()));
            }
        });
    }

    private String correctAwnserToString(Integer correctAnwser) {
        if(correctAnwser == 1){
            return "a";
        }
        else if(correctAnwser == 2){
            return "b";
        }
        else if(correctAnwser == 3){
            return "c";
        }
        else{
            return "d";
        }
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
    public void deleteQuestion(){
        Question q = questionTableView.getSelectionModel().getSelectedItem();
        FileBase.deleteQuestion(q);
        questionTableView.getItems().remove(q);

    }
    public void searchQuestion(){
        String questionTmp = question.getText();
        String aTmp = a.getText();
        String bTmp = b.getText();
        String cTmp = c.getText();
        String dTmp = d.getText();
        Integer categoryIntTmp = 0;
        Integer correctAnwserIntTmp = 0;
        Category categoryTmp = (Category) category.getSelectionModel().getSelectedItem();
        if(categoryTmp != null) {
            categoryIntTmp = categoryTmp.getCategoryNumber();
        }
        String correctAnwserTmp = (String) correctAwnser.getSelectionModel().getSelectedItem();
        if (correctAnwserTmp != null) {
            correctAnwserIntTmp = getCorrectAnwserInteger(correctAnwserTmp);
        }
        Integer finalCorrectAnwserIntTmp = correctAnwserIntTmp;
        Integer finalCategoryIntTmp = categoryIntTmp;
        List<Question> filtriraniPredmeti;
        if(finalCategoryIntTmp != 0 && finalCorrectAnwserIntTmp != 0) {
            filtriraniPredmeti = FileBase.getQuestion().stream().filter(s -> s.getQuestion().contains(questionTmp)).
                    filter(s -> s.getA().contains(aTmp)).filter(s -> s.getB().contains(bTmp)).filter(s -> s.getC().contains(cTmp)).filter(s -> s.getD().contains(dTmp)).
                    filter(s -> s.getCategory().getCategoryNumber().equals(finalCategoryIntTmp)).filter(s -> s.getCorrectAnwser().equals(finalCorrectAnwserIntTmp)).
                    toList();
        }else if(finalCorrectAnwserIntTmp != 0) {
            filtriraniPredmeti = FileBase.getQuestion().stream().filter(s -> s.getQuestion().contains(questionTmp)).
                    filter(s -> s.getA().contains(aTmp)).filter(s -> s.getB().contains(bTmp)).filter(s -> s.getC().contains(cTmp)).filter(s -> s.getD().contains(dTmp)).
                    filter(s -> s.getCorrectAnwser().equals(finalCorrectAnwserIntTmp)).
                    toList();
        }else if(finalCategoryIntTmp != 0) {
            filtriraniPredmeti = FileBase.getQuestion().stream().filter(s -> s.getQuestion().contains(questionTmp)).
                    filter(s -> s.getA().contains(aTmp)).filter(s -> s.getB().contains(bTmp)).filter(s -> s.getC().contains(cTmp)).filter(s -> s.getD().contains(dTmp)).
                    filter(s -> s.getCategory().getCategoryNumber().equals(finalCategoryIntTmp)).
                    toList();
        }else {
            filtriraniPredmeti = FileBase.getQuestion().stream().filter(s -> s.getQuestion().contains(questionTmp)).
                    filter(s -> s.getA().contains(aTmp)).filter(s -> s.getB().contains(bTmp)).filter(s -> s.getC().contains(cTmp)).filter(s -> s.getD().contains(dTmp)).
                    toList();
        }

        questionTableView.setItems(FXCollections.observableList(filtriraniPredmeti));

    }
    public void editQuestion(){
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

            Question newQuestion = new Question(questionTableView.getSelectionModel().getSelectedItem().getId(), categoryTmp, correctAwnserInteger, a.getText(), b.getText(), c.getText(), d.getText(), question.getText());
            FileBase.editQuestion(newQuestion);
            var alert = new Alert(Alert.AlertType.INFORMATION, "Uspjeh");
            alert.setTitle("Uneseno novo pitanje!");
            alert.show();
            questionList = FileBase.getQuestion();
            System.out.println(questionList);
            questionTableView.setItems(FXCollections.observableList(questionList));

        }
        else{
            String m = String.join("\n", messages);

            var alert = new Alert(Alert.AlertType.ERROR, m);
            alert.setTitle("Greška pri unosu!");
            alert.show();
        }
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
