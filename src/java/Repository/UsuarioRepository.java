package Repository;

import java.sql.*;
import Modelo.Usuario;

public class UsuarioRepository {

    public boolean guardar(Usuario user) {
        String sql = "INSERT INTO usuario (nombre, correo, contrasena, telefono, id_rol, id_universidad) VALUES (?, ?, ?, ?, 1, 1)";
        
        try (Connection con = Conection_DB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getCorreo());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getTelefono());

            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }
}