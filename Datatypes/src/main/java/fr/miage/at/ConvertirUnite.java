package fr.miage.at;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Class ConvertirUnite
 * 
 * void main
 * void init
 * void menuConversion
 * void ajoutUnite
 * void ajoutMemoire
 * void ajoutFichier
 * void affichage 
 * 
 * Authors : DANE & GUIFFAULT
 * Licence GNU GPL V3
 * Last version : 16 / 04 / 2014
 */
public class ConvertirUnite {

	public static List<Systeme> listeSystemes;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
                //initializing data memory
		init();
		Scanner scan = new Scanner(System.in);
		boolean menu = true;
                //display the main menu
		while (menu) {
			System.out.println("=========================================");
			System.out.println("========== Conversion d'unites ==========");
			System.out.println("=========================================");
			System.out.println("Que souhaitez-vous faire ?");
			System.out.println("1 - Effectuer une conversion");
			System.out.println("2 - Ajouter une unite");
			System.out.println("3 - Consulter la liste des unites disponibles");
			System.out.println("0 - Quitter");
			String choix = scan.nextLine();
                        int choixInt = Integer.parseInt(choix);
			switch (choixInt) {
			case 1:
                                //convert 2 units
				menuConversion(scan);
				break;
			case 2:
                                //add a new unit
				menuAjout(scan);
				break;
			case 3:
                                //display available units
                                affichage(scan);
				break;
			case 0:
                                //exit application
				menu = false;
				break;
			default:
				System.out.println("Erreur, choisissez une valeur entre 0 et 3");
				break;
			}
		}

		scan.close();
	}

        //function that initializes reading through the file
	public static void init() {
                listeSystemes = new ArrayList<Systeme>();
		String fichier = "src/main/java/fr/miage/at/convert";
		
		int cpt;
		boolean existe;

                //read file
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader buffer  = new BufferedReader(ipsr);
			String ligne;
                        String[] ligneFichier;
                        String ligneF, ligneF3, ligneF4;
                        String comp0, comp1;
                        //read line by line
			while ((ligne = buffer.readLine()) != null) {
				cpt = 0;
				existe = false;
                                //decomposition of the line
				ligneFichier = ligne.split(" ; ");
                                //system recovery
				while (cpt < listeSystemes.size() && !existe) {
                                        Object obj = listeSystemes.get(cpt);
                                        Systeme sys = (Systeme)obj;
                                        String sysNom = sys.getNom();
                                        ligneF = ligneFichier[0];
					if (ligneF.equals(sysNom)) {
						existe = true;
					} else {
						cpt++;
					}
				}

				Conversion conv;
                                //retrieve the conversion type
                                ligneF3 = ligneFichier[3];
                                ligneF4 = ligneFichier[4];
				if (ligneF3.equals("3")) {                                      
					String[] complexe = ligneF4.split("/");
                                        comp0 = complexe[0];
                                        comp1 = complexe[1];
					conv = new Conversion(Integer.parseInt(ligneF3),
							Double.parseDouble(comp0),
							Double.parseDouble(comp1));
				} else {
					conv = new Conversion(Integer.parseInt(ligneF3),
							Double.parseDouble(ligneF4), 0);
				}

                                //retrieve the unit type
                                ligneF = ligneFichier[2];
				TypeUnite nouveauType = new TypeUnite(Integer.parseInt(ligneF));
                                ligneF = ligneFichier[1];
				Unite nouvelleUnite = new Unite(ligneF, nouveauType,
						conv);

				//add unit in the system
                                if (!existe) {
                                        List<Unite> unit;
                                        ligneF = ligneFichier[0];
					Systeme sys = new Systeme(ligneF);
                                        unit = sys.getUnit();
					unit.add(nouvelleUnite);
					listeSystemes.add(sys);
				} else {
                                        Systeme sys;
                                        String name;
					for (cpt = 0; cpt < listeSystemes.size(); cpt++) {
                                                sys = listeSystemes.get(cpt);
                                                name = sys.getNom();
                                                ligneF = ligneFichier[0];
						if (name.equals(ligneF)) {
							listeSystemes.get(cpt).getUnit().add(nouvelleUnite);
						}
					}
				}
			}

			buffer.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

        /**
	 * @param scan read what the user types
         * Conversion from a unit chosen by the user
         * to another unit chosen by him
	 */
	public static void menuConversion(Scanner scan) {
		String rep = "";
		double val = 0.0;
		int cpt1, cpt2;
                
		Montant valDep = null;
		Montant valArr = new Montant();
		Unite uniteDep = new Unite();
		Unite uniteArr = new Unite();

                Systeme tempSys;
                List<Unite> tempUnit;
                Unite unit;
                String tempName;
                
		boolean trouve = false;
                //get the starting unit that will be converted
		while (!trouve) {
			System.out.print("Unite de depart : ");
			rep = scan.nextLine();
                        //the unit has to exist to be accepted
			for (cpt1 = 0; cpt1 < listeSystemes.size(); cpt1++) {
                                tempSys = listeSystemes.get(cpt1);
                                tempUnit = tempSys.getUnit();
				for (cpt2 = 0; cpt2< tempUnit.size(); cpt2++) {
                                        unit = tempUnit.get(cpt2);
                                        tempName = unit.getNom();
					if (tempName.equals(rep)) {
						trouve = true;
						uniteDep = unit;
					}
				}
			}

			if (!trouve) {
				System.out.println("Unite inexistante");
			}
		}

		trouve = false;
                //retrieve the value to convert
		while (!trouve) {
			try {
				System.out.print("Valeur de l'unite de depart : ");
				val = scan.nextDouble();
				scan.nextLine();
				valDep = new Montant(val, uniteDep);
				trouve = true;
			} catch (Exception e) {
				System.out.println("Veuillez entrer une valeur enti�re ou decimale (XX,XX)");
				scan.nextLine();
			}
		}

                //get the unit that the user wants in the end
		trouve = false;
		while (!trouve) {
			System.out.print("Unite d'arrivee : ");
			rep = scan.nextLine();
			for (cpt1 = 0; cpt1 < listeSystemes.size(); cpt1++) {
                                tempSys = listeSystemes.get(cpt1);
                                tempUnit = tempSys.getUnit();
				for (cpt2 = 0; cpt2 < tempUnit.size(); cpt2++) {
                                        unit = tempUnit.get(cpt2);
                                        tempName = unit.getNom();
					if (tempName.equals(rep)) {
						trouve = true;
						uniteArr = unit;
					}
				}
			}

			if (!trouve) {
				System.out.println("Unite inexistante");
			}
		}

		valArr.setUnite(uniteArr);
                
		valArr.convert(valDep);
                //return the value converted
		System.out.println("Valeur de l'unite d'arrivee : "
				+ valArr.getValeur());
	}

         /**
	 * @param scan
	 */
        //add a new unit
	public static void menuAjout(Scanner scan) {
		boolean trouveSys = false;
		String systeme = "";
                String typeUnit="";

                Systeme tempSys;
                List<Unite> tempUnit;
                Unite unit;
                EnumType tempInti;
                String tempIntiString;
                String tempName;
                Conversion tempConv;
                
                //verification of the existence of the system that will contain the new unit
		while (!trouveSys) {
			System.out
					.println("Dans quel systeme fait partie l'unite que vous voulez ajouter ? (METRIQUE / IMPERIAL / HORS-SYSTEME)");
			systeme = scan.nextLine();

			systeme = systeme.toUpperCase();
			if (systeme.equals("METRIQUE") || systeme.equals("IMPERIAL")
					|| systeme.equals("HORS-SYSTEME")) {
				trouveSys = true;
			} else {
				System.out.println("Le systeme n'existe pas, veuillez entrer un systeme existant");
			}
		}

                //retrieve the system
		Systeme syst = null;
                String listName;
		for (Systeme liste : listeSystemes) {
                        listName = liste.getNom();
			if (listName.equals(systeme)) {
				syst = liste;
			}
		}

		trouveSys = false;
		int cpt = 0;
		boolean trouve = false;
		TypeUnite typ = null;
                //retrieve the type of the unit
		System.out.println("De quel type votre unite va-t-elle faire partie ?");
		String type = scan.nextLine();
                
                tempUnit = syst.getUnit();
		type = type.toUpperCase();
                TypeUnite tempType;
		while (!trouve && cpt < tempUnit.size()) {
			unit = tempUnit.get(cpt);
                        tempType = unit.getType();
                        tempInti = tempType.getIntitule();
                        tempIntiString =  tempInti.toString();
			if (tempIntiString.equals(type)) {
				typ = unit.getType();
				trouve = true;
                                
                                if(type.equals("DISTANCE")){
                                    typeUnit = "1";
                                }else if(type.equals("SUPERFICIE")){
                                    typeUnit = "2";
                                } else if(type.equals("POIDS")){
                                    typeUnit = "3";
                                } else {
                                    typeUnit = "4";
                                }
			}
			cpt++;
		}
		
		boolean typeAjoute = false;

		if (trouve) {
			trouveSys = true;
		} else {
			System.out.println("Type inexistant");	
		}
		
                //add a unit
		String unite = "";
		if (trouveSys) {
			trouveSys = false;
			while (!trouveSys) {
				trouve = false;
				System.out.println("Quelle unite voulez-vous ajouter ?");
				unite = scan.nextLine();
				unite = unite.toLowerCase();
				cpt = 0;
                                tempUnit = syst.getUnit();
				while (!trouve && cpt < tempUnit.size()) {
					unit = tempUnit.get(cpt);
                                        tempName = unit.getNom();
					if (tempName.equals(unite)) {
						trouve = true;
					}
					cpt++;
				}

				if (!trouve) {
					trouveSys = true;
				} else {
					System.out.print("L'unite existe deja. ");
				}
			}
			
			String ajoutLigne = "";
			
                        String etalon = "";
                        trouve = false;
                        cpt = 0;
                        //retrieve the unit standard
                        for (Systeme liste : listeSystemes) {
                                tempUnit = liste.getUnit();
                                while (!trouve && cpt < tempUnit.size()) {
                                        unit = tempUnit.get(cpt);
                                        tempType = unit.getType();
                                        tempInti = tempType.getIntitule();
                                        tempIntiString = tempInti.toString();
                                        tempConv = unit.getConv();
                                        if (tempIntiString.equals(type) && tempConv.getValeur() == 1) {
                                                trouve = true;
                                                etalon = unit.getNom();
                                        }
                                        cpt++;
                                }
                        }

                        if (!trouve) {
                                System.out.println("Fichier corrompu. Pas d'unite etalon pour ce type");
                        } else {
                                // retrieve the conversion that contains
                                // the type of operation and value for conversion to the unit standard
                                trouveSys = false;
                                System.out.println("Quel type d'operation pour convertir vers l'unite " + etalon + " ? (MULTIPLICATION / ADDITION / COMPLEXE)");
                                String operation = scan.nextLine();
                                operation = operation.toUpperCase();
                                while (!trouveSys) {
                                        double valeur = 0;
                                        try {
                                                trouveSys = true;
                                                String typeCalc;
                                                if (operation.equals("COMPLEXE")) {
                                                        System.out.println("Premiere valeur pour convertir vers " + etalon + " ? (Addition)");
                                                        valeur = scan.nextDouble();
                                                        typeCalc = "3";
                                                        ajoutLigne = systeme + " ; " + unite + " ; " + typeUnit + " ; " + typeCalc + " ; " + String.valueOf(valeur);
                                                        System.out.println("Seconde valeur pour convertir vers " + etalon + " ? (Multiplication)");
                                                        valeur = scan.nextDouble();
                                                        ajoutLigne += "/" + String.valueOf(valeur);
                                                } else if (operation.equals("MULTIPLICATION") || operation.equals("ADDITION")) {
                                                        System.out.println("Combien faut-il pour faire 1" + etalon + " ?");
                                                        valeur = scan.nextDouble();
                                                        if(operation.equals("MULTIPLICATION") ){
                                                            typeCalc = "2";
                                                        } else{
                                                            typeCalc = "1";
                                                        }
                                                        ajoutLigne = systeme + " ; " + unite + " ; " + typeUnit + " ; " + typeCalc + " ; " + String.valueOf(valeur); 
                                                } else {
                                                        trouveSys = false;
                                                        System.out.println("Merci de choisir une operation existante");
                                                        System.out.println("Quel type d'operation pour convertir vers l'unite " + etalon + " ? (MULTIPLICATION / ADDITION / COMPLEXE)");
                                                        operation = scan.nextLine();
                                                        operation = operation.toUpperCase();
                                                }
                                                scan.nextLine();

                                        } catch (Exception e) {
                                                System.out.println("Merci de choisir une valeur entiere ou decimale (XX,XX)");
                                                scan.nextLine();
                                                trouveSys = false;
                                        }
                                }
                        }
                        //addition of the new unit in the current list that was loaded at the beginning
                        ajoutMemoire(ajoutLigne);
                        //addition of the new unit in the file
                        ajoutFichier(ajoutLigne);
		}
	}
	
         /**
	 * @param ajoutLigne is the line that contains the unit
         * addition of the new unit in the memory
	 */
	public static void ajoutMemoire(String ajoutLigne){
            int cpt;
            String ligneF0, ligneF1, ligneF2,ligneF3, ligneF4;
            Systeme syst;
            String name;
            String comp1, comp0;
            //list of systems
            for (cpt = 0 ; cpt < listeSystemes.size() ; cpt++) {
                String[] ligneFichier = ajoutLigne.split(" ; ");
                syst = listeSystemes.get(cpt);
                name = syst.getNom();
                ligneF0 = ligneFichier[0];
                //retrieve the system
                if (name.equals(ligneF0)) {
                    Conversion conv;
                    ligneF3 = ligneFichier[3];
                    ligneF4 = ligneFichier[4];
                    //retrieve the type of conversion
                    if (ligneF3.equals("3")) {                        
                        String[] complexe = ligneF4.split("/");
                        comp0 = complexe[0];
                        comp1 = complexe[1];
                        conv = new Conversion(Integer.parseInt(ligneF3),
                        Double.parseDouble(comp0),
                        Double.parseDouble(comp1));
                    } else {
                        conv = new Conversion(Integer.parseInt(ligneF3),
                        Double.parseDouble(ligneF4), 0);
                    }

                    ligneF2 = ligneFichier[2];
                    ligneF1 = ligneFichier[1];
                    //add the new objects to the types of units and the units
                    TypeUnite nouveauType = new TypeUnite(Integer.parseInt(ligneF2));
                    Unite nouvelleUnite = new Unite(ligneF1, nouveauType, conv);

                    listeSystemes.get(cpt).getUnit().add(nouvelleUnite);

               }

            }

         }
	
         /**
	 * @param ajoutLigne is the line that will be added in the file
         * add the unit in the file by writing in it
	 */
	public static void ajoutFichier(String ajoutLigne){
            String adressedufichier = "src/main/java/fr/miage/at/convert";
            try
            {
                FileWriter fwriter = new FileWriter(adressedufichier, true);
                BufferedWriter output = new BufferedWriter(fwriter);
                output.write("\n" + ajoutLigne);
                output.flush();
                output.close();
            } catch(IOException ioe) {
                System.out.print("Erreur : ");
                ioe.printStackTrace();
            }
        }

         /**
	 * @param scan read what the user types
         * display the existing units
	 */
        public static void affichage(Scanner scan){
            String reponse;
            int cptAffiche;
            int cpt;
            boolean trouveSys = false;
            String nomSys = "";
            Systeme syst;
            List<Unite> listeUnite;
            Unite unit;
            TypeUnite typeUnit;
            
            //retrieve system
            while (!trouveSys) {
                    System.out
                                    .println("Dans quel systeme fait partie l'unite que vous voulez afficher ? (METRIQUE / IMPERIAL / HORS-SYSTEME)");
                    reponse = scan.nextLine();

                    reponse = reponse.toUpperCase();
                    if (reponse.equals("METRIQUE") || reponse.equals("IMPERIAL")
                                    || reponse.equals("HORS-SYSTEME")) {
                            trouveSys = true;
                            nomSys = reponse;
                    } else {
                            System.out.println("Le systeme n'existe pas, veuillez entrer un systeme existant");
                    }
            }
            
            String listName;
            syst = null;
            for (Systeme liste : listeSystemes) {
                listName = liste.getNom();
                if (listName.equals(nomSys)) {
                        syst = liste;
                }
            }
            
            //display units 10 by 10
            int nonFin = 0;
            listeUnite = syst.getUnit();
            cptAffiche = listeUnite.size();
            for(cpt = 0; cpt < cptAffiche; cpt++){
                    unit = listeUnite.get(cpt);
                    typeUnit = unit.getType();
                    System.out.println(unit.getNom() + " : " + typeUnit.getIntitule());
                   
                    if((cpt+1)%10 == 0 && cpt != 0 && (cpt+1) != cptAffiche){
                        //asks the user if he wants to continue the display
                        System.out.println("Souhaitez-vous continuer l'affichage ?(O/N)");
                        reponse = scan.nextLine();
                        reponse = reponse.toUpperCase();
                        if(reponse.contains("N")){
                            cpt = cptAffiche;
                        }  
                    }
                }
            
            System.out.println("Fin de l'affichage");            
        }
        
}
