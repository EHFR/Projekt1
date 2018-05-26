package ELAB;

public class Kunde extends Person {

    public Kunde(int id, String name, String adresse, String telefonnr, String email) {
        super(id, name, adresse, telefonnr, email);
        this.type = "Kunde";
    }

}
