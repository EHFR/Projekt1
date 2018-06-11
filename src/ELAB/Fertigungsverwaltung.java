package ELAB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.itextpdf.text.log.SysoLogger;

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
        try {
            ResultSet rs = db.exequteQuery(sql);
            while (rs.next()) {
                int personID = rs.getInt("PersonID");
                personen.add(pw.getPersonByID(personID));
            }
        } catch (SQLException e) {
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
                Auftrag a = new Auftrag(rs.getInt("ID"), rs.getString("Titel"), rs.getString("FertigungsArt"),
                        rs.getString("DateiName"), rs.getString("DateiOrt"), rs.getFloat("Kosten"),
                        rs.getBoolean("angenommen"), rs.getTimestamp("statusZeitstempel_angenommen"),
                        rs.getBoolean("gefertigt"), rs.getTimestamp("statusZeitstempel_gefertigt"),
                        rs.getBoolean("kosten_kalkuliert"), rs.getTimestamp("statusZeitstempel_kosten_kalkuliert"),
                        rs.getBoolean("abgeholt"), rs.getTimestamp("statusZeitstempel_abgeholt"),
                        rs.getBoolean("abgerechnet"), rs.getTimestamp("statusZeitstempel_abgerechnet"),
                        rs.getBoolean("wartenAufMaterial"), rs.getTimestamp("statusZeitstempel_wartenAufMaterial"),
                        rs.getBoolean("fertigungFehlgeschlagen"),
                        rs.getTimestamp("statusZeitstempel_fertigungFehlgeschlagen"), pw.getPersonByID(rs.getInt("AuftraggeberID")), fillList(rs.getInt("ID")));
                a.setZeitstempel(rs.getTimestamp("ZeitStempel"));

//                System.out.println("Auftrag in Aufruf: "+a);

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

//    public void addAuftrag(String titel, String fertigungsArt, String dateiName, String dateiOrt, String kosten, String auftraggeberId, String auftragbearbeiter) throws ElabException {
//
//        //todo ("Auftraggeber existiert nicht!");
//
//        Personenverwaltung personenverwaltung = new Personenverwaltung();
//        ArrayList<Person> auftragbearbeiterListe = new ArrayList<Person>();
//        for (String name : auftragbearbeiter.split("\n")) {
//            auftragbearbeiterListe.add(personenverwaltung.getPersonByName(auftragbearbeiter));
//        }
//
//
//        //todo Idee zum testen, ob alle Auftraggeber auch existieren   man müsste hier dann ein objekt von der personenverwaltung erstellen
//        for (String name : auftragbearbeiter.split("\n")) {
//            if (!personenverwaltung.personAlreadyExists(name)) {
//                throw new ElabException("Auftragbearbeiter " + name + " existiert nicht!");
//            }
//        }
//
//        // hier schonmal der Test auf korrekte eingabe der Kosten
//        float kostenFloat;
//        try {
//            kostenFloat = Float.parseFloat(kosten);
//        } catch (NumberFormatException e) {
//            throw new ElabException("Kosten wurden nicht als korrekte Kommazahl angegeben! (float)");
//        }
//
//        ArrayList<String> auftragbearbeiterIds = new ArrayList<>();
//        // hier müssen die Personen korrekt aus dem String eingefügt werden
//        auftragbearbeiterIds.add("8"); //beispielhaft
//
//        Db db = new Db();
//        timestamp = new Timestamp(System.currentTimeMillis());
//
//        String sql = "INSERT INTO Auftrag (Titel, FertigungsArt, DateiName, DateiOrt, Kosten, angenommen, gefertigt, kosten_kalkuliert, abgeholt, abgerechnet, wartenAufMaterial, fertigungFehlgeschlagen, ZeitStempel ,AuftraggeberID , AuftragbearbeiterIds) "
//                + "VALUES ('" + titel + "','"
//                + fertigungsArt + "','"
//                + dateiName + "','"
//                + dateiOrt + "',"
//                + kostenFloat + ",'"
//                + "FALSE','FALSE','FALSE','FALSE','FALSE','FALSE','FALSE',"
//                + timestamp + "','"
//                + auftraggeberId + "')";
//        
//        System.out.println(sql);
//        try {
//           db.updateQuery(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        
//    }	

    public void addAuftrag(String titel, String fertigungsArt, String dateiName, String dateiOrt, String kosten, String auftraggeber, ArrayList<Integer> auftragbearbeiter) throws ElabException {
        Db db = new Db();
        Personenverwaltung pw = new Personenverwaltung();
        ArrayList<Person> bearbeiter = new ArrayList<Person>();
        
        for(Person p : pw.getPersonen())
        {
 	       if(!pw.personAlreadyExists(auftraggeber))
 	       {
 	    	   throw new ElabException("Auftraggeber " + auftraggeber + " existiert nicht");
 	       }
        }
        
        int key = 0;
        int auftraggeberid = pw.getPersonIdByName(auftraggeber);
        float kostenFloat;
        try {
            kostenFloat = Float.parseFloat(kosten);
        } catch (NumberFormatException e) {
            throw new ElabException("Kosten wurden nicht als korrekte Kommazahl angegeben! (float)");
        }

        timestamp = new Timestamp(System.currentTimeMillis()); 
        String sql = "INSERT INTO Auftrag (Titel, FertigungsArt, DateiName, DateiOrt, Kosten, angenommen, gefertigt, kosten_kalkuliert, abgeholt, abgerechnet, wartenAufMaterial, fertigungFehlgeschlagen, ZeitStempel ,AuftraggeberID) "
                + "VALUES ('" 
        		+ titel + "','"
                + fertigungsArt + "','"
                + dateiName + "','"
                + dateiOrt + "',"
                + kostenFloat + ",'"
                + "FALSE','FALSE','FALSE','FALSE','FALSE','FALSE','FALSE','"
                + timestamp.toString() + "','"
                + auftraggeberid + "')";
        System.out.println(sql);
        PreparedStatement stmt = null;
        try {
            stmt = db.dataSource().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
            }
        } catch (SQLException x) {
            x.printStackTrace();
        } finally 
        {
        	db.close();
        }

        for (Integer i : auftragbearbeiter) {
            String sql2 = "INSERT INTO AuftragPerson (AuftragID,PersonID) VALUES (" + key + "," + i + ")";
            try {
                db.updateQuery(sql2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeAuftrag(int id) {
        Db db = new Db();
        String sql = "DELETE FROM Auftrag WHERE ID = " + id + " ";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateAuftrag(int id, String titel, String fertigungsArt, String dateiName, String dateiOrt,
                              String kosten, String auftraggeber, ArrayList<Integer> bearbeiter) throws ElabException {

        //todo ("Auftraggeber existiert nicht!");

        Db db = new Db();
        Personenverwaltung pw = new Personenverwaltung();
        
       for(Person p : pw.getPersonen())
       {
	       if(!pw.personAlreadyExists(auftraggeber))
	       {
	    	   throw new ElabException("Auftraggeber " + auftraggeber + " existiert nicht");
	       }
       }
        
       float kostenFloat;
       try {
           kostenFloat = Float.parseFloat(kosten);
       } catch (NumberFormatException e) {
           throw new ElabException("Kosten wurden nicht als korrekte Kommazahl angegeben! (float)");
       }
    
        
        
        String sql = "UPDATE Auftrag SET Titel = '" + titel + "', FertigungsArt = '" + fertigungsArt
                + "', DateiName = '" + dateiName + "', DateiOrt = '" + dateiOrt + "', Kosten = " + kostenFloat + ", AuftraggeberID = " + pw.getPersonIdByName(auftraggeber)
                + " WHERE ID = " + id + "";
        
        System.out.println(sql);
        
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String sql2 = "DELETE FROM AuftragPerson WHERE AuftragID = " + id + "";
        try {
            db.updateQuery(sql2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (bearbeiter.size() > 0) {
            for (Integer i : bearbeiter) {
                String sql3 = "INSERT INTO AuftragPerson (AuftragID,PersonID) VALUES (" + id + "," + i + ")";
                try {
                    db.updateQuery(sql3);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new ElabException("Es muss mindestens ein Bearbeiter an einem Auftrag arbeiten");
        }
    }

    public void deleteAuftragbearbeiter(int auftragID, int personID) {
        Db db = new Db();

        String sql = "DELETE FROM AuftragPerson WHERE PersonID = " + personID + "";

    }


    public void updateStatus(int id, boolean angenommen, boolean gefertigt, boolean kosten_kalkuliert, boolean abgeholt,
                             boolean abgerechnet, boolean wartenAufMaterial, boolean fertigungFehlgeschlagen) {

        Db db = new Db();
        Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
        Auftrag a = this.getAuftragByID(id);

        if (a == null) {
            return;
        }
        if (a.isAngenommen() != angenommen) {

            a.setAngenommen(angenommen);
            a.setStatusZeitstempel_angenommen(timestampNew);

            String sql = "UPDATE Auftrag SET angenommen = '" + angenommen + "', statusZeitstempel_angenommen = '" + timestampNew.toString()
                    + "' WHERE ID = " + a.getId() + "";
            System.out.println(sql);
            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (a.isGefertigt() != gefertigt) {

            a.setGefertigt(gefertigt);
            a.setStatusZeitstempel_gefertigt(timestampNew);
            String sql = "UPDATE Auftrag SET gefertigt = '" + gefertigt + "', statusZeitstempel_gefertigt = '" + timestampNew.toString()
                    + "' WHERE ID = " + a.getId() + "";
            System.out.println(sql);
            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (a.isKosten_kalkuliert() != kosten_kalkuliert) {

            a.setKosten_kalkuliert(kosten_kalkuliert);
            a.setStatusZeitstempel_kosten_kalkuliert(timestampNew);
            String sql = "UPDATE Auftrag SET kosten_kalkuliert = '" + kosten_kalkuliert + "', statusZeitstempel_kosten_kalkuliert = '"
                    + timestampNew.toString() + "' WHERE ID = " + a.getId() + "";
            System.out.println(sql);
            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (a.isAbgeholt() != abgeholt) {

            a.setAbgeholt(abgeholt);
            a.setStatusZeitstempel_abgeholt(timestampNew);
            String sql = "UPDATE Auftrag SET abgeholt = '" + abgeholt + "', statusZeitstempel_abgeholt = '" + timestampNew.toString()
                    + "' WHERE ID = " + a.getId() + "";
            System.out.println(sql);
            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (a.isAbgerechnet() != abgerechnet) {

            a.setAbgerechnet(abgerechnet);
            a.setStatusZeitstempel_abgerechnet(timestampNew);
            String sql = "UPDATE Auftrag SET abgerechnet = '" + abgerechnet + "', statusZeitstempel_abgerechnet = '" + timestampNew.toString()
                    + "' WHERE ID = " + a.getId() + "";
            System.out.println(sql);
            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (a.isWartenAufMaterial() != wartenAufMaterial) {

            a.setWartenAufMaterial(wartenAufMaterial);
            a.setStatusZeitstempel_wartenAufMaterial(timestampNew);
            String sql = "UPDATE Auftrag SET wartenAufMaterial = '" + wartenAufMaterial + "', statusZeitstempel_wartenAufMaterial = '"
                    + timestampNew.toString() + "' WHERE ID = " + a.getId() + "";
            System.out.println(sql);
            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (a.isFertigungFehlgeschlagen() != fertigungFehlgeschlagen) {

            a.setFertigungFehlgeschlagen(fertigungFehlgeschlagen);
            a.setStatusZeitstempel_fertigungFehlgeschlagen(timestampNew);
            String sql = "UPDATE Auftrag SET fertigungFehlgeschlagen = '" + fertigungFehlgeschlagen + "', statusZeitstempel_fertigungFehlgeschlagen = '"
                    + timestampNew.toString() + "' WHERE ID = " + a.getId() + "";
            System.out.println(sql);
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

    public ArrayList<Auftrag> getAuftragByStatus(String status) {
        ArrayList<Auftrag> auftraegeGefiltert = new ArrayList<>();

        this.reloadAuftraege();
        for (Auftrag auftrag : this.auftraege) {
            switch (status) {
                case "Alles":
                    return getAuftraege();
                case "Angenommen":
                    if (auftrag.isAngenommen()) {
                        auftraegeGefiltert.add(auftrag);
                    }
                    break;
                case "Nicht Angenommen":
                    if (!auftrag.isAngenommen()) {
                        auftraegeGefiltert.add(auftrag);
                    }
                    break;
                case "Gefertigt":
                    if (auftrag.isGefertigt()) {
                        auftraegeGefiltert.add(auftrag);
                    }
                    break;
                case "Nicht Gefertigt":
                    if (!auftrag.isGefertigt()) {
                        auftraegeGefiltert.add(auftrag);
                    }
                    break;
                case "Kosten kalkuliert":
                    if (auftrag.isKosten_kalkuliert()) {
                        auftraegeGefiltert.add(auftrag);
                    }
                    break;
                case "Kosten nicht kalkuliert":
                    if (!auftrag.isKosten_kalkuliert()) {
                        auftraegeGefiltert.add(auftrag);
                    }
                    break;
                case "Abgeholt":
                    if (auftrag.isAbgeholt()) {
                        auftraegeGefiltert.add(auftrag);
                    }
                    break;
                case "Nicht Abgeholt":
                    if (!auftrag.isAbgeholt()) {
                        auftraegeGefiltert.add(auftrag);
                    }
                    break;
                case "Abgerechnet":
                    if (auftrag.isAbgerechnet()) {
                        auftraegeGefiltert.add(auftrag);
                    }
                    break;
                case "Nicht Abgerechnet":
                    if (!auftrag.isAbgerechnet()) {
                        auftraegeGefiltert.add(auftrag);
                    }
                    break;
                case "Auf Material warten":
                    if (auftrag.isWartenAufMaterial()) {
                        auftraegeGefiltert.add(auftrag);
                    }
                    break;
                case "Material vorhanden":
                    if (!auftrag.isWartenAufMaterial()) {
                        auftraegeGefiltert.add(auftrag);
                    }
                    break;
                case "Fertigung unterbrochen":
                    if (auftrag.isFertigungFehlgeschlagen()) {
                        auftraegeGefiltert.add(auftrag);
                    }
                    break;
                case "Fertigung nicht unterbrochen":
                    if (!auftrag.isFertigungFehlgeschlagen()) {
                        auftraegeGefiltert.add(auftrag);
                    }
                    break;
                default:
                    System.out.println("Status unbekannt in getAuftragByStatus");
                    break;
            }
        }

        return auftraegeGefiltert;
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
