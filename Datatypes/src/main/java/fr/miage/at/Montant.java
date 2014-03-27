
public class Montant {
	public double valeur;
	public Unite unite;
	
	public Montant(){}
	
	public Montant(double val, Unite unite){
		valeur = val;
		this.unite = unite;
	}
	
	public void convert(Montant valDep) {
		double val = valDep.getValeur();
		
		switch (valDep.getUnite().getConv().getType()) {
		case ADDITION:
			val = val + valDep.getUnite().getConv().getValeur();
			break;
		case MULTIPLICATION:
			val = val * valDep.getUnite().getConv().getValeur();
			break;
		case COMPLEXE:
			break;
		default:
			break;
		}
		
		switch (unite.getConv().getType()) {
		case ADDITION:
			val = val - unite.getConv().getValeur();
			break;
		case MULTIPLICATION:
			val = val / unite.getConv().getValeur();
			break;
		case COMPLEXE:
			break;
		default:
			break;
		}
		
		System.out.println(val);
		valeur = val;
	}
	
	public double getValeur() {
		return valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

	public Unite getUnite() {
		return unite;
	}

	public void setUnite(Unite unite) {
		this.unite = unite;
	}
}
