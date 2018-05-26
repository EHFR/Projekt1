package ELAB;

import java.util.Date;

public abstract class Person {
    private int id;
    private String name;
    private String adresse;
    private String telefonnr;
    private String email;
    private Date zeitstempel;
    private String passwort;

    public Person(int id, String name, String adresse, String telefonnr, String email) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.telefonnr = telefonnr;
        this.email = email;
        this.zeitstempel = new Date(); // Muss bei schon existierenden Personen mit set überschrieben werden
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelefonnr() {
        return telefonnr;
    }

    public void setTelefonnr(String telefonnr) {
        this.telefonnr = telefonnr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setZeitstempel(Date zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    public String getZeitstempel() {
        return zeitstempel.toString();
    }

    public int getId() {
        return id;
    }
}
