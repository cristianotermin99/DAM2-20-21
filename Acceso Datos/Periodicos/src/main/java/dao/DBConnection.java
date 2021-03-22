package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface DBConnection {
    Connection getConnection() throws Exception;

    void cerrarConexion(Connection connection);

    void cerrarStatement(Statement stmt);

    void cerrarResultSet(ResultSet rs);

    void rollbackCon(Connection con);
}
