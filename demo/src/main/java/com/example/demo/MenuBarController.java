package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuBarController {
    public void showUserInput() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userInput-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        HelloApplication.getMainStage().setTitle("Korisnici-unos");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void showUserEdit() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userEdit-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        HelloApplication.getMainStage().setTitle("Korisnici-ažuriranje");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
}
