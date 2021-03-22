package dao.implDao;

import config.Configuration;
import dao.DBConnection;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class DBConnectionImpl implements DBConnection {

    public DBConnectionImpl() {

    }

    @Override
    public Connection getConnection() throws Exception {

        Connection connection = null;

        // solo hace falta en web.
        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(
                Configuration.getInstance().getRuta(),
                Configuration.getInstance().getUser(),
                Configuration.getInstance().getPassword());

        //    connection = DBConnectionPool.getInstance().getConnection();
        return connection;
    }

    @Override
    public void cerrarConexion(Connection connection) {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException ex) {
            log.error("no se ha podido cerrar conexion", ex);
        }
    }
    @Override
    public void cerrarStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            log.error("", ex);
        }
    }
    @Override
    public void cerrarResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void rollbackCon(Connection con) {
        try {
            if (con != null)
                con.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
