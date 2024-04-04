package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class UserDashboardController {
    public void seeResult() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userStatistic-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        HelloApplication.getMainStage().setTitle("Statistika");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void startQuiz() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("quizCondition-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        HelloApplication.getMainStage().setTitle("Započnite kviz");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();

    }
    public void logOut() throws IOException {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setContentText("Jeste sigurni da se želite odlogirati?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.get() == ButtonType.OK) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 300);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            HelloApplication.getMainStage().setTitle("Login");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        }

    }
}

