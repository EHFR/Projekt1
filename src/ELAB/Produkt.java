package ELAB;

public class Produkt {
    private int id;
    private String name;
    private String link;
    private float einzelpreis;
    private int menge_lagernd;
    private int menge_geplant;
    private int menge_bestellt;
    private String lagerort;
    
    
	public Produkt(int id, String name, String link, float einzelpreis, int menge_lagernd, int menge_geplant, int menge_bestellt, String lagerort) 
	{
		this.id = id;
		this.name = name;
		this.link = link;
		this.einzelpreis = einzelpreis;
		this.menge_lagernd = menge_lagernd;
		this.menge_geplant = menge_geplant;
		this.menge_bestellt = menge_bestellt;
		this.lagerort = lagerort;
	}


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public float getEinzelpreis() {
		return einzelpreis;
	}

	public void setEinzelpreis(float einzelpreis) {
		this.einzelpreis = einzelpreis;
	}

	public int getMenge_lagernd() {
		return menge_lagernd;
	}

	public void setMenge_lagernd(int menge_lagernd) {
		this.menge_lagernd = menge_lagernd;
	}

	public int getMenge_geplant() {
		return menge_geplant;
	}

	public void setMenge_geplant(int menge_geplant) {
		this.menge_geplant = menge_geplant;
	}

	public int getMenge_bestellt() {
		return menge_bestellt;
	}

	public void setMenge_bestellt(int menge_bestellt) {
		this.menge_bestellt = menge_bestellt;
	}

	public String getLagerort() {
		return lagerort;
	}

	public void setLagerort(String lagerort) {
		this.lagerort = lagerort;
	}

}
