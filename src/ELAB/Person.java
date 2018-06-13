package ELAB;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

    public Person(int id, String name, String adresse, String telefonnr, String email, String type, String passwort) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.telefonnr = telefonnr;
        this.email = email;
        this.zeitstempel = new Timestamp(System.currentTimeMillis());
        this.type = type;
        this.passwort = passwort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getAdresse() {
        return adresse;
    }

    String getTelefonnr() {
        return telefonnr;
    }

    String getEmail() {
        return email;
    }

    void setZeitstempel(Timestamp zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    String getZeitstempelString() {
        if (zeitstempel != null) {
            Date date = new Date();
            date.setTime(zeitstempel.getTime());
            return new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(date);
        }
        return "Null"; // für den Fall, dass der Zeitstempel null ist
    }

    public int getId() {
        return id;
    }

    String getType() {
        return type;
    }

    String getPasswort() {
        return passwort;
    }

}
