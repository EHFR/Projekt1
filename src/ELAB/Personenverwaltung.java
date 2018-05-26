package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Personenverwaltung {
    private ArrayList<Person> personen;

    public Personenverwaltung() {
        personen = new ArrayList<>();
        this.reloadPersonen();
    }

    private void reloadPersonen() {
        Db db = new Db();

        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Personen");
            while (rs.next()) {
                Person p = new Person(rs.getInt("PersonID"), rs.getString("PersonName"),
                        rs.getString("PersonAdresse"), rs.getString("PersonTel"),
                        rs.getString("PersonEmail"), rs.getString("Type"));
                p.setZeitstempel(rs.getTimestamp("timestamp"));
                personen.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error while reading Database!");
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public ArrayList<Person> getPersonen() {
        return personen;
    }

    public void addPerson(String personName, String personAdresse, String personTel, String personEmail) {
    
    		Db db = new Db();
    	 
    		String sql = "INSERT INTO (PersonName, PersonAdresse, PersonTel, PersonEmail) "
    				+ "VALUES ('" + personName + "','" + personAdresse + "','" + personTel + "','" + personEmail + "'"; 
    		try {
				db.updateQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
    		
    }

    public void removePerson(int id) 
    {
    	Db db = new Db();
    	String sql = "DELETE FROM Personen WHERE PersonID = " + id + "" ;
    	
    	try {
			db.updateQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void updatePerson(int id,String personID, String personName, String personAdresse, String personTel, String personEmail)
    {
    	Db db = new Db();
    	String sql = "UPDATE Personen SET PersonName = '" + personName + "', PersonAdresse = '" + personAdresse 
    			+ "', PersonTel = '" + personTel + "', PersonEmail '" + personEmail + "' WHERE PersonID = " + id + "";
    	try {
			db.updateQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    

    // Methoden zum Bearbeiten von Personen zB. Namen ändern und sowas... hier wird später mit der GUI drauf zugegriffen
}
