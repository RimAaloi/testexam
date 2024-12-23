module org.example.testexam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.testexam to javafx.fxml;
    exports org.example.testexam;
    exports org.example.testexam.appconsole;
    opens org.example.testexam.appconsole to javafx.fxml;
}