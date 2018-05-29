package ELAB;

import java.util.ArrayList;

public class Kasse {
    private ArrayList<Topf> toepfe;
    // todo Hier Fehlen soll und ist Bestand und Name siehe PDF: Topfe haben ebenfalls wie die Kassen einen Namen, Soll- und Ist-Bestand

    public Kasse(ArrayList<Topf> toepfe) {
        this.toepfe = toepfe;
    }

    public ArrayList<Topf> getToepfe() {
        return toepfe;
    }

    public void setToepfe(ArrayList<Topf> toepfe) {
        this.toepfe = toepfe;
    }

}
