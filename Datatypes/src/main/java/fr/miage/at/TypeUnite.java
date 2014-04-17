package fr.miage.at;

enum EnumType {
	DISTANCE, SUPERFICIE, POIDS, TEMPERATURE
};

/* Class TypeUnite
 * 
* Authors : DANE & GUIFFAULT
 * Licence GNU GPL V3
 * Last version : 16 / 04 / 2014
 */
public class TypeUnite {
	public EnumType intitule;

        //constuct for enum
	public TypeUnite(int intitule) {
		switch (intitule) {
		case 1:
			this.intitule = EnumType.DISTANCE;
			break;
		case 2:
			this.intitule = EnumType.SUPERFICIE;
			break;
		case 3:
			this.intitule = EnumType.POIDS;
			break;
		case 4:
			this.intitule = EnumType.TEMPERATURE;
			break;
		default:
			break;
		}
	}

        //getters and setters
	public EnumType getIntitule() {
		return intitule;
	}

	public void setIntitule(EnumType intitule) {
		this.intitule = intitule;
	}

}
