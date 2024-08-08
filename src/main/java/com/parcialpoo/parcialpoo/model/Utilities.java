package com.parcialpoo.parcialpoo.model;

import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Utilities {

    public static void Insertar(Conexion conectar, String menu, int precio) {
        try {
            Connection conexion = conectar.conectar();
            PreparedStatement insertar = conexion.prepareStatement("Insert into menus values(?,?,?)");
            insertar.setString(1, "0");
            insertar.setString(2, menu);
            insertar.setInt(3, precio);

            insertar.executeUpdate();
            System.out.println("Datos registrados");
            conectar.cerrarConexion();

        } catch (Exception e) {
            System.out.println("Datos no registrados");
        }
    }

    public static void Eliminar(Conexion conectar, String id) {
        try {
            if (!isEmpty(conectar)) {

                Connection conexion = conectar.conectar();
                PreparedStatement eliminar = conexion.prepareStatement("delete from menus WHERE id = ?");
                eliminar.setString(1, id);
                eliminar.executeUpdate();
                conectar.cerrarConexion();
                System.out.println("Se ha eliminado el dato");

            } else {
                System.out.println("No hay datos para eliminar");
            }
        } catch (Exception e) {
            System.out.println("Datos no eliminados");
        }
    }

    public static void Consultar(Conexion conectar) {
        try {
            Connection conexion = conectar.conectar();
            PreparedStatement seleccionar = conexion.prepareStatement("SELECT * FROM menus");

            ResultSet consulta = seleccionar.executeQuery();

            if (!isEmpty(conectar)) {
                while (consulta.next()) {
                    System.out.println("------------------------------------------------------");
                    System.out.println("id: " + consulta.getString(1));
                    System.out.println("name: " + consulta.getString(2));
                    System.out.println("gmail: " + consulta.getString(3));
                    System.out.println("password: " + consulta.getString(4));
                    System.out.println("------------------------------------------------------");
                }

                conectar.cerrarConexion();
            } else {
                System.out.println("No hay datos para consultar");
            }

        } catch (Exception e) {
            System.out.println("Datos no registrados");
        }
    }

    public static int Ultimo(Conexion conectar) {
        try {
            if (!isEmpty(conectar)) {
                Connection conexion = conectar.conectar();
                PreparedStatement buscar = conexion.prepareStatement("SELECT * FROM menus ORDER BY id DESC");
                ResultSet consulta = buscar.executeQuery();

                while (consulta.next()) {
                    System.out.println(consulta.getString(1));
                    return consulta.getInt(1);
                }

                conectar.cerrarConexion();

                System.out.println("Se ha encontrado el dato");

                return 0;

            } else {
                System.out.println("No hay datos para buscar");
            }
        } catch (Exception e) {
            System.out.println("Datos no encontrados");
        }

        return 0;
    }

    public static boolean isEmpty(Conexion conectar) {
        try {
            Connection conexion = conectar.conectar();
            PreparedStatement seleccionar = conexion.prepareStatement("SELECT * FROM menus");

            ResultSet consulta = seleccionar.executeQuery();

            return !consulta.next();

        } catch (Exception e) {
            System.out.println("Error al saber si la tabla esta vacia");
        }
        return true;
    }

    public static boolean Buscar(Conexion conectar, String id) {
        try {
            if (!isEmpty(conectar)) {
                Connection conexion = conectar.conectar();
                PreparedStatement buscar = conexion.prepareStatement("SELECT * FROM menus WHERE id = " + id);
                ResultSet consulta = buscar.executeQuery();

                while (consulta.next()) {
                    System.out.println("------------------------------------------------------");
                    System.out.println("id: " + consulta.getString(1));
                    System.out.println("name: " + consulta.getString(2));
                    System.out.println("gmail: " + consulta.getString(3));
                    System.out.println("password: " + consulta.getString(4));
                    System.out.println("------------------------------------------------------");
                }

                conectar.cerrarConexion();

                System.out.println("Se ha encontrado el dato");

                return consulta.next();

            } else {
                System.out.println("No hay datos para buscar");
            }
        } catch (Exception e) {
            System.out.println("Datos no encontrados");
        }

        return false;

    }

    public static void Modificar(Conexion conectar, String id, String name, String email, String password) {
        try {
            if (!isEmpty(conectar)) {
                Connection conexion = conectar.conectar();
                PreparedStatement modificar = conexion.prepareStatement("UPDATE menus SET name = ?, email = ?, password = ? WHERE id = " + id);
                modificar.setString(1, name);
                modificar.setString(2, email);
                modificar.setString(3, password);
                modificar.executeUpdate();
                System.out.println("Se ha actualizado el dato");
            } else {
                System.out.println("No hay datos para modificar");
            }
        } catch (Exception e) {
            System.out.println("Datos no modificados");
        }
    }

}
