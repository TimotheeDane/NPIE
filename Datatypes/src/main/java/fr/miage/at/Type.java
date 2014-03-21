package fr.miage.at;

import java.util.ArrayList;

enum EnumType {
	LONGUEUR, SURFACE, POIDS
};

public class Type {
	public EnumType intitule;

	// public ArrayList<Unites> unites;

	public Type(int intitule) {
		switch (intitule) {
		case 1:
			this.intitule = EnumType.LONGUEUR;
			break;
		case 2:
			this.intitule = EnumType.SURFACE;
			break;
		case 3:
			this.intitule = EnumType.POIDS;
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
