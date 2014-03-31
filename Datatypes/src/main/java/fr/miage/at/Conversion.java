enum TypeConversion {
	ADDITION, MULTIPLICATION, COMPLEXE
}

public class Conversion {
	public double valeur;
	public double valeurComplexe;
	public TypeConversion type;
	
	public Conversion(String type, double valeur, double valeur2) {
		this.valeur = valeur;
		valeurComplexe = valeur2;
		
		switch (type) {
		case "ADDITION":
			this.type = TypeConversion.ADDITION;
			break;
		case "MULTIPLICATION":
			this.type = TypeConversion.MULTIPLICATION;
			break;
		case "COMPLEXE":
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
