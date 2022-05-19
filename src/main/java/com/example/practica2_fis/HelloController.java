package com.example.practica2_fis;

import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TableColumn<Registros, Number> clmNoNiño;
    @FXML
    private TableColumn<Registros, Number> clmNoAuto;
    @FXML
    private TableColumn<Registros, String> clmNiño;

    int No = 0;
    @FXML
    private TableView<Registros> tblNiños;
    private ObservableList<Registros> lista;
    @FXML
    private Circle Car1,Car2,Car3;
    private Conexion conexion;
    int auto = 0;
    public int NumNiños = 0;
    int NO = 0;
    String NombreNiño;
    String Nombres[] = {"Emmy",
            "Garwin",
            "Archambault",
            "Spense",
            "Vasili",
            "Tome",
            "Tadeo",
            "Beaufort",
            "Ossie",
            "Trev",
            "Cordie",
            "Giff"};


    /**
     * Codigo para la llegada aleatoria de los niños
     * y el registro de los niños
     */
    @FXML
    protected void Iniciar(ActionEvent event) throws IOException {
        int min = 1;
        int max = 3;

        Random random = new Random();
        int value;
        int NombreAleatorio;

        for (int i = 1; i < 4; i++) {
            if(i == 1){
                PathTransition pathTransition = new PathTransition(Duration.seconds(3), new Line(0, 0, 600, 0), Car1);
                pathTransition.setCycleCount(1);
                pathTransition.play();
            }
            if(i == 2){
                PathTransition pathTransition = new PathTransition(Duration.seconds(5), new Line(0, 0, 650, 0), Car2);
                pathTransition.setCycleCount(1);
                pathTransition.play();
            }
            if(i == 3){
                PathTransition pathTransition = new PathTransition(Duration.seconds(7), new Line(0, 0, 900, 0), Car3);
                pathTransition.setCycleCount(1);
                pathTransition.play();
            }


            value = random.nextInt(max + min) + min;
            NumNiños = value;
            for (int j = 1; j < NumNiños; j++) {
                NO++;
                NombreAleatorio = random.nextInt(11 + 0) + 0;
                System.out.println(Nombres[NombreAleatorio]);

                Registros a = new Registros(
                        NO, i, Nombres[NombreAleatorio]);

                conexion.establecerConexion();
                int resultado = a.guardarRegistro(conexion.getConnection());
                conexion.cerrarConexion();
                refresh();
            }
        }
    }

    public void refresh(){
        conexion = new Conexion();
        conexion.establecerConexion();
        lista = FXCollections.observableArrayList();
        Registros.llenarInformacion(conexion.getConnection(), lista);
        tblNiños.setItems(lista);

        clmNoNiño.setCellValueFactory(new PropertyValueFactory<Registros, Number>("no"));
        clmNoAuto.setCellValueFactory(new PropertyValueFactory<Registros, Number>("noAuto"));
        clmNiño.setCellValueFactory(new PropertyValueFactory<Registros, String>("NombreKid"));
        conexion.cerrarConexion();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
    }
}