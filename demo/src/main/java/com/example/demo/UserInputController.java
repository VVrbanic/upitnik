package com.example.demo;

import entities.EducationLevel;
import entities.Input;
import entities.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;

public non-sealed class UserInputController implements GeneralFX {
    @FXML
    private TextField firstName;

    @FXML
    public TextField lastName;

    @FXML
    public TextField mail;

    @FXML
    public DatePicker birthDate;

    @FXML
    public ChoiceBox<EducationLevel> educationLevel;

    @FXML
    public TextField userName;

    @FXML
    public TextField password;

    @FXML
    public RadioButton roleAdmin;

    @FXML
    public RadioButton roleUser;

    public EducationLevel eduLvl;

    public static ArrayList<String> messages = new ArrayList<>();
    private static final String USER_FILE = "C:\\Users\\Vera\\Desktop\\Upitnik-opce-informiranosti\\demo\\src\\main\\java\\file\\users.txt";


    public void initialize(){
        educationLevel.setItems(FXCollections.observableArrayList(eduLvl.values()));
        ToggleGroup role = new ToggleGroup();
        roleUser.setToggleGroup(role);
        roleAdmin.setToggleGroup(role);
    }

    public void goBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        HelloApplication.getMainStage().setTitle("Registracija");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();

    }
    public void  save() {
        Long id;
        Boolean isAdmin;

        isFull(messages);
        //SREDI
        if(!Input.isUnique(mail.getText(), userName.getText(), password.getText())) {
            GeneralFX.userExists();
        }
        else {
            if (messages.size() == 0) {
                success();
                if (roleAdmin.getText().isBlank()) {
                    isAdmin = true;
                } else {
                    isAdmin = false;
                }
                id = (long) Input.getUsers(USER_FILE).size() + 1;
                User user = new User(id, isAdmin, firstName.getText(), lastName.getText(), mail.getText(), userName.getText(), password.getText(), educationLevel.getSelectionModel().getSelectedItem(), birthDate.getValue());
                System.out.println(user);
                Input.writeUser(user);
                clearAll();
            } else {
                String m = String.join("\n", messages);
                error(m);
            }
        }
    }


    private void clearAll() {
        firstName.clear();
        lastName.clear();
        mail.clear();
        userName.clear();
        password.clear();
        educationLevel.setValue(null);
        birthDate.setValue(null);
        roleUser.setSelected(false);
        roleAdmin.setSelected(false);
    }
    //METHODS
    public void isFull(ArrayList < String > messages) {
        if (firstName.getText().isBlank()) {
            messages.add("Molimo unesite ime");
        }
        if (lastName.getText().isBlank()) {
            messages.add("Molimo unesite prezime");
        }
        if (mail.getText().isBlank()) {
            messages.add("Molimo unesite mail");
        }
        if (birthDate.getValue() == null) {
            messages.add("Molimo unesite datum rodenja");
        }
        if (educationLevel.getSelectionModel().getSelectedItem() == null) {
            messages.add("Molimo unesite stupanj obrazovanja");
        }
        if (userName.getText().isBlank()) {
            messages.add("Molimo unosite korisničko ime!");
        }
        if (password.getText().isBlank()) {
            messages.add("Molim unesite lozinku");
        }
        if (roleAdmin.getText() == null || roleUser.getText() == null) {
            messages.add("Molimo označi rolu");
        }
    }
}
