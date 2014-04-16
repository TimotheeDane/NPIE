package fr.miage.at;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Interface {

	public static ArrayList<Systeme> listeSystemes = new ArrayList<Systeme>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		init();
		Scanner scan = new Scanner(System.in);
		boolean menu = true;

		while (menu) {
			System.out.println("=========================================");
			System.out.println("========== Conversion d'unit�s ==========");
			System.out.println("=========================================");
			System.out.println("Que souhaitez-vous faire ?");
			System.out.println("1 - Effectuer une conversion");
			System.out.println("2 - Ajouter une unit�");
			System.out.println("3 - Consulter la liste des unit�s disponibles");
			System.out.println("0 - Quitter");
			String choix = scan.next();
                        int choixInt = Integer.parseInt(choix);
			switch (choixInt) {
			case 1:
				menuConversion(scan);
				break;
			case 2:
				menuAjout(scan);
				break;
			case 3:
				break;
			case 0:
				menu = false;
				break;
			default:
				System.out
						.println("Erreur, choisissez une valeur entre 0 et 3");
				break;
			}
		}

		scan.close();
	}

	public static void init() {
		String fichier = "C:\\Users\\Alex\\Downloads\\eclipse\\workspace\\Units_project\\src\\convert";
		String[] ligneFichier;
		int i;
		boolean existe;

		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				i = 0;
				existe = false;

				ligneFichier = ligne.split(" ; ");
				while (i < listeSystemes.size() && !existe) {
					if (ligneFichier[0].equals(listeSystemes.get(i).getNom())) {
						existe = true;
					} else {
						i++;
					}
				}

				Conversion conv;

				if (ligneFichier[3].equals("COMPLEXE")) {
					String[] complexe = ligneFichier[4].split("/");
					conv = new Conversion(Integer.parseInt(ligneFichier[3]),
							Double.parseDouble(complexe[0]),
							Double.parseDouble(complexe[1]));
				} else {
					conv = new Conversion(Integer.parseInt(ligneFichier[3]),
							Double.parseDouble(ligneFichier[4]), 0);
				}

				Type nouveauType = new Type(Integer.parseInt(ligneFichier[2]));
				Unite nouvelleUnite = new Unite(ligneFichier[1], nouveauType,
						conv);

				if (!existe) {
					Systeme sys = new Systeme(ligneFichier[0]);
					sys.getUnit().add(nouvelleUnite);
					listeSystemes.add(sys);
				} else {
					for (i = 0; i < listeSystemes.size(); i++) {
						if (listeSystemes.get(i).getNom()
								.equals(ligneFichier[0])) {
							listeSystemes.get(i).getUnit().add(nouvelleUnite);
						}
					}
				}
			}

			/*int j;
			for (i = 0; i < listeSystemes.size(); i++) {
				System.out.println(listeSystemes.get(i).getNom());
				for (j = 0; j < listeSystemes.get(i).getUnit().size(); j++) {
					System.out.println(listeSystemes.get(i).getUnit().get(j)
							.getNom());
				}
			}*/

			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static void menuConversion(Scanner scan) {
		String rep = "";
		double val = 0.0;
		int i, j;

		Montant valDep = null;
		Montant valArr = new Montant();
		Unite uniteDep = new Unite();
		Unite uniteArr = new Unite();

		boolean ok = false;
		while (!ok) {
			System.out.print("Unit� de d�part : ");
			rep = scan.nextLine();

			for (i = 0; i < listeSystemes.size(); i++) {
				for (j = 0; j < listeSystemes.get(i).getUnit().size(); j++) {
					if (listeSystemes.get(i).getUnit().get(j).getNom()
							.equals(rep)) {
						ok = true;
						uniteDep = listeSystemes.get(i).getUnit().get(j);
					}
				}
			}

			if (!ok) {
				System.out.println("Unit� inexistante");
			}
		}

		ok = false;
		while (!ok) {

			try {
				System.out.print("Valeur de l'unit� de d�part : ");
				val = scan.nextDouble();
				scan.nextLine();
				valDep = new Montant(val, uniteDep);
				ok = true;
			} catch (Exception e) {
				System.out.println("Veuillez entrer une valeur enti�re ou d�cimale (XX,XX)");
				scan.nextLine();
			}
		}

		ok = false;
		while (!ok) {
			System.out.print("Unit� d'arriv�e : ");
			rep = scan.nextLine();

			for (i = 0; i < listeSystemes.size(); i++) {
				for (j = 0; j < listeSystemes.get(i).getUnit().size(); j++) {
					if (listeSystemes.get(i).getUnit().get(j).getNom()
							.equals(rep)) {
						ok = true;
						uniteArr = listeSystemes.get(i).getUnit().get(j);
					}
				}
			}

			if (!ok) {
				System.out.println("Unit� inexistante");
			}
		}

		valArr.setUnite(uniteArr);
		valArr.convert(valDep);

		System.out.println("Valeur de l'unit� d'arriv�e : "
				+ valArr.getValeur());
	}

	public static void menuAjout(Scanner scan) {
		boolean ok = false;
		String systeme = "";

		while (!ok) {
			System.out
					.println("Dans quel syst�me fait partie l'unit� que vous voulez ajouter ? (METRIQUE / IMPERIAL / HORS-SYSTEME)");
			systeme = scan.next();

			systeme = systeme.toUpperCase();
			if (systeme.equals("METRIQUE") || systeme.equals("IMPERIAL")
					|| systeme.equals("HORS-SYSTEME")) {
				ok = true;
			} else {
				System.out
						.println("Le syst�me n'existe pas, veuillez entrer un syst�me existant");
			}
		}

		Systeme syst = null;
		for (Systeme s : listeSystemes) {
			if (s.getNom().equals(systeme)) {
				syst = s;
			}
		}

		ok = false;
		int i = 0;
		boolean trouve = false;
		Type typ = null;
		System.out.println("De quel type votre unit� va-t-elle faire partie ?");
		String type = scan.next();

		type = type.toUpperCase();
		while (!trouve && i < syst.getUnit().size()) {
			Unite unit = syst.getUnit().get(i);
			if (unit.getType().getIntitule().toString().equals(type)) {
				typ = unit.getType();
				trouve = true;
			}
			i++;
		}
		
		boolean typeAjoute = false;

		if (trouve) {
			ok = true;
		} else {
			System.out
					.println("Type inexistant, voulez-vous l'ajouter ? (o/n)");
			String rep = scan.next();
			rep.toLowerCase();

			if (rep.equals("o")) {
				ok = true;
				typeAjoute = true;
			} else {
				System.out.println("Op�ration annul�e, retour au menu");
			}
		}
		
		String unite = "";
		if (ok) {
			ok = false;
			while (!ok) {
				trouve = false;
				System.out.println("Quelle unit� voulez-vous ajouter ?");
				unite = scan.next();
				unite = unite.toLowerCase();
				i = 0;
				while (!trouve && i < syst.getUnit().size()) {
					Unite unit = syst.getUnit().get(i);
					if (unit.getNom().equals(unite)) {
						trouve = true;
					}
					i++;
				}

				if (!trouve) {
					ok = true;
				} else {
					System.out.print("L'unit� existe d�j�. ");
				}
			}
			
			String ajoutLigne = "";
			if (typeAjoute) {
				// Unit� �talon
				ajoutLigne = systeme + " ; " + unite + " ; " + type + " ; MULTIPLICATION ; 1";
				System.out.println(ajoutLigne);
			} else {
				String etalon = "";
				trouve = false;
				i = 0;
				for (Systeme s : listeSystemes) {
					while (!trouve && i < s.getUnit().size()) {
						Unite unit = s.getUnit().get(i);
						if (unit.getType().getIntitule().toString().equals(type) && unit.getConv().getValeur() == 1) {
							trouve = true;
							etalon = unit.getNom();
						}
						i++;
					}
				}
				
				if (!trouve) {
					System.out.println("Fichier corrompu. Pas d'unit� �talon pour ce type");
				} else {
					ok = false;
					System.out.println("Quel type d'op�ration pour convertir vers l'unit� " + etalon + " ? (MULTIPLICATION / ADDITION / COMPLEXE)");
					String operation = scan.next();
					operation = operation.toUpperCase();
					while (!ok) {
						double valeur = 0;
						
						try {
							ok = true;
							if (operation.equals("COMPLEXE")) {
								System.out.println("Premi�re valeur pour convertir vers " + etalon + " ? (Addition)");
								valeur = scan.nextDouble();
								ajoutLigne = systeme + " ; " + unite + " ; " + type + " ; " + operation + " ; " + String.valueOf(valeur);
								System.out.println("Seconde valeur pour convertir vers " + etalon + " ? (Multiplication)");
								valeur = scan.nextDouble();
								ajoutLigne += "/" + String.valueOf(valeur);
							} else if (operation.equals("MULTIPLICATION") || operation.equals("ADDITION")) {
								System.out.println("Combien faut-il pour faire 1" + etalon + " ?");
								valeur = scan.nextDouble();
								ajoutLigne = systeme + " ; " + unite + " ; " + type + " ; " + operation + " ; " + String.valueOf(valeur); 
							} else {
								ok = false;
								System.out.println("Merci de choisir une op�ration existante");
								System.out.println("Quel type d'op�ration pour convertir vers l'unit� " + etalon + " ? (MULTIPLICATION / ADDITION / COMPLEXE)");
								operation = scan.next();
								operation = operation.toUpperCase();
							}
							
						} catch (Exception e) {
							System.out.println("Merci de choisir une valeur enti�re ou d�cimale (XX,XX)");
							scan.next();
							ok = false;
						}
					}
				}
				ajoutMemoire(ajoutLigne);
				ajoutFichier(ajoutLigne);
			}
		}
	}
	
	public static void ajoutMemoire(String ajoutLigne){
		
	}
	
	public static void ajoutFichier(String ajoutLigne){
		
	}
}
