package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Finanzverwaltung {
    private ArrayList<Topf> toepfe;

    public Finanzverwaltung() {
        toepfe = new ArrayList<>();
        this.reloadTopf();
    }

    ArrayList<Topf> getToepfe() {
        this.reloadTopf();
        return toepfe;
    }

    //Methoden f�r T�pfe
    private void reloadTopf() {
        Db db = new Db();
        this.toepfe.clear();
        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Topf");
            while (rs.next()) {
                Topf t = new Topf(rs.getInt("ID"), rs.getString("Name"),
                        rs.getFloat("SollBestand"), rs.getString("Kasse"));
                this.toepfe.add(t);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error while reading Database!");
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    void addTopf(String name, String sollBetrag, String kasse) throws ElabException {

        float sollBetragFloat;
        try {
            sollBetragFloat = Float.parseFloat(sollBetrag);
        } catch (NumberFormatException e) {
            throw new ElabException("Sollbetrag wurde nicht als korrekte Kommazahl angegeben! (float)");
        }

        Db db = new Db();
        String sql = "INSERT INTO Topf (Name, SollBestand, Kasse) "
                + "VALUES ('" + name + "','"
                + sollBetragFloat + "','"
                + kasse + "')";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void removeTopf(int id) throws ElabException {
        if (getTopfByID(id).getRechnungen().size() > 0) {
            throw new ElabException("Topf hat noch Rechnungen. Diese im vorhinein l�schen.");
        }
        Db db = new Db();
        Topf t = this.getTopfByID(id);
        String sql = "DELETE FROM Topf WHERE ID = " + t.getId() + " ";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Topf getTopfByID(int id) {
        for (Topf topf : toepfe) {
            if (topf.getId() == id) {
                return topf;
            }
        }
        return null;
    }


    void updateTopf(int id, String name, String sollBetrag, String kasse) throws ElabException {

        float sollBetragFloat2;
        try {
            sollBetragFloat2 = Float.parseFloat(sollBetrag);
        } catch (NumberFormatException e) {
            throw new ElabException("Sollbetrag wurde nicht als korrekte Kommazahl angegeben! (float)");
        }

        Db db = new Db();
        String sql = "UPDATE Topf SET Name = '" + name +
                "', SollBestand = '" + sollBetragFloat2 +
                "', Kasse = '" + kasse +
                "' WHERE ID = " + id + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    String getIstbestandBarkasse() {
        this.reloadTopf();
        float ergebnis = 0;
        for (Topf topf : this.toepfe) {
            if (topf.getKasse().equals("Barkasse"))
                ergebnis += topf.getIstbestand();
        }
        return String.valueOf(ergebnis);
    }


    String getSollbestandBarkasse() {
        String sql = "SELECT * FROM Topf";
        Db db = new Db();
        float betrag = 0;
        try {
            ResultSet rs = db.exequteQuery(sql);
            while (rs.next()) {
                if (rs.getString("Kasse").equals("Barkasse")) {
                    betrag += rs.getFloat("SollBestand");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return String.valueOf(betrag);
    }


    String getIstbestandKonto() {
        this.reloadTopf();
        float ergebnis = 0;
        for (Topf topf : this.toepfe) {
            if (topf.getKasse().equals("Konto"))
                ergebnis += topf.getIstbestand();
        }
        return String.valueOf(ergebnis);
    }


    String getSollbestandKonto() {
        String sql = "SELECT * FROM Topf";
        Db db = new Db();
        float betrag = 0;
        try {
            ResultSet rs = db.exequteQuery(sql);
            while (rs.next()) {
                if (rs.getString("Kasse").equals("Konto")) {
                    betrag += rs.getFloat("SollBestand");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.valueOf(betrag);
    }


    String getIstbestandKostenstelle() {
        this.reloadTopf();
        float ergebnis = 0;
        for (Topf topf : this.toepfe) {
            if (topf.getKasse().equals("Kostenstelle"))
                ergebnis += topf.getIstbestand();
        }
        return String.valueOf(ergebnis);
    }


    String getSollbestandKostenstelle() {
        String sql = "SELECT * FROM Topf";
        Db db = new Db();
        float betrag = 0;
        try {
            ResultSet rs = db.exequteQuery(sql);
            while (rs.next()) {
                if (rs.getString("Kasse").equals("Kostenstelle")) {
                    betrag += rs.getFloat("SollBestand");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.valueOf(betrag);
    }
    print(helloworld)
}
