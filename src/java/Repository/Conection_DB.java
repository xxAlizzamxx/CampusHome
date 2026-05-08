/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author slced
 */
public class Conection_DB {
    private static final String URL = "jdbc:postgresql://localhost:5432/Sistema_Habitaciones";
    private static final String USER = "postgres";
    private static final String PASS = "Naibaf722";
 
    public static Connection getConexion() {
        Connection conexion = null;
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en getConexion: " + e.getMessage());
        }
        return conexion;
    }


    public static void main(String[] args) {
        System.out.println("Iniciando prueba de conexion...");
        
        Connection c = getConexion();

        if (c != null) {
            System.out.println("Conexion establecida con Postgres.");

            
            try {
                c.close();
            } catch (SQLException e) {
            }
        } else {
            System.out.println("ERROR: No se pudo conectar.");
            System.out.println("Revise el puerto, usuario o contraseña.");
        }
    }
}