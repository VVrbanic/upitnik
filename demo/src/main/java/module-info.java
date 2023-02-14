module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;

    exports com.example.demo;
    opens com.example.demo to javafx.fxml;
}