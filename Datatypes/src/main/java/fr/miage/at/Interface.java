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

		String rep = "";
		double val = 0.0;
		int i, j;

		Montant valDep;
		Montant valArr = new Montant();
		Unite uniteDep = new Unite();
		Unite uniteArr = new Unite();

		Scanner scan = new Scanner(System.in);
		System.out.println("=========================================");
		System.out.println("========== Conversion d'unités ==========");
		System.out.println("=========================================");

		boolean ok = false;
		while (!ok) {
			System.out.print("Unité de départ : ");
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
				System.out.println("Unité inexistante");
			}
		}

		System.out.print("Valeur de l'unité de départ : ");
		val = scan.nextDouble();
		scan.nextLine();
		valDep = new Montant(val, uniteDep);

		ok = false;
		while (!ok) {
			System.out.print("Unité d'arrivée : ");
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
				System.out.println("Unité inexistante");
			}
		}

		valArr.setUnite(uniteArr);
		valArr.convert(valDep);

		System.out.println("Valeur de l'unité d'arrivée : "
				+ valArr.getValeur());

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

				Conversion conv = new Conversion(ligneFichier[3],
						Double.parseDouble(ligneFichier[4]));
				Type nouveauType = new Type(ligneFichier[2]);
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

			int j;
			for (i = 0; i < listeSystemes.size(); i++) {
				System.out.println(listeSystemes.get(i).getNom());
				for (j = 0; j < listeSystemes.get(i).getUnit().size(); j++) {
					System.out.println(listeSystemes.get(i).getUnit().get(j)
							.getNom());
				}
			}

			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
