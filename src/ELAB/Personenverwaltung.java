package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Personenverwaltung {
    private ArrayList<Person> personen;
    private Timestamp timestamp;

    public Personenverwaltung() {
        personen = new ArrayList<>();
        this.reloadPersonen();
    }

    private Person getPersonByID(int id) {
		for (Person personen : personen) {
			if (personen.getId() == id) {
				return personen;
			}
		}

		return null;
	}

    
    
    private void reloadPersonen() {
        Db db = new Db();
        this.personen.clear();
        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Personen");
            while (rs.next()) {
                Person p = new Person(rs.getInt("PersonID"), rs.getString("PersonName"),
                        rs.getString("PersonAdresse"), rs.getString("PersonTel"),
                        rs.getString("PersonEmail"), rs.getString("Type"), rs.getString("Password"));
                p.setZeitstempel(rs.getTimestamp("timestamp"));
                this.personen.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error while reading Database!");
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public ArrayList<Person> getPersonen() {
        this.reloadPersonen();
        return personen;
    }

    public void addPerson(String personName, String personAdresse, String personTel, String personEmail, String type, String passwort) {

        Db db = new Db();
        timestamp = new Timestamp(System.currentTimeMillis());

        String sql = "INSERT INTO Personen (PersonName, PersonAdresse, PersonTel, PersonEmail, timestamp, Type, Password) "
                + "VALUES ('" + personName + "','" + personAdresse + "','" + personTel + "','" + personEmail + "'," + timestamp + ",'" + type + "','" + passwort + "')";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removePerson(int id) {
        Db db = new Db();
        Person p = this.getPersonByID(id);
        String sql = "DELETE FROM Personen WHERE PersonID = " + p.getId() + " ";

        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePerson(int id, String personName, String personAdresse, String personTel, String personEmail, String type, String passwort) {
        Db db = new Db();
        Person p = this.getPersonByID(id);
        String sql = "UPDATE Personen SET PersonName = '" + p.getName() + "', PersonAdresse = '" + p.getAdresse()
                + "', PersonTel = '" + p.getTelefonnr() + "', PersonEmail = '" + p.getEmail() + "', Type = '" + p.getType() + "', Password = '" + p.getPasswort() + "' "
                + "WHERE PersonID = " + p.getId() + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean personAlreadyExists(String name) {
        boolean check = false;
        Db db = new Db();
        try {
            ResultSet rs = db.exequteQuery("SELECT PersonName FROM Personen WHERE PersonName = '" + name + "'");
            while (rs.next()) {
                if (rs.getString("PersonName").equals(name)) {
                    check = true;
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return check;
    }

}
