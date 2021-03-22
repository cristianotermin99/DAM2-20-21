package dao.implDao;

import dao.DBConnection;
import dao.DaoAutores;
import dao.modelo.Autor;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoAutoresImpl implements DaoAutores {

    private static final String QUERY_INSERTAR_AUTOR =
            "insert into autores (nombre,apellidos) values(?,?)";

    private static final String QUERY_LISTA_AUTORES =
            "select * from autores";

    @Override
    public Autor insertarAutor(Autor autor) {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_INSERTAR_AUTOR,
                            Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getApellidos());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                autor.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            log.error(e.getMessage());

        } finally {
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return autor;
    }

    @Override
    public List<Autor> getAutores() {
        DBConnection db = new DBConnectionImpl();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Autor> autores = new ArrayList<>();
        Autor autor = null;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement
                    (QUERY_LISTA_AUTORES);

            rs = stmt.executeQuery();

            while (rs.next()) {
                autor = new Autor(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"));
                autores.add(autor);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            db.cerrarResultSet(rs);
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return autores;
    }
}
