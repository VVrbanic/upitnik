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
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        HelloApplication.getMainStage().setTitle("Dashboard");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    public void startQuiz() throws IOException {
        indexCategory = (Category) category.getValue();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("questions-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        HelloApplication.getMainStage().setTitle("Kviz");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    public static Integer getIndex(){
        Category tmpCategory = indexCategory;
        Integer index = tmpCategory.getCategoryNumber();
        System.out.println(tmpCategory.getCategoryNumber());

        return index;
    }


}
