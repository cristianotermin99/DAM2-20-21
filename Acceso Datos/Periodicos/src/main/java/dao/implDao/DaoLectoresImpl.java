package dao.implDao;

import dao.DBConnection;
import dao.DaoLectores;
import dao.modelo.Lector;
import dao.modelo.TipoUsuario;
import dao.modelo.Usuario;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.time.LocalDate;

@Log4j2
public class DaoLectoresImpl implements DaoLectores {

    private static final String QUERY_GET_LECTOR =
            "select * from lectores l inner join usuarios u on l.id=u.id " +
                    "inner join tipos_usuario tu on u.id_tipo_usuario=tu.id_tipo_usuario" +
                    " where u.id=?";

    private static final String QUERY_UPDATE_DATOS_LECTOR =
            "update lectores set nombre=?, fechaNacimiento=? where id=?";

    private static String QUERY_INSERTAR_LECTOR = " insert into lectores " +
            "(id, nombre, fechaNacimiento) VALUES (?,?,?)";

    private static final String QUERY_AÑADIR_USUARIO =
            "insert into usuarios (user,password,mail,id_tipo_usuario) values(?,?,?,?)";

    private static final String QUERY_DELETE_LECTOR =
            "delete from lectores where id=?";

    private static final String QUERY_DELETE_USER =
            "delete from usuarios where id=?";

    private static final String QUERY_DELETE_SUSCRIPCION =
            "delete from suscripciones where id_lector=?";

    private static final String QUERY_DELETE_ARTICULO_LEIDO =
            "delete from articulos_leidos where id_lector=?";


    @Override
    public Lector getLector(Usuario user) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Lector lector=null;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_GET_LECTOR);

            stmt.setInt(1, user.getIdUsuario());


            rs = stmt.executeQuery();

            if (rs.next()) {
                lector = new Lector(rs.getInt("u.id"),
                        rs.getString("user"),
                        rs.getString("password"),
                        rs.getInt("primera_vez"),
                        rs.getString("mail"),
                        new TipoUsuario(
                                rs.getInt("id_tipo_usuario"),
                                rs.getString("tipo"),
                                rs.getString("descripcion")),
                        rs.getInt("l.id"),
                        rs.getString("nombre"),
                        rs.getDate("fechaNacimiento").toLocalDate());
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            db.cerrarResultSet(rs);
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return lector;
    }

    @Override
    public int actualizarDatosLector(Usuario user, String nombre, LocalDate date) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = 0;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_UPDATE_DATOS_LECTOR);

            stmt.setString(1, nombre);
            stmt.setDate(2, Date.valueOf(date));
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
    public Lector addLector(Lector lector, String contraseña) {

        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement
                    (QUERY_AÑADIR_USUARIO,
                            Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, lector.getUser());
            stmt.setString(2, contraseña);
            stmt.setString(3, lector.getMail());
            stmt.setInt(4, lector.getTipoUsuario().getIdTipoUsuario());

             stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                lector.setIdLector(rs.getInt(1));
                lector.setIdUsuario(rs.getInt(1));
            }

            stmt = con.prepareStatement
                    (QUERY_INSERTAR_LECTOR,
                            Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, lector.getIdLector());
            stmt.setString(2, lector.getNombre());
            stmt.setDate(3,
                    java.sql.Date.valueOf(lector.getBirth()));
            stmt.executeUpdate();
            con.commit();

        } catch (Exception e) {
            log.error(e.getMessage());
            db.rollbackCon(con);
            return null;
        } finally {
            db.cerrarResultSet(rs);
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return lector;
    }

    @Override
    public int deleteLector(Lector usuario) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int filas = 0;
        try {
            con = db.getConnection();
            con.setAutoCommit(false);

            stmt = con.prepareStatement
                    (QUERY_DELETE_ARTICULO_LEIDO);

            stmt.setInt(1, usuario.getIdLector());
            filas = stmt.executeUpdate();


            stmt = con.prepareStatement
                    (QUERY_DELETE_SUSCRIPCION);

            stmt.setInt(1, usuario.getIdLector());
            filas = stmt.executeUpdate();


            stmt = con.prepareStatement
                    (QUERY_DELETE_LECTOR);

            stmt.setInt(1, usuario.getIdLector());
            filas = stmt.executeUpdate();

            stmt = con.prepareStatement
                    (QUERY_DELETE_USER);
            stmt.setInt(1, usuario.getIdLector());

            filas = stmt.executeUpdate();

            con.commit();
        } catch (Exception e) {
            log.error(e.getMessage());
            db.rollbackCon(con);
        } finally {
            db.cerrarResultSet(rs);
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }

        return filas;
    }
}
