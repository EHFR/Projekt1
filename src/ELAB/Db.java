package ELAB;

import java.sql.*;


class Db {
    private String db_url = "jdbc:sqlite:ELAB.db";
    private Connection conn = null;

    ResultSet exequteQuery(String sqlquery) throws SQLException {
        PreparedStatement stmt;
        DriverManager.registerDriver(new org.sqlite.JDBC());
        this.conn = DriverManager.getConnection(this.db_url);
        stmt = this.conn.prepareStatement(sqlquery);
        return stmt.executeQuery();
    }

    void updateQuery(String sqlquery) throws SQLException {
        PreparedStatement stmt;
        DriverManager.registerDriver(new org.sqlite.JDBC());
        this.conn = DriverManager.getConnection(this.db_url);
        stmt = this.conn.prepareStatement(sqlquery);
        stmt.executeUpdate();
        stmt.close();
        close();
    }

    Connection dataSource() throws SQLException {
        DriverManager.registerDriver(new org.sqlite.JDBC());
        this.conn = DriverManager.getConnection(this.db_url);
        return conn;
    }

    void close() {
        try {
            if (this.conn != null)
                this.conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}