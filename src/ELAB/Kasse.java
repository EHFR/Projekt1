package ELAB;

import java.util.ArrayList;

public class Kasse {
    private ArrayList<Topf> toepfe;

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
