import java.util.ArrayList;

enum EnumType {
	DISTANCE, SUPERFICIE, POIDS, TEMPERATURE
};

public class Type {
	public EnumType intitule;

	// public ArrayList<Unites> unites;

	public Type(String intitule) {
		switch (intitule) {
		case "DISTANCE":
			this.intitule = EnumType.DISTANCE;
			break;
		case "SURFACE":
			this.intitule = EnumType.SUPERFICIE;
			break;
		case "POIDS":
			this.intitule = EnumType.POIDS;
			break;
		case "TEMPERATURE":
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
