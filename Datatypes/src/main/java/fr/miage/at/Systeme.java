package fr.miage.at;

import java.util.ArrayList;
import java.util.List;

/* Class Systeme
 * 
* Authors : DANE & GUIFFAULT
 * Licence GNU GPL V3
 * Last version : 16 / 04 / 2014
 */
public class Systeme {
	public String nom;
	public List<Unite> unit = new ArrayList<Unite>();
	
        //construct
	public Systeme(String nom) {
		this.nom = nom;
	}

        //getters and setters
	public List<Unite> getUnit() {
		return unit;
	}

	public void setUnit(List<Unite> unit) {
		this.unit = unit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
