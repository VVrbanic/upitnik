package FileBase;

import entities.Category;
import entities.EducationLevel;
import entities.Question;
import entities.User;
import javafx.fxml.FXML;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.*;

public class FileBase {
    private static Connection connection() throws IOException, SQLException {

        Properties properties = new Properties();
        properties.load(new FileReader("C:\\Users\\Vera\\Desktop\\Upitnik-opce-informiranosti\\demo\\src\\main\\java\\FileBase\\dataBase.properties"));
        String urlBaza = properties.getProperty("urlBase");
        Connection conn = DriverManager.getConnection(urlBaza);

        return conn;
    }

    public static void editQuestion(Question q) {
        try{
            Connection connection = connection();

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE QUESTIONS SET CATEGORY_ID = ? ,CORRECT_ANWSER = ?, A = ?, B = ?, C = ?, D = ?, QUESTION = ? WHERE ID  = ?");
            preparedStatement.setInt(1, q.getCategory().getCategoryNumber());
            preparedStatement.setInt(2, q.getCorrectAnwser());
            preparedStatement.setString(3, q.getA());
            preparedStatement.setString(4, q.getB());
            preparedStatement.setString(5, q.getC());
            preparedStatement.setString(6, q.getD());
            preparedStatement.setString(7, q.getQuestion());
            preparedStatement.setLong(8, q.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveQuestion(Question q){
        try{
            Connection connection = connection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO QUESTIONS(CATEGORY_ID,CORRECT_ANWSER, A, B, C, D, QUESTION) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, q.getCategory().getCategoryNumber());
            preparedStatement.setInt(2, q.getCorrectAnwser());
            preparedStatement.setString(3, q.getA());
            preparedStatement.setString(4, q.getB());
            preparedStatement.setString(5, q.getC());
            preparedStatement.setString(6, q.getD());
            preparedStatement.setString(7, q.getQuestion());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Question> getQuestion(){
        List<Question> questionList = new ArrayList<>();
        try {
            Connection connection = connection();

            if(connection != null) {
                System.out.println("Connected to the base");
            }

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM QUESTIONS");

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Category category = Category.parseEducationLevel(resultSet.getInt("category_id"));
                Integer correctAnwser = resultSet.getInt("correct_anwser");
                String a = resultSet.getString("a");
                String b = resultSet.getString("b");
                String c = resultSet.getString("c");
                String d = resultSet.getString("d");
                String question = resultSet.getString("question");

                Question tempQuestion = new Question(id, category,correctAnwser, a, b, c, d, question);
                questionList.add(tempQuestion);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return questionList;
    }

    public static List<Question> getQuiz(int n){
        List<Question> questionList = new ArrayList<>();
        try {
            Connection connection = connection();

            if(connection != null) {
                System.out.println("Connected to the base");
            }

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM QUESTIONS WHERE CATEGORY_ID = ?");
            preparedStatement.setInt(1,n);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Category category = Category.parseEducationLevel(resultSet.getInt("category_id"));
                Integer correctAnwser = resultSet.getInt("correct_anwser");
                String a = resultSet.getString("a");
                String b = resultSet.getString("b");
                String c = resultSet.getString("c");
                String d = resultSet.getString("d");
                String question = resultSet.getString("question");

                Question tempQuestion = new Question(id, category, correctAnwser, a, b, c, d, question);
                questionList.add(tempQuestion);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return questionList;
    }

    public static void deleteQuestion(Question q) {
        List<Question> questionList = new ArrayList<>();
        try {
            Connection connection = connection();

            if (connection != null) {
                System.out.println("Connected to the base");
            }

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM QUESTIONS WHERE ID = ?");
            System.out.println(q);
            preparedStatement.setLong(1, q.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}