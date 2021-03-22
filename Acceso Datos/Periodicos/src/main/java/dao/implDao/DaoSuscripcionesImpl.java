package dao.implDao;

import dao.DBConnection;
import dao.DaoSuscripciones;
import dao.modelo.Lector;
import dao.modelo.Suscripcion;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoSuscripcionesImpl implements DaoSuscripciones {

    private static final String QUERY_INSERTAR_SUSCRIPCION =
            "insert into suscripciones (id_lector,id_periodico,fecha_inicio,fecha_baja) values(?,?,?,?)";

    private static final String QUERY_BORRAR_SUSCRIPCION =
            "update suscripciones set fecha_baja=? where id=?";

    private static final String QUERY_LISTA_SUSCRIPCIONES_DE_UN_LECTOR =
            "select * from suscripciones " +
                    "where id_lector=? and fecha_baja is null";


    @Override
    public int insertarSuscripcion(Suscripcion suscripcion) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = 0;
        try {
            con = db.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement
                    (QUERY_INSERTAR_SUSCRIPCION);


            stmt.setInt(1, suscripcion.getIdLector());
            stmt.setInt(2, suscripcion.getIdPeriodico());
            stmt.setDate(3, Date.valueOf(suscripcion.getFechaInicio()));
            stmt.setDate(4, null);

            filas = stmt.executeUpdate();

        } catch (Exception e) {
            log.error(e.getMessage());
            db.rollbackCon(con);

        } finally {
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return filas;
    }

    @Override
    public int deleteSuscripcion(Suscripcion suscripcion) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = 0;
        try {
            con = db.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement
                    (QUERY_BORRAR_SUSCRIPCION);
            stmt.setDate(1, Date.valueOf(suscripcion.getFechaBaja()));
            stmt.setInt(2, suscripcion.getIdSuscripcion());

            filas = stmt.executeUpdate();


        } catch (Exception e) {
            log.error(e.getMessage());
            db.rollbackCon(con);

        } finally {
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return filas;
    }

    @Override
    public List<Suscripcion> getSuscripcionesDeUnLector(Lector lector) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Suscripcion suscripcion;
        List<Suscripcion> suscripciones = new ArrayList<>();
        try {
            con = db.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement
                    (QUERY_LISTA_SUSCRIPCIONES_DE_UN_LECTOR);

            stmt.setInt(1, lector.getIdUsuario());
            rs = stmt.executeQuery();

            while (rs.next()) {
                suscripcion = new Suscripcion(rs.getInt("id"),
                        rs.getInt("id_lector"),
                        rs.getInt("id_periodico"),
                        rs.getDate("fecha_inicio").toLocalDate());
                suscripciones.add(suscripcion);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            db.rollbackCon(con);
        } finally {
            db.cerrarResultSet(rs);
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return suscripciones;
    }
}
