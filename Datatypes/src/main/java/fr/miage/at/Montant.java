package fr.miage.at;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/* Class Montant
 * 
* Authors : DANE & GUIFFAULT
 * Licence GNU GPL V3
 * Last version : 16 / 04 / 2014
 */
public class Montant {
	public double valeur;
	public Unite unite;
	
        //construct
	public Montant(){}
	
	public Montant(double val, Unite unite){
		valeur = val;
		this.unite = unite;
	}
	
        //conversion as a function of the type of conversion
	public void convert(Montant valDep) {
		double val = valDep.getValeur();
                
		Unite unit = valDep.getUnite();
                Conversion conv = unit.getConv();

                //retrieving type
                //call the corresponding calculation
		switch (conv.getType()) {
		case ADDITION:
			val = conv.calculAdd(val);
			break;
		case MULTIPLICATION:
			val = conv.calculMul(val);
			break;
		case COMPLEXE:
                        val = conv.calculComplexe(val);
			break;
		default:
			break;
		}
		
                //retrieving the value
                conv = unite.getConv();
                valeur = conv.getValeur();
		switch (conv.getType()) {
		case ADDITION:
			val = val + valeur;
			break;
		case MULTIPLICATION:
			val = val /valeur;
			break;
		case COMPLEXE:
                        double valeurComp = conv.getValeurComplexe();
			val = val / valeurComp;
			val = val + valeur;
			break;
		default:
			break;
		}
		
		valeur = val;
	}
	
        //getter and setter
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
