package Controller;

import Repository.Conection_DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListarUsuariosServlet")
public class ListarUsuariosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try (Connection con = Conection_DB.getConexion()) {
            String sql = "SELECT * FROM usuario ORDER BY id_usuario ASC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Listado de Usuarios - Sistema Monolítico</title>");
            out.println("<style>");
            out.println("    body { font-family: sans-serif; margin: 40px; color: #333; }");
            out.println("    h2 { border-bottom: 2px solid #333; padding-bottom: 10px; }");
            out.println("    table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            out.println("    th, td { border: 1px solid #000; padding: 10px; text-align: left; }");
            out.println("    th { background-color: #f2f2f2; font-weight: bold; }");
            out.println("    .btn-volver { margin-top: 20px; display: inline-block; padding: 5px 10px; background-color: #e1e1e1; color: black; text-decoration: none; border: 1px solid #999; }");
            out.println("    .btn-volver:hover { background-color: #ccc; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h2>Base de Datos: Usuarios Registrados</h2>");
            
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Correo Electrónico</th>");
            out.println("<th>Teléfono</th>");
            out.println("<th>Rol</th>");
            out.println("<th>Universidad</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id_usuario") + "</td>");
                out.println("<td>" + rs.getString("nombre") + "</td>");
                out.println("<td>" + rs.getString("correo") + "</td>");
                out.println("<td>" + rs.getString("telefono") + "</td>");
                out.println("<td>" + rs.getInt("id_rol") + "</td>");
                out.println("<td>" + rs.getInt("id_universidad") + "</td>");
                out.println("</tr>");
            }
            
            out.println("</tbody>");
            out.println("</table>");
            
            out.println("<br>");
            out.println("<a href='formulario.html' class='btn-volver'>Volver al Formulario</a>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            out.println("<html><body><h3>Error al recuperar datos:</h3><p>" + e.getMessage() + "</p></body></html>");
        }
    }
}