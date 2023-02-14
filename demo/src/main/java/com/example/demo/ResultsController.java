package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.LocalDate;

public class ResultsController {

    @FXML
    Label text;

    public void initialize(){
        ResultInfo<Integer, LocalDate> showText = new ResultInfo<>(QuestionsController.getResult(), LocalDate.now());
        text.setText("Broj osvojenih bodova: " + showText);
    }

    public void newGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("quizCondition-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        HelloApplication.getMainStage().setTitle("Započnite kviz");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();

    }

    public void statistic() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userStatistic-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        HelloApplication.getMainStage().setTitle("Statistika");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    public void dashboard() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userDashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        HelloApplication.getMainStage().setTitle("Korisnik");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
}
