package dao;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Log4j2
public class DaoProductos {
    private static final String QUERY_LISTA_PRODUCTOS =
            "select * from producto";

    public List<String> listaProductos() {
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> productos=new ArrayList<>();
        try {
            con = db.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement
                    (QUERY_LISTA_PRODUCTOS,
                            Statement.RETURN_GENERATED_KEYS);


            rs = stmt.executeQuery();

            while (rs.next()){
                productos.add(rs.getString("nombreProducto"));

            }

        } catch (Exception e) {
         log.error(e.getMessage());
            db.rollbackCon(con);
        } finally {
            db.cerrarResultSet(rs);
            db.cerrarStatement(stmt);
            db.cerrarConexion(con);
        }
        return productos;
    }
}
