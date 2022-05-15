module com.example.practica2_fis {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.practica2_fis to javafx.fxml;
    exports com.example.practica2_fis;
}