package com.example.demo;

import entities.Input;
import entities.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class LoginController implements GeneralFX{
    public Button registrationButton;
    public Button loginButton;

    public TextField nickname;

    public TextField password;

    ArrayList< String > messages = new ArrayList<>();
    public static final String FILE_NAME = "C:\\Users\\Vera\\Desktop\\Upitnik-opce-informiranosti\\project\\src\\main\\java\\file\\users.txt";
    public void login() throws IOException {
        isItFull(messages);
        if (messages.size() == 0) {
            /*System.out.println(nickname.getText());
            System.out.println(password.getText());*/
            if(Input.checkUsersPassword(nickname.getText(), password.getText()).isPresent()){
                if(Input.checkUsersPassword(nickname.getText(), password.getText()).get().getIsAdmin().equals(false)){
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userDashboard-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 500, 300);
                    HelloApplication.getMainStage().setTitle("Korisnik");
                    HelloApplication.getMainStage().setScene(scene);
                    HelloApplication.getMainStage().show();
                }else{
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menuBar-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                    HelloApplication.getMainStage().setTitle("Admin" +
                            "");
                    HelloApplication.getMainStage().setScene(scene);
                    HelloApplication.getMainStage().show();
                }


            }
            else{
                String m = "Neispravno korisničko ime ili lozinka!";
                error(m);


            }
            //Input.writeUser(user);
        } else {
            String m = String.join("\n", messages);
            error(m);
        }

    }
    public void showRegistrationScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registration-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        HelloApplication.getMainStage().setTitle("Registracija");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    //METHODS
    public void isItFull(ArrayList< String > messages) {
        messages.clear();
        if (nickname.getText().isBlank()) {
            messages.add("Molimo unesite korisničko ime");
        }
        if (password.getText().isBlank()) {
            messages.add("Molimo unesite lozinku");
        }
    }

}