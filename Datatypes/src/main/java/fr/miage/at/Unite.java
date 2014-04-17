package fr.miage.at;


/* Class Unite
 * 
* Authors : DANE & GUIFFAULT
 * Licence GNU GPL V3
 * Last version : 16 / 04 / 2014
 */
public class Unite {

	public String nom;
	public Systeme systeme;
	public TypeUnite type;
	public Conversion conv;
	
        //construct
	public Unite(String name, TypeUnite type, Conversion conversion){
		nom = name;
		this.type = type;
		conv = conversion;
	}
	
	public Unite() {}

        //getters and setters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Conversion getConv() {
		return conv;
	}

	public void setConv(Conversion conv) {
		this.conv = conv;
	}

	public TypeUnite getType() {
		return type;
	}

	public void setType(TypeUnite type) {
		this.type = type;
	}
}
