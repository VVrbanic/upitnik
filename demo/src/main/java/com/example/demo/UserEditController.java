package com.example.demo;

import entities.EducationLevel;
import entities.Input;
import entities.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserEditController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;

    @FXML
    private TextField mail;
    @FXML
    private DatePicker birthDate;

    @FXML
    private ChoiceBox<EducationLevel> educationLevel;

    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    @FXML
    private RadioButton roleAdmin;

    @FXML
    private RadioButton roleUser;

    public EducationLevel eduLvl;

    //TABLICA
    @FXML
    private TableColumn<User, String> firstNameC;
    @FXML
    private TableColumn<User, String> lastNameC;
    @FXML
    private TableColumn<User, String> mailC;
    @FXML
    private TableColumn<User, String> dateOfBirthC;
    @FXML
    private TableColumn<User, String> userNameC;
    @FXML
    private TableColumn<User, String> passwordC;
    @FXML
    private TableColumn<User, String> roleC;
    @FXML
    private TableColumn<User, String> educationLevelC;

    @FXML
    private TableView<User> userTable;

    private static final String USER_FILE = "C:\\Users\\Vera\\Desktop\\Upitnik-opce-informiranosti\\demo\\src\\main\\java\\file\\users.txt";


    public static Map<Long, User> userMap = Input.getUsers(USER_FILE);

    static List<User> mapToUserList(Map<Long, User> userMap) {
        List<User> list = new ArrayList<>();
        for (Map.Entry<Long, User> entry : userMap.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    public void initialize(){
        System.out.println("Proslo:" + mapToUserList(userMap));
        firstNameC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        lastNameC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        mailC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        userNameC.setCellValueFactory(cellDate -> new SimpleStringProperty(cellDate.getValue().getUserName()));
        dateOfBirthC.setCellValueFactory(cellData -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                    String formattedDate = formatter.format(cellData.getValue().getDateOfBirth());
                    return new SimpleStringProperty(formattedDate);
                });
        educationLevelC.setCellValueFactory(cellDate -> new SimpleStringProperty(cellDate.getValue().getEducationLevel().getEducationName()));
        roleC.setCellValueFactory(cellDate -> new SimpleStringProperty(cellDate.getValue().getIsAdmin().toString()));

        userTable.setItems(FXCollections.observableList(mapToUserList(userMap)));


        educationLevel.setItems(FXCollections.observableArrayList(eduLvl.values()));
        ToggleGroup role = new ToggleGroup();
        roleUser.setToggleGroup(role);
        roleAdmin.setToggleGroup(role);
    }

    public void searchUser() {
        String firstNameTmp = firstName.getText();
        String lastNameTmp = lastName.getText();
        String mailTmp = mail.getText();
        LocalDate dateTmp = birthDate.getValue();
        String userNameTmp = userName.getText();
        EducationLevel educationLevelTmp = educationLevel.getSelectionModel().getSelectedItem();
        Boolean isAdmin = null;
        if (roleAdmin.isSelected()){
            isAdmin = true;
            //System.out.println(isAdmin);
        }
        else if (roleUser.isSelected()){
            isAdmin = false;
            //System.out.println(isAdmin);

        }

        List<User> filteredUsers = new ArrayList<>();
        if (dateTmp == null) {
            Boolean finalIsAdmin = isAdmin;
            filteredUsers = mapToUserList(userMap).stream().filter(s -> s.getFirstName().contains(firstNameTmp)).
                    filter(s -> s.getLastName().contains(lastNameTmp)).filter(s -> s.getEmail().contains(mailTmp)).
                    filter(s -> s.getUserName().contains(userNameTmp)).filter(s -> s.getEducationLevel().equals(educationLevelTmp)).
                    //filter(s -> (s.getIsAdmin() == finalIsAdmin)).
                    toList();
        } else {
            Boolean finalIsAdmin = isAdmin;
            filteredUsers = mapToUserList(userMap).stream().filter(s -> s.getFirstName().contains(firstNameTmp)).
                    filter(s -> s.getLastName().contains(lastNameTmp)).filter(s -> s.getEmail().contains(mailTmp)).
                    filter(s -> s.getUserName().contains(userNameTmp)).filter(s -> s.getEducationLevel().equals(educationLevelTmp)).
                    //filter(s -> s.getIsAdmin() == finalIsAdmin).
                    toList();
            //filteredUsers = filteredUsers.stream().filter(s -> s.getDateOfBirth().equals(dateTmp)).toList();
        }

        userTable.setItems(FXCollections.observableList(filteredUsers));
    }

    /*public void dataToTable(){
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        userName.setText(selectedUser.getUserName());
        password.setText(selectedUser.getPassword());
        firstName.setText(selectedUser.getFirstName());
        lastName.setText(selectedUser.getLastName());
        mail.setText(selectedUser.getEmail());
        birthDate.setValue(selectedUser.getDateOfBirth());
        if (selectedUser.getIsAdmin() == true){
            roleAdmin.setSelected(true);
        }
        else{
            roleUser.setSelected(true);
        }

    }*/
}
