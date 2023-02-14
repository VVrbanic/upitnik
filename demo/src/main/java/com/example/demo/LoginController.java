package com.example.demo;

import entities.Input;
import exceptions.NoUserFoundException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public non-sealed class LoginController implements GeneralFX{
    public Button registrationButton;
    public Button loginButton;

    public TextField nickname;

    public TextField password;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    Printer<String> message = new Printer("Podaci nisu uneseni!");
    ArrayList< String > messages = new ArrayList<>();
    public static final String FILE_NAME = "C:\\Users\\Vera\\Desktop\\Upitnik-opce-informiranosti\\project\\src\\main\\java\\file\\users.txt";


    public void login() throws IOException {
        try {
            isItFull(messages);
        }catch (NoUserFoundException ex){
            System.out.println(message.printerThing);
            logger.error(message.printerThing);

        }
        if (messages.size() == 0) {
            logger.error("Uspješno ulogirani u bazu!");
            System.out.println("proslo");
            String hashPass = GeneralFX.hashPassword(password.getText());
            if(Input.checkUsersPassword(nickname.getText(), hashPass).isPresent()){
                if(Input.checkUsersPassword(nickname.getText(), hashPass).get().getIsAdmin().equals(false)){
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userDashboard-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 500, 300);
                    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                    HelloApplication.getMainStage().setTitle("Korisnik");
                    HelloApplication.getMainStage().setScene(scene);
                    HelloApplication.getMainStage().show();

                }else{
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menuBar-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

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
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        HelloApplication.getMainStage().setTitle("Registracija");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    //METHODS
    public void isItFull(ArrayList< String > messages) throws NoUserFoundException {
        messages.clear();
        if (nickname.getText().isBlank()) {
            messages.add("Molimo unesite korisničko ime");
        }
        if (password.getText().isBlank()) {
            messages.add("Molimo unesite lozinku");
        }
        System.out.println(messages.size());
        if(messages.size() != 0){
            throw new NoUserFoundException();
        }
    }
}