/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ch;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Unimagdalena
 */

public class Demo extends HttpServlet {


static final String DB_URL = "jdbc:postgresql://localhost:5432/Prueba";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "123456";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        int calificacion = Integer.parseInt(request.getParameter("calificacion"));

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //String sql = "INSERT INTO monitores (nombre, correo, calificacion) VALUES (?, ?, ?)";
            String sql = "INSERT INTO Monitores (nombre, email, calificacion_promedio) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, correo);
            stmt.setInt(3, calificacion);

            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                response.getWriter().println("Monitor guardado exitosamente.");
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error al guardar el monitor."+e.getMessage());
        }
    }
}
