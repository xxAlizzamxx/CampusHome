package Controller;

import Modelo.Usuario;         
import Repository.UsuarioRepository; 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class FormularioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. CAPA DE ENTIDAD
    
        Usuario user = new Usuario();
        user.setNombre(request.getParameter("nombre"));
        user.setCorreo(request.getParameter("correo"));
        user.setPassword(request.getParameter("password"));
        user.setTelefono(request.getParameter("telefono"));

        // 2. CAPA DE PERSISTENCIA 
      
        UsuarioRepository repo = new UsuarioRepository();
        boolean guardadoExitoso = repo.guardar(user);

        // 3. CAPA DE NEGOCIO 
        
        if (guardadoExitoso) {
            response.sendRedirect("formulario.html?status=success");
        } else {
            response.sendRedirect("formulario.html?status=error_sql");
        }
    }
}