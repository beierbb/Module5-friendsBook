module com.example.module5friendsbood {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.module5friendsbook to javafx.fxml;
    exports com.example.module5friendsbook;
}