package com.example.practica2_fis;

import javafx.collections.ObservableList;

import java.sql.*;

public class Registros {
    /**
     * Guardar la la información de los niños en la tabla
     * */

    private int no;
    private int noAuto;
    private String NombreKid;

    public Registros(int no, int noAuto, String nombreKid) {
        this.no = no;
        this.noAuto = noAuto;
        this.NombreKid = nombreKid;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getNoAuto() {
        return noAuto;
    }

    public void setNoAuto(int noAuto) {
        this.noAuto = noAuto;
    }

    public String getNombreKid() {
        return NombreKid;
    }

    public void setNombreKid(String nombreKid) {
        this.NombreKid = nombreKid;
    }

/** Guardar los niños que llegan */
    public int guardarRegistro(Connection connection) {
        try {
           PreparedStatement ps = connection.prepareStatement("insert into kids(`no`, `noAuto`, `NombreKid`) VALUES (?,?,?)");
            ps.setInt(1, no);
            ps.setInt(2, noAuto);
            ps.setString(3, NombreKid);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
/** LLenar la tabla cada vez que lleguen los niños */
    public static void llenarInformacion(Connection connection,
                                         ObservableList<Registros> lista) {
        try {
            Statement instruccion = connection.createStatement();
            ResultSet resultado = instruccion.executeQuery(
                    "SELECT * from kids"
            );
            while (resultado.next()) {
                lista.add(
                        new Registros(
                                resultado.getInt("no"),
                                resultado.getInt("noAuto"),
                                resultado.getString("NombreKid")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
