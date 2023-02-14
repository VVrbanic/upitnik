package com.example.demo;

import entities.EducationLevel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage mainStage;
    EducationLevel edV;

    public static Stage getMainStage() {
        return mainStage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}