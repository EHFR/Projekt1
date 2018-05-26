package ELAB;

import java.sql.Timestamp;
import java.util.Date;

public class Person {
    private int id;
    private String name;
    private String adresse;
    private String telefonnr;
    private String email;
    private Timestamp zeitstempel;
    private String passwort;
    private String type;


    public Person(int id, String name, String adresse, String telefonnr, String email, String type) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.telefonnr = telefonnr;
        this.email = email;
        this.zeitstempel = new Timestamp(System.currentTimeMillis());
        this.type = type;
    }

    public Timestamp getZeitstempel() {
        return zeitstempel;
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

    public void setZeitstempel(Timestamp zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    public String getZeitstempelString() {
        return zeitstempel.toString();
    }


    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
