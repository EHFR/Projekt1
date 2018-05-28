package ELAB;

import java.sql.*;


public class Db {
    private String db_url = "jdbc:sqlite:ELAB.db";
    private Connection conn = null;

    ResultSet exequteQuery(String sqlquery) throws SQLException {
        String sql = sqlquery;
        PreparedStatement stmt = null;
        DriverManager.registerDriver(new org.sqlite.JDBC());
        this.conn = DriverManager.getConnection(this.db_url);
        stmt = this.conn.prepareStatement(sql);
        return stmt.executeQuery();
    }
    
    void updateQuery(String sqlquery) throws SQLException {
        String sql = sqlquery;
        PreparedStatement stmt = null;
        DriverManager.registerDriver(new org.sqlite.JDBC());
        this.conn = DriverManager.getConnection(this.db_url);
        stmt = this.conn.prepareStatement(sql);
        stmt.executeUpdate();
    }


    void close() {
        try {
            if (this.conn != null)
                this.conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

/** Test
 public static void main(String[] args) {
 Db db = new Db();

 try {
 ResultSet rs = db.connect("Personen");
 while (rs.next()) {
 Person p = new Person(rs.getInt("PersonID"), rs.getString("PersonName"),
 rs.getString("PersonAdresse"), rs.getString("PersonTel"), rs.getString("PersonEmail"));
 System.out.println(p.toString());
 }
 } catch (SQLException e) {
 System.out.println("Error while reading Database!");
 e.printStackTrace();
 } finally {
 db.close();
 }
 }
 */
}


//
//import java.sql.*;
//import java.util.Properties;
//
//
//public class Db {
//	
//	String db_url = "jdbc:sqlite:C:\\Users\\Emre Arduc\\eclipse-workspace\\ELAB\\ELAB.db";
//	Connection conn = null;
//	PreparedStatement stmt = null;
//	
//	public void connectDB()
//	{
//		try
//		{
//			DriverManager.registerDriver(new org.sqlite.JDBC());
//			conn = DriverManager.getConnection(db_url);
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//	public void insert(String name, String adresse, String tel)
//	{
//		String sql = "INSERT INTO Personen (PersonID, PersonName, PersonAdresse, PersonTel"
//				+ " VALUES (" + name + ", " + adresse + ", " + tel + ")";
//		
//		try 
//		{
//			stmt.executeUpdate(sql);
//		} 
//		catch (SQLException e) 
//		{
//		e.printStackTrace();
//		}
//		
//	}
//	
//	public void selectDB() {
//		
//	String sql = "SELECT * FROM Personen";
//	try
//	{
//		ResultSet rs = stmt.executeQuery();
//		
//		while(rs.next())
//		{
//			int id = rs.getInt("PersonID");
//			String name = rs.getString("PersonName");
//			String adresse = rs.getString("PersonAdresse");
//			String tel = rs.getString("PersonTel");
//			
//			System.out.print("ID: " + id);
//	        System.out.print(", Name: " + name);
//	        System.out.print(", Adresse: " + adresse);
//	        System.out.println(", Tel: " + tel);
//		}
//	}
//	catch(SQLException se)
//	{
//		se.printStackTrace();
//	}
//	catch(Exception e)
//	{
//		e.printStackTrace();
//	}
//	finally
//	{
//		try
//		{
//			if(conn!=null)
//				conn.close();
//		}
//		catch(SQLException se)
//		{
//			se.printStackTrace();
//		}
//	}
//	}
//}
//	
//


