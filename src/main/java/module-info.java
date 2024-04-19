module org.example.jproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.jproject to javafx.fxml;
    exports org.example.jproject;
}