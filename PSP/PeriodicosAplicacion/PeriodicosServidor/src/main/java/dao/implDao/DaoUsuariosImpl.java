package dao.implDao;

import dao.DBConnection;
import dao.DaoUsuarios;
import dao.modelo.TipoUsuario;
import dao.modelo.Usuario;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoUsuariosImpl implements DaoUsuarios {
    private static final String QUERY_COMPROBAR_USUARIO =
            "select * from usuarios u inner join tipos_usuario tu on u.id_tipo_usuario = tu.id_tipo_usuario" +
                    " where u.user=? and u.password=?";

    private static final String QUERY_ACTUALIZAR_CONTRASEÑA =
            "update usuarios set password=?, primera_vez=? where id=?";

    private static final String QUERY_ACTUALIZAR_DATOS_USUARIO =
            "update usuarios set user=?, mail=? where id=?";

    private static final String QUERY_AÑADIR_USUARIO =
            "insert into usuarios (user,password,mail,id_tipo_usuario) values(?,?,?,?)";

    private static final String QUERY_LISTA_USUARIOS =
            "select * from usuarios u inner join tipos_usuario tu on u.id_tipo_usuario = tu.id_tipo_usuario";

    private static final String QUERY_DELETE_USER =
            "delete from usuarios where id=?";

    private static final String QUERY_LISTA_ADMINS =
            "select * from usuarios u inner join tipos_usuario tu on u.id_tipo_usuario = tu.id_tipo_usuario " +
                    "where tu.id_tipo_usuario=? or tu.id_tipo_usuario=?";


    @Override
    public Usuario comprobarUsuario(String userNombre, String password) {
        DBConnection db = new DBConnectionImpl();
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
                usuario = new Usuario(rs.getInt("id"),
                        rs.getString("user"),
                        rs.getString("password"),
                        rs.getInt("primera_vez"),
                        rs.getString("mail"),
                        new TipoUsuario(
                                rs.getInt("id_tipo_usuario"),
                                rs.getString("tipo"),
                                rs.getString("descripcion")
                        ));
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

    @Override
    public int actualizarContraseña(Usuario user, String contraseñaNueva) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = 0;

        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_ACTUALIZAR_CONTRASEÑA);

            stmt.setString(1, contraseñaNueva);
            stmt.setInt(2, 0);
            stmt.setInt(3, user.getIdUsuario());

            filas = stmt.executeUpdate();

        } catch (Exception e) {
            log.error(e.getMessage());

        } finally {
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return filas;
    }

    @Override
    public int actualizarDatosUsuario(Usuario user, String userName, String mail) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = 0;

        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_ACTUALIZAR_DATOS_USUARIO);

            stmt.setString(1, userName);
            stmt.setString(2, mail);
            stmt.setInt(3, user.getIdUsuario());

            filas = stmt.executeUpdate();

        } catch (Exception e) {
            log.error(e.getMessage());

        } finally {
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return filas;
    }

    @Override
    public Usuario insertarUsuario(Usuario user, String contraseña) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_AÑADIR_USUARIO,
                            Statement.RETURN_GENERATED_KEYS);


            stmt.setString(1, user.getUser());
            stmt.setString(2, contraseña);
            stmt.setString(3, user.getMail());
            stmt.setInt(4, user.getTipoUsuario().getIdTipoUsuario());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setIdUsuario(rs.getInt(1));
            }

        } catch (Exception e) {
            log.error(e.getMessage());

        } finally {
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return user;
    }

    @Override
    public List<Usuario> getAllUsers() {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario ;
        List<Usuario> allUsers = new ArrayList<>();

        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_LISTA_USUARIOS);

            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario = new Usuario(rs.getInt("id"),
                        rs.getString("user"),
                        rs.getString("password"),
                        rs.getInt("primera_vez"),
                        rs.getString("mail"),
                        new TipoUsuario(
                                rs.getInt("id_tipo_usuario"),
                                rs.getString("tipo"),
                                rs.getString("descripcion")
                        ));
                allUsers.add(usuario);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            db.cerrarResultSet(rs);
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return allUsers;
    }

    @Override
    public int deleteUser(Usuario usuario) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = 0;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_DELETE_USER);

            stmt.setInt(1, usuario.getIdUsuario());

            filas = stmt.executeUpdate();

        } catch (Exception e) {
            log.error(e.getMessage());

        } finally {
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return filas;
    }

    @Override
    public List<Usuario> getAdmins() {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> admins = new ArrayList<>();

        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_LISTA_ADMINS);
            stmt.setInt(1, 1);
            stmt.setInt(2, 2);
            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario = new Usuario(rs.getInt("id"),
                        rs.getString("user"),
                        rs.getString("password"),
                        rs.getInt("primera_vez"),
                        rs.getString("mail"),
                        new TipoUsuario(
                                rs.getInt("id_tipo_usuario"),
                                rs.getString("tipo"),
                                rs.getString("descripcion")
                        ));
                admins.add(usuario);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            db.cerrarResultSet(rs);
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return admins;
    }


}
