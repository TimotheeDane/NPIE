package fr.miage.at;

enum TypeConversion {
	ADDITION, MULTIPLICATION, COMPLEXE
}

public class Conversion {
	public double valeur;
	public double valeurComplexe;
	public TypeConversion type;
	
	public Conversion(int type, double valeur, double valeur2) {
		this.valeur = valeur;
		valeurComplexe = valeur2;
		
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

	public double getValeurComplexe() {
		return valeurComplexe;
	}

	public void setValeurComplexe(double valeurComplexe) {
		this.valeurComplexe = valeurComplexe;
	}

	public TypeConversion getType() {
		return type;
	}

	public void setType(TypeConversion type) {
		this.type = type;
	}
}
