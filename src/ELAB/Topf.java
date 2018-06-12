package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Topf {
    private int id;
    private String name;
    private float sollbestand;
    private float istbestand;
    private ArrayList<Rechnung> rechnungen;
    private String kasse;
    private Timestamp zeitstempel;


    public Timestamp getZeitstempel() {
        return zeitstempel;
    }

    public void setZeitstempel(Timestamp zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    public Topf(int id, String name, float sollbestand, float istbestand, String kasse) {
        this.id = id;
        this.name = name;
        this.sollbestand = sollbestand;
        this.istbestand = istbestand;
        this.kasse = kasse;
        this.zeitstempel = new Timestamp(System.currentTimeMillis());
        this.rechnungen = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSollbestand() {
        return sollbestand;
    }

    public void setSollbestand(float sollbestand) {
        this.sollbestand = sollbestand;
    }

    public float getIstbestand() {
    	this.reloadRechnung();
    	float ergebnis = 0;
    	for(Rechnung rechnung : this.rechnungen)
    	{
    		ergebnis+=rechnung.getBetrag();
    	}
    	return ergebnis;
    }

    public void setIstbestand(float istbestand) {
        this.istbestand = istbestand;
    }

    public ArrayList<Rechnung> getRechnungen() {
        this.reloadRechnung();
        return rechnungen;
    }

    public void setRechnungen(ArrayList<Rechnung> rechnungen) {
        this.rechnungen = rechnungen;
    }

    public void fuegeRechnungHinzu(Rechnung r) {
        rechnungen.add(r);
    }

    public String getKasse() {
        return kasse;
    }


    // Methoden Für Rechnungen in dem Topf
    
    
    public boolean booleanReturn(int b)
    {
    	
    	if(b == 1)
    	{
    		return true;
    	}
    	else 
    	{ 
    		return false;
        }
    }
    
    public int intReturn(boolean b)
    {
    	if(b == true)
    	{
    		return 1;
    	}
    	else
    	{
    		return 0;
    	}
    }
    
    private Personenverwaltung personenVerwaltung = new Personenverwaltung();

    private void reloadRechnung() {
        Db db = new Db();
        Personenverwaltung pw = new Personenverwaltung();
        this.rechnungen.clear();
        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Rechnung WHERE TopfID=" + this.getId());
            while (rs.next()) {
                Rechnung r = new Rechnung(rs.getInt("ID"), rs.getString("Name"), rs.getFloat("Betrag"), rs.getString("Bezahlart"),
                        booleanReturn(rs.getInt("inBearbeitung")), rs.getTimestamp("statusZeitstempel_inBearbeitung"),
                        booleanReturn(rs.getInt("eingereicht")), rs.getTimestamp("statusZeitstempel_eingereicht"),
                        booleanReturn(rs.getInt("abgewickelt")), rs.getTimestamp("statusZeitstempel_abgewickelt"),
                        booleanReturn(rs.getInt("ausstehend")), rs.getTimestamp("statusZeitstempel_ausstehend"),
                		rs.getFloat("IstBestand"));
                r.setZeitstempel(rs.getTimestamp("Zeitstempel"));

                System.out.println(booleanReturn(rs.getInt("inBearbeitung")));
                System.out.println(rs.getTimestamp("statusZeitstempel_inBearbeitung"));
                
                try {
                    Person geber = personenVerwaltung.getPersonByName(rs.getString("AuftragGeber"));
                    r.setAuftraggeber(geber);

                    Person ansprechPartner = personenVerwaltung.getPersonByName(rs.getString("AnsprechPartner"));
                    r.setAnsprechpartner(ansprechPartner);

                } catch (ElabException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                r.setTopf(this);

                this.rechnungen.add(r);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error while reading Database!");
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void addRechnung(String name, String auftraggeber, String ansprechpartner, String betrag, String bezahlart) throws ElabException {

        float BetragFloat;
        try {
            BetragFloat = Float.parseFloat(betrag);
        } catch (NumberFormatException e) {
            throw new ElabException("Betrag wurde nicht als korrekte Kommazahl angegeben! (float)");
        }

        Personenverwaltung person = new Personenverwaltung();

        for (String namen : auftraggeber.split("\n")) {
            if (!person.personAlreadyExists(namen)) {
                throw new ElabException("Auftraggeber " + namen + " existiert nicht!");
            }
        }

        for (String namen : ansprechpartner.split("\n")) {
            if (!person.personAlreadyExists(namen)) {
                throw new ElabException("Ansprechpartner " + namen + " existiert nicht!");
            }
        }

        ArrayList<String> personen = new ArrayList<>();
        personen.add(auftraggeber);
        personen.add(ansprechpartner);

        Db db = new Db();
        zeitstempel = new Timestamp(System.currentTimeMillis());

        String sql = "INSERT INTO Rechnung (Datum, Name, AuftragGeber, AnsprechPartner, TopfID, Betrag, Bezahlart, Zeitstempel) "
                + "VALUES ('"
                + zeitstempel + "','"
                + name + "','"
                + auftraggeber + "','"
                + ansprechpartner + "',"
                + this.id + ","
                + BetragFloat + ",'"
                + bezahlart + "','"
                + zeitstempel + "')";

        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeRechnung(int id) throws ElabException {
        Db db = new Db();
        String sql = "DELETE FROM Rechnung WHERE ID = " + id + " ";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRechnung(int id, String name, String auftraggeber, String ansprechpartner, String betrag, String bezahlart) throws ElabException {

        Db db = new Db();
        String sql = "UPDATE Rechnung SET Name = '" + name + "', AuftragGeber = '" + auftraggeber
                + "', AnsprechPartner = '" + ansprechpartner + "', Betrag = '" + betrag + "', Bezahlart = '" + bezahlart
                + "' WHERE ID = " + id + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
