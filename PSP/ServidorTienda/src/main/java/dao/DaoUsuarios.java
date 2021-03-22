package dao;

import dto.Usuario;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Log4j2
public class DaoUsuarios {
    private static final String QUERY_COMPROBAR_USUARIO =
            "select * from usuario" +
                    " where user=? and password=?";


    public Usuario comprobarUsuario(String userNombre, String password) {
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_COMPROBAR_USUARIO);

            stmt.setString(1, userNombre);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(rs.getString("user"),
                        rs.getString("password"));
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            db.cerrarResultSet(rs);
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return usuario;
    }



}
