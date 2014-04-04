package fr.miage.at;

enum EnumType {
	DISTANCE, SUPERFICIE, POIDS, TEMPERATURE
};

public class Type {
	public EnumType intitule;

	public Type(int intitule) {
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

	public EnumType getIntitule() {
		return intitule;
	}

	public void setIntitule(EnumType intitule) {
		this.intitule = intitule;
	}

}
