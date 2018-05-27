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

    private void reloadAuftraege() {
        Db db = new Db();
        this.auftraege.clear();
        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Auftrag");
            while (rs.next()) {
                Auftrag a = new Auftrag(rs.getInt("ID"), rs.getString("Titel"),
                        rs.getString("FertigungsArt"), rs.getString("DateiName"),
                        rs.getString("DateiOrt"), rs.getFloat("Kosten"), rs.getBoolean("angenommen"), rs.getTimestamp("statusZeitstempel_angenommen"),
                        rs.getBoolean("gefertigt"),rs.getTimestamp("statusZeitstempel_gefertigt"), rs.getBoolean("kosten_kalkuliert"), rs.getTimestamp("statusZeitstempel_kosten_kalkuliert"),
                        rs.getBoolean("abgeholt"), rs.getTimestamp("statusZeitstempel_abgeholt"), 
                        rs.getBoolean("abgerechnet"), rs.getTimestamp("statusZeitstempel_abgerechnet"), rs.getBoolean("wartenAufMaterial"), rs.getTimestamp("statusZeitstempel_wartenAufMaterial"),
                        rs.getBoolean("fertigungFehlgeschlagen"), rs.getTimestamp("statusZeitstempel_fertigungFehlgeschlagen"));
                a.setZeitstempel(rs.getTimestamp("timestamp"));
                this.auftraege.add(a);
            }
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

    public void addAuftrag(String titel, String fertigungsArt, String dateiName, String dateiOrt, float kosten) {

        Db db = new Db();

        String sql = "INSERT INTO Auftrag (Titel, FertigungsArt, DateiName, DateiOrt, Kosten, angenommen, gefertigt, kosten_kalkuliert, abgeholt, abgerechnet, wartenAufMaterial, fertigungFehlgeschlagen) "
                + "VALUES ('" + titel + "','" + fertigungsArt + "','" + dateiName
                + "','" + dateiOrt + "','" + kosten + "'," + false + "," + false + ","
                + false + "," + false + "," + false + "," + false + "," + false + ")";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void updateAuftrag(int id, String titel, String fertigungsArt, String dateiName, String dateiOrt, float kosten) {
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

    public void updateStatus(int id, boolean angenommen, boolean gefertigt, boolean kosten_kalkuliert,
                             boolean abgeholt, boolean abgerechnet, boolean wartenAufMaterial, boolean fertigungFehlgeschlagen) {
        Db db = new Db();
        
        timestamp = new Timestamp(System.currentTimeMillis());
        
        String sql = "UPDATE Auftrag SET angenommen = " + angenommen + ", statusZeitstempel_angenommen " + timestamp + ", gefertigt = " + gefertigt + ", statusZeitstempel_gefertigt " 
        		+ timestamp + ", kosten_kalkuliert = " + kosten_kalkuliert + ", statusZeitstempel_kosten_kalkuliert " + timestamp + ", abgeholt = "
                + abgeholt + ", statusZeitstempel_abgeholt " + timestamp + ", abgerechnet = " + abgerechnet + ", statusZeitstempel_abgerechnet " + timestamp + ", wartenAufMaterial = " 
        		+ wartenAufMaterial + ", statusZeitstempel_wartenAufMaterial " + timestamp + ", fertigungFehlgeschlagen = " + fertigungFehlgeschlagen + ", statusZeitstempel_fertigungFehlgeschlagen " + timestamp 
                + "WHERE ID = " + id + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
