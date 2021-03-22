package dao.implDao;

import dao.DBConnection;
import dao.DaoArticulos;
import dao.modelo.Articulo;
import dao.modelo.TipoArticulo;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoArticulosImpl implements DaoArticulos {
    private static final String QUERY_LISTA_ARTICULOS =
            "select * from articulos";

    private static final String QUERY_BORRAR_ARTICULO =
            "delete from articulos where id=?";

    private static final String QUERY_INSERTAR_ARTICULO =
            "insert into articulos (id,titular,descripcion,id_periodico,id_tipo,id_autor) values(?,?,?,?,?,?)";

    private static final String QUERY_ACTUALIZAR_ARTICULO =
            "update articulos set titular=?, descripcion=?, id_periodico=?, id_tipo=?, id_autor=? where id=?";

    private static final String QUERY_LISTA_TIPO_ARTICULOS =
            "select * from tipos_articulo";

    private static final String QUERY_LISTA_ARTICULOS_DE_UN_PERIODICO =
            "select * from articulos where id_periodico=?";

    @Override
    public List<Articulo> getAllArticulos() {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Articulo articulo = null;
        List<Articulo> articulos = new ArrayList<>();
        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_LISTA_ARTICULOS);

            rs = stmt.executeQuery();

            while (rs.next()) {
                articulo = new Articulo(rs.getInt("id"),
                        rs.getString("titular"),
                        rs.getString("descripcion"),
                        rs.getInt("id_periodico"),
                        rs.getInt("id_tipo"),
                        rs.getInt("id_autor"));
                articulos.add(articulo);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            db.cerrarResultSet(rs);
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return articulos;
    }

    @Override
    public int deleteArticulo(Articulo articulo) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = 0;
        try {
            con = db.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement
                    (QUERY_BORRAR_ARTICULO,
                            Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, articulo.getIdArticulo());


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
    public Articulo insertarArticulo(Articulo articulo) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_INSERTAR_ARTICULO,
                            Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, articulo.getIdArticulo());
            stmt.setString(2, articulo.getTitular());
            stmt.setString(3, articulo.getDescripcion());
            stmt.setInt(4, articulo.getIdPeriodico());
            stmt.setInt(5, articulo.getIdTipoArticulo());
            stmt.setInt(6, articulo.getIdAutor());
            stmt.executeUpdate();
            rs=stmt.getGeneratedKeys();
            if (rs.next()){
                articulo.setIdArticulo(rs.getInt(1));
            }


        } catch (Exception e) {
            log.error(e.getMessage());

        } finally {
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return articulo;
    }

    @Override
    public int actualizarArticulo(Articulo articulo) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = 0;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_ACTUALIZAR_ARTICULO);

            stmt.setString(1, articulo.getTitular());
            stmt.setString(2, articulo.getDescripcion());
            stmt.setInt(3, articulo.getIdPeriodico());
            stmt.setInt(4, articulo.getIdTipoArticulo());
            stmt.setInt(5, articulo.getIdAutor());
            stmt.setInt(6, articulo.getIdArticulo());

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
    public List<TipoArticulo> getTiposArticulos() {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TipoArticulo tipoArticulo= null;
        List<TipoArticulo> tiposArticulos = new ArrayList<>();
        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_LISTA_TIPO_ARTICULOS);

            rs = stmt.executeQuery();

            while (rs.next()) {
                tipoArticulo = new TipoArticulo(rs.getInt("id"),
                        rs.getString("tipo"));
                tiposArticulos.add(tipoArticulo);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            db.cerrarResultSet(rs);
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return tiposArticulos;
    }

    @Override
    public List<Articulo> getArticulosDeUnPeriodico(int idPeriodico){
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Articulo articulo;
        List<Articulo> articulos = new ArrayList<>();
        try {
            con = db.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement
                    (QUERY_LISTA_ARTICULOS_DE_UN_PERIODICO);
            stmt.setInt(1,idPeriodico );
            rs = stmt.executeQuery();

            while (rs.next()) {
                articulo = new Articulo(rs.getInt("id"),
                        rs.getString("titular"),
                        rs.getString("descripcion"),
                        rs.getInt("id_periodico"),
                        rs.getInt("id_tipo"),
                        rs.getInt("id_autor"));
                articulos.add(articulo);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            db.rollbackCon(con);
        } finally {
            db.cerrarResultSet(rs);
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return articulos;
    }
}
