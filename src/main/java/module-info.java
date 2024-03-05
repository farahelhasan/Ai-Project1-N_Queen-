module com.example.aiprojict1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aiprojict1 to javafx.fxml;
    exports com.example.aiprojict1;
}