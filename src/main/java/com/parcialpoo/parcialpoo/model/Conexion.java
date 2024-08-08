package com.parcialpoo.parcialpoo.model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private static Connection conexion;

    private static Conexion instancia;

    private static final String URL = "jdbc:mysql://localhost/parcial";

    private static final String USER = "root";

    private static final String PASSWORD = "";

    private Conexion() {

    }

    public Connection conectar() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Conexion exitosa");

            return conexion;
        } catch (Exception e) {
            System.out.println("Error de conexion");
        }
        return conexion;
    }

    public void cerrarConexion() throws SQLException {
        try {
            conexion.close();
            System.out.println("Se desconecto de la base de datos");

        } catch (Exception e) {
            conexion.close();
        } finally {
            conexion.close();
        }
    }

    public static Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

}
