module com.example.javafxdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javafxdb to javafx.fxml;
    exports com.example.javafxdb;
    exports com.example.javafxdb.dao;
    opens com.example.javafxdb.dao to javafx.fxml;
    exports com.example.javafxdb.service;
    opens com.example.javafxdb.service to javafx.fxml;
    exports com.example.javafxdb.util;
    opens com.example.javafxdb.util to javafx.fxml;
}