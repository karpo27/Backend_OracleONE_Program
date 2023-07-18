package com.alura.jdbc.pruebas;

import java.sql.*;

public class PruebaConexion {

    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC",
                "root",
                "Julian27!");

        System.out.println("Cerrando la conexión");

        con.close();
    }

}
