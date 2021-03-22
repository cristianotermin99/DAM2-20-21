package dao.implDao;

import dao.DBConnection;
import dao.DaoPeriodicos;
import dao.modelo.Periodico;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoPeriodicosImpl implements DaoPeriodicos {

    private static final String QUERY_LISTA_PERIODICOS =
            "select * from periodicos;";

    private static final String QUERY_BORRAR_PERIODICO =
            "delete from periodicos where id=?";

    private static final String QUERY_ACTUALIZAR_PERIODICO =
            "update periodicos set nombre=?,director=?,precio_diario=? where id=?";

    private static final String QUERY_ADD_PERIODICO =
            "insert into periodicos (id,nombre,director,id_administrador,precio_diario) values(?,?,?,?,?)";

    private static final String QUERY_DELETE_USER =
            "delete from usuarios where id=?";


    @Override
    public List<Periodico> getAllPeriodicos() {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Periodico periodico;
        List<Periodico> periodicos = new ArrayList<>();
        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_LISTA_PERIODICOS);

            rs = stmt.executeQuery();

            while (rs.next()) {
                periodico = new Periodico(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio_diario"),
                        rs.getString("director"),
                        rs.getInt("id_administrador"));
                periodicos.add(periodico);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            db.cerrarResultSet(rs);
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return periodicos;
    }

    @Override
    public int deletePeriodico(Periodico periodico) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = 0;
        try {
            con = db.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement
                    (QUERY_BORRAR_PERIODICO);

            stmt.setInt(1, periodico.getId());

            filas = stmt.executeUpdate();

            stmt = con.prepareStatement
                    (QUERY_DELETE_USER);
            stmt.setInt(1, periodico.getId_administrador());

            filas = stmt.executeUpdate();
            con.commit();

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
    public int actualizarDatosPeriodico(Periodico periodico) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = 0;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_ACTUALIZAR_PERIODICO);

            stmt.setString(1, periodico.getNombre());
            stmt.setString(2, periodico.getDirector());
            stmt.setDouble(3, periodico.getPrecio());
            stmt.setInt(4, periodico.getId());

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
    public int insertarPeriodico(Periodico periodico) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = 0;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_ADD_PERIODICO);

            stmt.setInt(1, periodico.getId());
            stmt.setString(2, periodico.getNombre());
            stmt.setString(3, periodico.getDirector());
            stmt.setInt(4, periodico.getId_administrador());
            stmt.setDouble(5, periodico.getPrecio());
            filas = stmt.executeUpdate();


        } catch (Exception e) {
            log.error(e.getMessage());

        } finally {
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return filas;
    }

}
