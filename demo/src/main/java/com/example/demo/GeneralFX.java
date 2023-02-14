package com.example.demo;

import javafx.scene.control.Alert;

public sealed interface GeneralFX  permits LoginController, RegistrationController, UserInputController{

    static void userExists() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText(null);
        alert.setContentText("Korisničko ime ili lozinka već postoje!");
        alert.showAndWait();
    }

    default void success() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Čestitamo!");
        alert.setHeaderText(null);
        alert.setContentText("Uspješno kreirano!");
        alert.showAndWait();
    }
    default void error(String m){
        var alert = new Alert(Alert.AlertType.ERROR, m);
        alert.setTitle("Greška pri unosu!");
        alert.show();
    }
    static String hashPassword(String password){
         String hashPass = String.valueOf(password.hashCode());
         return hashPass;
    }
}
