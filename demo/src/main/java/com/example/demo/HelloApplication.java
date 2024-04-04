package com.example.demo;

import entities.EducationLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import thread.ClockThread;
import thread.GoGif;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloApplication extends Application {
    public DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static Stage mainStage;

    public static Stage getMainStage() {
        return mainStage;
    }

    public static Integer i = 0;
    public static LocalDateTime timeNow = LocalDateTime.now();
    public static String ikona = "/ikona1.png";

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println(simpleDateFormat.format(HelloApplication.timeNow));
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle(simpleDateFormat.format(HelloApplication.timeNow));
        stage.getIcons().add(new Image(ikona));
        stage.setScene(scene);
        stage.show();
        showTimeCurrent();
        goGif();
    }
    public void showTimeCurrent() {
        Timeline showTime = new Timeline(
                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Platform.runLater(new ClockThread());
                    }
                }));
        showTime.setCycleCount(Timeline.INDEFINITE);
        showTime.play();
    }
    public void goGif() {
        Timeline showTime = new Timeline(
                new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Platform.runLater(new GoGif());
                    }
                }));
        showTime.setCycleCount(Timeline.INDEFINITE);
        showTime.play();
    }
    public static void main(String[] args) {
        launch();
    }
}