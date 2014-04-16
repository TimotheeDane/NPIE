package fr.miage.at;

enum TypeConversion {
	ADDITION, MULTIPLICATION, COMPLEXE
}


/* Class Conversion
 * 
 * Authors : DANE & GUIFFAULT
 * Licence GNU GPL V3
 * Last version : 16 / 04 / 2014
 */
public class Conversion {
	public double valeur;
	public double valeurComplexe;
	public TypeConversion type;
	
        //constuct
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

        //getters and setters
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
        
        
        //operations
        
        //calculating a complex-valued
        public double calculComplexe(double val){
            val = val - valeur;
            val = val * valeurComplexe;
            return val;
        }
        
        //calculating a value from the addition
        public double calculAdd(double val){
            val = val - valeur;
            return val;
        }
        
        //calculating a value by multiplication
        public double calculMul(double val){
            val = val * valeur;
            return val;
        }
}
