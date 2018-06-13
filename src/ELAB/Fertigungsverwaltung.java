package ELAB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Fertigungsverwaltung {
    private ArrayList<Auftrag> auftraege;

    public Fertigungsverwaltung() {
        auftraege = new ArrayList<>();
        this.reloadAuftraege();
    }

    private ArrayList<Person> fillList(int id) {
        Db db = new Db();
        Personenverwaltung pw = new Personenverwaltung();
        ArrayList<Person> personen = new ArrayList<>();
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

    private boolean booleanReturn(int b) {
        return b == 1;
    }

    private int intReturn(boolean b) {
        if (b)
            return 1;
        else
            return 0;
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
                        booleanReturn(rs.getInt("angenommen")), rs.getTimestamp("statusZeitstempel_angenommen"),
                        booleanReturn(rs.getInt("gefertigt")), rs.getTimestamp("statusZeitstempel_gefertigt"),
                        booleanReturn(rs.getInt("kosten_kalkuliert")), rs.getTimestamp("statusZeitstempel_kosten_kalkuliert"),
                        booleanReturn(rs.getInt("abgeholt")), rs.getTimestamp("statusZeitstempel_abgeholt"),
                        booleanReturn(rs.getInt("abgerechnet")), rs.getTimestamp("statusZeitstempel_abgerechnet"),
                        booleanReturn(rs.getInt("wartenAufMaterial")), rs.getTimestamp("statusZeitstempel_wartenAufMaterial"),
                        booleanReturn(rs.getInt("fertigungFehlgeschlagen")),
                        rs.getTimestamp("statusZeitstempel_fertigungFehlgeschlagen"), pw.getPersonByID(rs.getInt("AuftraggeberID")), fillList(rs.getInt("ID")));
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

    ArrayList<Auftrag> getAuftraege() {
        this.reloadAuftraege();
        return auftraege;
    }

    void addAuftrag(String titel, String fertigungsArt, String dateiName, String dateiOrt, String kosten, String auftraggeber, ArrayList<Integer> auftragbearbeiter) throws ElabException {
        Db db = new Db();

        Personenverwaltung personenverwaltung = new Personenverwaltung();
        if (!personenverwaltung.personAlreadyExists(auftraggeber)) {
            throw new ElabException("Auftraggeber " + auftraggeber + " existiert nicht");
        }

        int key = 0;
        int auftraggeberid = personenverwaltung.getPersonIdByName(auftraggeber);

        float kostenFloat;
        try {
            kostenFloat = Float.parseFloat(kosten);
        } catch (NumberFormatException e) {
            throw new ElabException("Kosten wurden nicht als korrekte Kommazahl angegeben! (float)");
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String sql = "INSERT INTO Auftrag (Titel, FertigungsArt, DateiName, DateiOrt, Kosten, angenommen, gefertigt, kosten_kalkuliert, abgeholt, abgerechnet, wartenAufMaterial, fertigungFehlgeschlagen, ZeitStempel ,AuftraggeberID) "
                + "VALUES ('"
                + titel + "','"
                + fertigungsArt + "','"
                + dateiName + "','"
                + dateiOrt + "',"
                + kostenFloat + ","
                + "0,0,0,0,0,0,0,'"
                + timestamp.toString() + "','"
                + auftraggeberid + "')";
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
        } finally {
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

    void removeAuftrag(int id) {
        Db db = new Db();
        String sql = "DELETE FROM Auftrag WHERE ID = " + id + " ";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void updateAuftrag(int id, String titel, String fertigungsArt, String dateiName, String dateiOrt,
                       String kosten, String auftraggeber, ArrayList<Integer> bearbeiter) throws ElabException {
        Db db = new Db();

        Personenverwaltung personenverwaltung = new Personenverwaltung();
        if (!personenverwaltung.personAlreadyExists(auftraggeber)) {
            throw new ElabException("Auftraggeber " + auftraggeber + " existiert nicht");
        }

        float kostenFloat;
        try {
            kostenFloat = Float.parseFloat(kosten);
        } catch (NumberFormatException e) {
            throw new ElabException("Kosten wurden nicht als korrekte Kommazahl angegeben! (float)");
        }

        String sql = "UPDATE Auftrag SET Titel = '" + titel + "', FertigungsArt = '" + fertigungsArt
                + "', DateiName = '" + dateiName + "', DateiOrt = '" + dateiOrt + "', Kosten = " + kostenFloat + ", AuftraggeberID = " + personenverwaltung.getPersonIdByName(auftraggeber)
                + " WHERE ID = " + id + "";
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

    void updateStatus(int id, boolean angenommen, boolean gefertigt, boolean kosten_kalkuliert, boolean abgeholt,
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

            String sql = "UPDATE Auftrag SET angenommen = '" + intReturn(angenommen) + "', statusZeitstempel_angenommen = '" + timestampNew.toString()
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
            String sql = "UPDATE Auftrag SET gefertigt = '" + intReturn(gefertigt) + "', statusZeitstempel_gefertigt = '" + timestampNew.toString()
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
            String sql = "UPDATE Auftrag SET kosten_kalkuliert = '" + intReturn(kosten_kalkuliert) + "', statusZeitstempel_kosten_kalkuliert = '"
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
            String sql = "UPDATE Auftrag SET abgeholt = '" + intReturn(abgeholt) + "', statusZeitstempel_abgeholt = '" + timestampNew.toString()
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
            String sql = "UPDATE Auftrag SET abgerechnet = '" + intReturn(abgerechnet) + "', statusZeitstempel_abgerechnet = '" + timestampNew.toString()
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
            String sql = "UPDATE Auftrag SET wartenAufMaterial = '" + intReturn(wartenAufMaterial) + "', statusZeitstempel_wartenAufMaterial = '"
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
            String sql = "UPDATE Auftrag SET fertigungFehlgeschlagen = '" + intReturn(fertigungFehlgeschlagen) + "', statusZeitstempel_fertigungFehlgeschlagen = '"
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

    ArrayList<Auftrag> getAuftragByStatus(String status) {
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
}
