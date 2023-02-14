package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class MenuBarController {
    public void showUserInput() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userInput-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        HelloApplication.getMainStage().setTitle("Korisnici");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void showUserEdit() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userEdit-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        HelloApplication.getMainStage().setTitle("Korisnici");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    public void showQuestionInput() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("questionInput-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        HelloApplication.getMainStage().setTitle("Pitanja");
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void showQuestionEdit() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("questionEdit-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        HelloApplication.getMainStage().setTitle("Pitanja");
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
    public void showNewQuestions() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newQuestion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        HelloApplication.getMainStage().setTitle("Pitanja");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
}

