package com.example.demo;

import java.util.Random;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class ResultsController {

    private boolean isDiplomaGenerated = false;
    private String textDiploma;
    private static final String PATH = "C:\\Users\\Vera\\Desktop\\Upitnik-opce-informiranosti\\demo\\src\\main\\java\\file\\";

    @FXML
    Label text;

    public void initialize() throws InterruptedException {
        ResultInfo<Integer, LocalDate> showText = new ResultInfo<>(QuestionsController.getResult(), LocalDate.now());
        text.setText("Broj osvojenih bodova: " + showText);
        textDiploma = "Broj osvojenih bodova: " + showText;
        Thread diplomaThread = new Thread(() -> {
            try {
                generateDiploma();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread uploadThread = new Thread(() -> {
            try {
                uploadDiploma();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        diplomaThread.start();
        uploadThread.start();
    }

    private synchronized void generateDiploma() throws InterruptedException {
        Thread.sleep(3000);
         Random random = new Random();
        String name = "diploma "+ String.valueOf(LocalDate.now()) + "-" + random.nextInt(1000) ;
        String text = textDiploma;

        try (FileWriter fileWriter = new FileWriter(PATH + name)) {
            fileWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Diploma je generirana");

        synchronized (this) {
            isDiplomaGenerated = true;
            notify();
        }
    }

    private synchronized void uploadDiploma() throws InterruptedException {
        synchronized (this) {
            while (!isDiplomaGenerated) {
                wait();
            }
        }
        Thread.sleep(3000);
        System.out.println("Diploma je uploadana");
        System.out.println("Sadržaj:");
        System.out.println(textDiploma);
    }

    public void newGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("quizCondition-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        HelloApplication.getMainStage().setTitle("Započnite kviz");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();

    }

    public void dashboard() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userDashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        HelloApplication.getMainStage().setTitle("Korisnik");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
}
