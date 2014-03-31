
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
			val = val - valDep.getUnite().getConv().getValeur();
			break;
		case MULTIPLICATION:
			val = val * valDep.getUnite().getConv().getValeur();
			break;
		case COMPLEXE:
			val = val - valDep.getUnite().getConv().getValeur();
			val = val * valDep.getUnite().getConv().getValeurComplexe();
			break;
		default:
			break;
		}
		
		switch (unite.getConv().getType()) {
		case ADDITION:
			val = val + unite.getConv().getValeur();
			break;
		case MULTIPLICATION:
			val = val / unite.getConv().getValeur();
			break;
		case COMPLEXE:
			val = val / unite.getConv().getValeurComplexe();
			val = val + unite.getConv().getValeur();
			break;
		default:
			break;
		}
		
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
