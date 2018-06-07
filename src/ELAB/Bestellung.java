package ELAB;

public class Bestellung {
    private int id;
    private String name;
    private String produkt;
    private String kategorie;
    private String einzelpreis;
    private String menge;

    public Bestellung(int id, String name, String produkt, String kategorie, String einzelpreis, String menge) {
        this.id = id;
        this.name = name;
        this.produkt = produkt;
        this.kategorie = kategorie;
        this.einzelpreis = einzelpreis;
        this.menge = menge;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProdukt() {
        return produkt;
    }

    public String getKategorie() {
        return kategorie;
    }

    public String getEinzelpreis() {
        return einzelpreis;
    }

    public String getMenge() {
        return menge;
    }
}
