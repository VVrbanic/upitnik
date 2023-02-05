package com.example.demo;

import entities.Category;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;

public class QuizConditionController {
    @FXML
    public ChoiceBox category;

    public Category categories;

    public static Category  indexCategory;

    @FXML
    public void initialize(){
        category.setItems(FXCollections.observableArrayList(categories.values()));
    }

    public void goBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userDashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        HelloApplication.getMainStage().setTitle("Dashboard");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    public void startQuiz() throws IOException {
        indexCategory = (Category) category.getValue();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("questions-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        HelloApplication.getMainStage().setTitle("Kviz");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    public static Integer getIndex(){
        Category tmpCategory = indexCategory;
        Integer index = tmpCategory.getEducationNumber();
        System.out.println(tmpCategory.getEducationNumber());

        return index;
    }


}