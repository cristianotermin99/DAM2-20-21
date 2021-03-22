package dao.implDao;

import dao.DBConnection;
import dao.DaoArticulosLeidos;
import dao.modelo.ArticuloLeido;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Log4j2
public class DaoArticulosLeidosImpl implements DaoArticulosLeidos {
    private static final String QUERY_INSERTAR_ARTICULO_LEIDO =
            "insert into articulos_leidos (id_lector,id_articulo,rating) values(?,?,?)";

    private static final String QUERY_COMPROBAR_ARTICULO_LEIDO =
            "select * from articulos_leidos where id_articulo = ? and id_lector = ?";

    private static final String QUERY_UPDATE_ARTICULO_LEIDO =
            "update articulos_leidos set rating=? where id=?";


    @Override
    public int leerArticulo0(ArticuloLeido articuloLeido) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = 0;
        try {
            con = db.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement
                    (QUERY_INSERTAR_ARTICULO_LEIDO);

            stmt.setInt(1, articuloLeido.getIdLector());
            stmt.setInt(2, articuloLeido.getIdArticulo());
            stmt.setInt(3, articuloLeido.getRating());

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
    public int comprobarArticuloLeido(ArticuloLeido articuloLeido) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_COMPROBAR_ARTICULO_LEIDO);

            stmt.setInt(1, articuloLeido.getIdArticulo());
            stmt.setInt(2, articuloLeido.getIdLector());

            rs = stmt.executeQuery();

            if (rs.next()) {
                return 1;
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return 3;
        } finally {
            db.cerrarResultSet(rs);
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return 0;
    }

    @Override
    public ArticuloLeido getArticuloLeido(ArticuloLeido articuloLeido){
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_COMPROBAR_ARTICULO_LEIDO,
                            Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, articuloLeido.getIdArticulo());
            stmt.setInt(2, articuloLeido.getIdLector());
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                articuloLeido.setId(rs.getInt("id"));
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            db.cerrarResultSet(rs);
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return articuloLeido;
    }

    @Override
    public int actualizarRatingArticulo(ArticuloLeido articuloLeido){
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = 0;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_UPDATE_ARTICULO_LEIDO);

            stmt.setInt(1, articuloLeido.getRating());
            stmt.setInt(2, articuloLeido.getId());

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
