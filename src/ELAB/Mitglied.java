package ELAB;

public class Mitglied extends Person {

    public Mitglied(int id, String name, String adresse, String telefonnr, String email) {
        super(id, name, adresse, telefonnr, email);
        this.type = "Mitglied";
    }
}
