package ELAB;

import java.util.Date;

public class Rechnung {
    private int id;
    private Date datum;
    private String name;
    private Kunde auftraggeber;
    private Mitglied ansprechpartner;
    private Topf topf;
    private float betrag;
    private String status;
    private Date zeitstempel;

    public void exportToPDF() {

    }
}
