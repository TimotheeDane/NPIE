package fr.miage.at;

public class Conversion {
	public double valeur;
	public TypeConversion type;
	
	public Conversion(int type, double valeur) {
		this.valeur = valeur;
		
		switch (type) {
		case 1:
			this.type = TypeConversion.ADDITION;
			break;
		case 2:
			this.type = TypeConversion.MULTIPLICATION;
			break;
		case 3:
			this.type = TypeConversion.COMPLEXE;
			break;
		}
	}

	public double getValeur() {
		return valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

	public TypeConversion getType() {
		return type;
	}

	public void setType(TypeConversion type) {
		this.type = type;
	}
}

enum TypeConversion {
	ADDITION, MULTIPLICATION, COMPLEXE
}
