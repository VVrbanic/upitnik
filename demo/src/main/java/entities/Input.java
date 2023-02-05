package entities;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import static entities.EducationLevel.numberEducationLevel;
import static entities.EducationLevel.parseEducationLevel;

public class Input {
    private static final String USER_FILE = "C:\\Users\\Vera\\Desktop\\Upitnik-opce-informiranosti\\demo\\src\\main\\java\\file\\users.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
    public static Map<Long, User> getUsers(String filename){
        Map<Long,User> users = new HashMap<>();

        try(Scanner scanner = new Scanner(new File(filename))){
            while(scanner.hasNextLine()){
                long id = scanner.nextLong();
                scanner.nextLine();

                Integer role = scanner.nextInt();
                Boolean isAdmin = convertRole(role);
                scanner.nextLine();

                String firstName = scanner.nextLine();

                String lastName =  scanner.nextLine();

                String email =  scanner.nextLine();

                String userName =  scanner.nextLine();

                String password =  scanner.nextLine();

                Integer edLvl =  scanner.nextInt();
                EducationLevel educationLevel = parseEducationLevel(edLvl);
                scanner.nextLine();

                String date = scanner.nextLine();
                LocalDate dateOfBirth = LocalDate.parse(date, DATE_FORMAT) ;

                User user= new User(id,isAdmin, firstName, lastName,email, userName, password, educationLevel, dateOfBirth);
                users.put(user.getId(),user);
            }
        }catch(FileNotFoundException ex){
            throw new RuntimeException(String.format("File not found of #Users# class: %s%n", filename));
        }
        System.out.println(users);
        return users;
    }
    public static Optional<User> checkUsersPassword(String userName, String password){
        User tempUser;
        Map<Long,User> users = new HashMap<>();
        users = getUsers(USER_FILE);
        for (Map.Entry<Long, User> entry:users.entrySet()){
            tempUser = entry.getValue();
            System.out.println(tempUser.getUserName());
            if (tempUser.getUserName().equals(userName)) {
                System.out.println(tempUser.getPassword());
                if (tempUser.getPassword().equals(password)){
                    return Optional.of(tempUser);
                }
            }
        }
        return Optional.empty();
    }

    public static void writeUser(User user){
            try (PrintWriter out = new PrintWriter(
                    new FileWriter(new File(USER_FILE), true))) {
                    out.println(user.getId());
                    out.println(role(user));
                    out.println(user.getFirstName());
                    out.println(user.getLastName());
                    out.println(user.getEmail());
                    out.println(user.getUserName());
                    out.println(user.getPassword());
                    out.println(numberEducationLevel(user.getEducationLevel()));
                    DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                    out.println(dateFormater.format(user.getDateOfBirth()));
            } catch (IOException e) {
                System.err.println(e);
            }
    }

    private static Integer role(User user) {
        if(user.getIsAdmin().equals(true)){
            return 0;
        }
        else return 1;
    }

    private static Boolean convertRole(Integer role) {
        if (role == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean isUnique(String mail, String userName, String password) {
        User tempUser;
        Map<Long,User> users = new HashMap<>();
        users = getUsers(USER_FILE);
        for (Map.Entry<Long, User> entry:users.entrySet()){
            tempUser = entry.getValue();
            System.out.println(tempUser.getUserName());
            if (tempUser.getUserName().equals(userName) || tempUser.getPassword().equals(password)) {
                return false;
            }
        }
        return true;
    }
}
