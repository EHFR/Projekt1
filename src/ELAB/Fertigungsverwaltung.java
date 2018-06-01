package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Fertigungsverwaltung {
    private ArrayList<Auftrag> auftraege;
    private Timestamp timestamp;

    public Fertigungsverwaltung() {
        auftraege = new ArrayList<>();
        this.reloadAuftraege();
    }

    //todo Hier muss auch Auftraggeber(Name:String) und Auftragbearbeiter(Name\n Name2...:String) geladen werden!
    //todo \n bedeutet neue Zeile!
    
    public ArrayList<Person> fillList(int id) {
    	Db db = new Db();
    	Personenverwaltung pw = new Personenverwaltung();
    	ArrayList<Person> personen = new ArrayList<Person>();
    	String sql = "SELECT * FROM AuftragPerson WHERE AuftragID = " + id + "";
    	try{
    	ResultSet rs = db.exequteQuery(sql);
    	while(rs.next()) {
    		int personID = rs.getInt("PersonID");
    		personen.add(pw.getPersonByID(personID));
    		}
    	} 
    	catch (SQLException e)
    	{
    		e.printStackTrace();
    	}
    	return personen;
   }
    
    
    private void reloadAuftraege() {
        Db db = new Db();
        Personenverwaltung pw = new Personenverwaltung();
        this.auftraege.clear();
        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Auftrag");
            while (rs.next()) {
            	int auftragID = rs.getInt("ID");
                Auftrag a = new Auftrag(rs.getInt("ID"), rs.getString("Titel"), rs.getString("FertigungsArt"),
                        rs.getString("DateiName"), rs.getString("DateiOrt"), rs.getFloat("Kosten"),
                        rs.getBoolean("angenommen"), rs.getTimestamp("statusZeitstempel_angenommen"),
                        rs.getBoolean("gefertigt"), rs.getTimestamp("statusZeitstempel_gefertigt"),
                        rs.getBoolean("kosten_kalkuliert"), rs.getTimestamp("statusZeitstempel_kosten_kalkuliert"),
                        rs.getBoolean("abgeholt"), rs.getTimestamp("statusZeitstempel_abgeholt"),
                        rs.getBoolean("abgerechnet"), rs.getTimestamp("statusZeitstempel_abgerechnet"),
                        rs.getBoolean("wartenAufMaterial"), rs.getTimestamp("statusZeitstempel_wartenAufMaterial"),
                        rs.getBoolean("fertigungFehlgeschlagen"),
                        rs.getTimestamp("statusZeitstempel_fertigungFehlgeschlagen"),pw.getPersonByID(rs.getInt("AuftraggeberID")),fillList(auftragID));
                a.setZeitstempel(rs.getTimestamp("ZeitStempel"));
                this.auftraege.add(a);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error while reading Database!");
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public ArrayList<Auftrag> getAuftraege() {
        this.reloadAuftraege();
        return auftraege;
    }

    public void addAuftrag(String titel, String fertigungsArt, String dateiName, String dateiOrt, String kosten, String auftraggeberId, String auftragbearbeiter) throws ElabException {

        //todo ("Auftraggeber existiert nicht!");

        Personenverwaltung personenverwaltung = new Personenverwaltung();

        //todo Idee zum testen, ob alle Auftraggeber auch existieren   man müsste hier dann ein objekt von der personenverwaltung erstellen
        for (String name : auftragbearbeiter.split("\n")) {
            if (!personenverwaltung.personAlreadyExists(name)) {
                throw new ElabException("Auftragbearbeiter " + name + " existiert nicht!");
            }
        }

        // hier schonmal der Test auf korrekte eingabe der Kosten
        float kostenFloat;
        try {
            kostenFloat = Float.parseFloat(kosten);
        } catch (NumberFormatException e) {
            throw new ElabException("Kosten wurden nicht als korrekte Kommazahl angegeben! (float)");
        }

        ArrayList<String> auftragbearbeiterIds = new ArrayList<>();
        // hier müssen die Personen korrekt aus dem String eingefügt werden
        auftragbearbeiterIds.add("8"); //beispielhaft

        Db db = new Db();
        timestamp = new Timestamp(System.currentTimeMillis());

        String sql = "INSERT INTO Auftrag (Titel, FertigungsArt, DateiName, DateiOrt, Kosten, angenommen, gefertigt, kosten_kalkuliert, abgeholt, abgerechnet, wartenAufMaterial, fertigungFehlgeschlagen, ZeitStempel ,AuftraggeberID , AuftragbearbeiterIds) "
                + "VALUES ('" + titel + "','"
                + fertigungsArt + "','"
                + dateiName + "','"
                + dateiOrt + "',"
                + kostenFloat + ",'"
                + "FALSE','FALSE','FALSE','FALSE','FALSE','FALSE','FALSE',"
                + kosten + ","
                + auftraggeberId + ",'"
                + String.join(",", auftragbearbeiterIds) + "')";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }


    public void removeAuftrag(int id) {
        Db db = new Db();
        Auftrag a = this.getAuftragByID(id);
        String sql = "DELETE FROM Auftrag WHERE ID = " + a.getId() + " ";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateAuftrag(int id, String titel, String fertigungsArt, String dateiName, String dateiOrt,
                              float kosten) throws ElabException {

        //todo ("Auftraggeber existiert nicht!");

        Db db = new Db();
        String sql = "UPDATE Auftrag SET Titel = '" + titel + "', FertigungsArt = '" + fertigungsArt
                + "', DateiName = '" + dateiName + "', DateiOrt = '" + dateiOrt + "', Kosten = '" + kosten
                + "WHERE ID = " + id + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateStatus(int id, boolean angenommen, boolean gefertigt, boolean kosten_kalkuliert, boolean abgeholt,
                             boolean abgerechnet, boolean wartenAufMaterial, boolean fertigungFehlgeschlagen) {

        Db db = new Db();
        String sql = "";
        Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
        Auftrag a = this.getAuftragByID(id);

        if (a == null) {
            return;
        }

        if (a.isAngenommen() != angenommen) {

            a.setAngenommen(angenommen);
            a.setStatusZeitstempel_angenommen(timestampNew);

            sql = "UPDATE Auftrag SET angeommen = " + angenommen + ", statusZeitstempel_angenommen = " + timestampNew
                    + " WHERE ID = " + a.getId() + "";
            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (a.isGefertigt() != gefertigt) {

            a.setGefertigt(gefertigt);
            a.setStatusZeitstempel_gefertigt(timestampNew);
            sql = "UPDATE Auftrag SET gefertigt = " + gefertigt + ", statusZeitstempel_gefertigt = " + timestampNew
                    + " WHERE ID = " + a.getId() + "";
            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (a.isKosten_kalkuliert() != kosten_kalkuliert) {

            a.setKosten_kalkuliert(kosten_kalkuliert);
            a.setStatusZeitstempel_kosten_kalkuliert(timestampNew);
            sql = "UPDATE Auftrag SET kosten_kalkuliert = " + kosten_kalkuliert + ", statusZeitstempel_gefertigt = "
                    + timestampNew + " WHERE ID = " + a.getId() + "";
            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (a.isAbgeholt() != abgeholt) {

            a.setAbgeholt(abgeholt);
            a.setStatusZeitstempel_abgeholt(timestampNew);
            sql = "UPDATE Auftrag SET abgeholt = " + abgeholt + ", statusZeitstempel_gefertigt = " + timestampNew
                    + " WHERE ID = " + a.getId() + "";
            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (a.isAbgerechnet() != abgerechnet) {

            a.setAbgerechnet(abgerechnet);
            a.setStatusZeitstempel_abgerechnet(timestampNew);
            sql = "UPDATE Auftrag SET abgerechnet = " + abgerechnet + ", statusZeitstempel_gefertigt = " + timestampNew
                    + " WHERE ID = " + a.getId() + "";
            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (a.isWartenAufMaterial() != wartenAufMaterial) {

            a.setWartenAufMaterial(wartenAufMaterial);
            a.setStatusZeitstempel_wartenAufMaterial(timestampNew);
            sql = "UPDATE Auftrag SET wartenAufMaterial = " + wartenAufMaterial + ", statusZeitstempel_gefertigt = "
                    + timestampNew + " WHERE ID = " + a.getId() + "";
            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (a.isFertigungFehlgeschlagen() != fertigungFehlgeschlagen) {

            a.setFertigungFehlgeschlagen(fertigungFehlgeschlagen);
            a.setStatusZeitstempel_fertigungFehlgeschlagen(timestampNew);
            sql = "UPDATE Auftrag SET fertigungFehlgeschlagen = " + fertigungFehlgeschlagen + ", statusZeitstempel_gefertigt = "
                    + timestampNew + " WHERE ID = " + a.getId() + "";
            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Auftrag getAuftragByID(int id) {
        for (Auftrag auftrag : auftraege) {
            if (auftrag.getId() == id) {
                return auftrag;
            }
        }
        return null;
    }

    // int id, boolean angenommen, boolean gefertigt, boolean kosten_kalkuliert,
    // boolean abgeholt, boolean abgerechnet, boolean wartenAufMaterial, boolean
    // fertigungFehlgeschlagen,Timestamp statusZeitstempel_angenommen,
    // Timestamp statusZeitstempel_gefertigt,Timestamp
    // statusZeitstempel_kosten_kalkuliert,Timestamp
    // statusZeitstempel_abgeholt,Timestamp statusZeitstempel_abgerechnet,
    // Timestamp statusZeitstempel_wartenAufMaterial

    // public void updateStatusAngenommen(int id, boolean angenommen)
    // {
    // String sql = "";
    // Db db = new Db();
    // Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
    // if(angenommen == true)
    // {
    // sql = "UPDATE Auftrag SET angeommen = " + true + ",
    // statusZeitstempel_angenommen = " + timestampNew + " WHERE ID = " + id +
    // "";
    // }
    // else
    // {
    // sql = "UPDATE Auftrag SET angeommen = " + false + ",
    // statusZeitstempel_angenommen = " + timestampNew + " WHERE ID = " + id +
    // "";
    // }
    // try {
    // db.updateQuery(sql);
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    //
    // }
    // public void updateStatusGefertigt(int id, boolean gefertigt)
    // {
    // String sql = "";
    // Db db = new Db();
    // Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
    // if(gefertigt == true)
    // {
    // sql = "UPDATE Auftrag SET gefertigt = " + true + ",
    // statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id +
    // "";
    // }
    // else
    // {
    // sql = "UPDATE Auftrag SET gefertigt = " + false + ",
    // statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id +
    // "";
    // }
    // try {
    // db.updateQuery(sql);
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }
    // public void updateStatusKosten_kalkuliert(int id, boolean
    // kosten_kalkuliert)
    // {
    // String sql = "";
    // Db db = new Db();
    // Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
    // if(kosten_kalkuliert == true)
    // {
    // sql = "UPDATE Auftrag SET kosten_kalkuliert = " + true + ",
    // statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id +
    // "";
    // }
    // else
    // {
    // sql = "UPDATE Auftrag SET kosten_kalkuliert = " + false + ",
    // statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id +
    // "";
    // }
    // try {
    // db.updateQuery(sql);
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }
    // public void updateStatusAbgeholt(int id, boolean abgeholt)
    // {
    // String sql = "";
    // Db db = new Db();
    // Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
    // if(abgeholt == true)
    // {
    // sql = "UPDATE Auftrag SET abgeholt = " + true + ",
    // statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id +
    // "";
    // }
    // else
    // {
    // sql = "UPDATE Auftrag SET abgeholt = " + false + ",
    // statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id +
    // "";
    // }
    // try {
    // db.updateQuery(sql);
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }
    // public void updateStatusAbgerechnet(int id, boolean abgerechnet)
    // {
    // String sql = "";
    // Db db = new Db();
    // Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
    // if(abgerechnet == true)
    // {
    // sql = "UPDATE Auftrag SET abgerechnet = " + true + ",
    // statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id +
    // "";
    // }
    // else
    // {
    // sql = "UPDATE Auftrag SET abgerechnet = " + false + ",
    // statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id +
    // "";
    // }
    // try {
    // db.updateQuery(sql);
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }
    // public void updateStatusWartenAufMaterial(int id, boolean
    // wartenAufMaterial)
    // {
    // String sql = "";
    // Db db = new Db();
    // Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
    // if(wartenAufMaterial == true)
    // {
    // sql = "UPDATE Auftrag SET wartenAufMaterial = " + true + ",
    // statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id +
    // "";
    // }
    // else
    // {
    // sql = "UPDATE Auftrag SET wartenAufMaterial = " + false + ",
    // statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id +
    // "";
    // }
    // try {
    // db.updateQuery(sql);
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }
    // public void updateStatusFertigungFehlgeschlagen(int id, boolean
    // fertigungFehlgeschlagen)
    // {
    // String sql = "";
    // Db db = new Db();
    // Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
    // if(fertigungFehlgeschlagen == true)
    // {
    // sql = "UPDATE Auftrag SET fertigungFehlgeschlagen = " + true + ",
    // statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id +
    // "";
    // }
    // else
    // {
    // sql = "UPDATE Auftrag SET fertigungFehlgeschlagen = " + false + ",
    // statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id +
    // "";
    // }
    // try {
    // db.updateQuery(sql);
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }
}
