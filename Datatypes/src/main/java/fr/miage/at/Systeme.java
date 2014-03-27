import java.util.ArrayList;

public class Systeme {
	public String nom;
	public ArrayList<Unite> unit;
	
	public Systeme(String nom) {
		this.nom = nom;
		unit = new ArrayList<Unite>();
	}

	public ArrayList<Unite> getUnit() {
		return unit;
	}

	public void setUnit(ArrayList<Unite> unit) {
		this.unit = unit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
