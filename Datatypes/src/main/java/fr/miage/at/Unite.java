public class Unite {

	public String nom;
	public Systeme systeme;
	public Type type;
	public Conversion conv;
	
	public Unite(String name, Type type, Conversion conversion){
		nom = name;
		this.type = type;
		conv = conversion;
	}
	
	public Unite() {
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Conversion getConv() {
		return conv;
	}

	public void setConv(Conversion conv) {
		this.conv = conv;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
