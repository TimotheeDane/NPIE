enum TypeConversion {
	ADDITION, MULTIPLICATION, COMPLEXE
}

public class Conversion {
	public double valeur;
	public TypeConversion type;
	
	public Conversion(String type, double valeur) {
		this.valeur = valeur;
		
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

	public TypeConversion getType() {
		return type;
	}

	public void setType(TypeConversion type) {
		this.type = type;
	}
}
