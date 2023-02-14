package com.example.demo;

import entities.EducationLevel;
import entities.Input;
import entities.Role;
import entities.User;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ChoiceBox<Role> roles;
    public EducationLevel eduLvl;
    public Role role;

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
    /*@FXML
    private TableColumn<User, String> passwordC;*/
    @FXML
    private TableColumn<User, String> roleC;
    @FXML
    private TableColumn<User, String> educationLevelC;
    @FXML
    private TableView<User> userTable;
    private static final String USER_FILE = "C:\\Users\\Vera\\Desktop\\Upitnik-opce-informiranosti\\demo\\src\\main\\java\\file\\users.txt";

    public List<Role> listRole = new ArrayList<>();
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
        roles.setItems(FXCollections.observableArrayList(role.values()));
    }
    public void searchUser() {
        String firstNameTmp = firstName.getText();
        System.out.println(firstNameTmp);
        String lastNameTmp = lastName.getText();
        String mailTmp = mail.getText();
        LocalDate dateTmp = birthDate.getValue();
        String userNameTmp = userName.getText();
        EducationLevel educationLevelTmp = educationLevel.getSelectionModel().getSelectedItem();
       /*Role roleTmp = roles.getSelectionModel().getSelectedItem();
        System.out.println(roleTmp);
        Boolean isAdmin;
        if(roleTmp.getRoleName().equals("Admin")){
            isAdmin = true;
        }else{
            isAdmin = false;
        }
        System.out.println(isAdmin);*/

        List<User> filteredUsers = new ArrayList<>();
        if (dateTmp == null) {
            filteredUsers = mapToUserList(userMap).stream().filter(s -> s.getFirstName().contains(firstNameTmp)).
                    filter(s -> s.getLastName().contains(lastNameTmp)).filter(s -> s.getEmail().contains(mailTmp)).
                    filter(s -> s.getUserName().contains(userNameTmp)).filter(s -> s.getEducationLevel().getEducationName().equals(educationLevelTmp)).
                    toList();
        } else {
            filteredUsers = mapToUserList(userMap).stream().filter(s -> s.getFirstName().contains(firstNameTmp)).
                    filter(s -> s.getLastName().contains(lastNameTmp)).//filter(s -> s.getEmail().contains(mailTmp)).
                    //filter(s -> s.getUserName().contains(userNameTmp)).filter(s -> s.getEducationLevel().equals(educationLevelTmp)).
                    toList();
        }
        userTable.setItems(FXCollections.observableList(filteredUsers));
    }
}
