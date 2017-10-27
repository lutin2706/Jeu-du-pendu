package be.java.base;

import static be.java.base.gui.Console.*;
import be.java.base.model.Dico;
import be.java.base.model.Jeu;
import be.java.base.test.Tester;

public class Main {

	public static void main(String[] args) {
		Dico dico = Dico.getInstance();

		Tester test = new Tester();
		//		test.testDico();
		//		test.testConsole();
		boolean enCours = true;
		do {
//			Console.nettoieConsole();
			int choix = afficheMenu();

			switch (choix) {
			case 1: // Ajoute des mots au dico
				dico.encodeMots();
				break;
			case 2: // Joue au pendu
				String categorie = choisirCategorie(dico.getCategories());
				Jeu jeu = new Jeu();
				boolean rejouer = true;
				do {
					String motATrouver = dico.donneMot(categorie);
					if (motATrouver != null) {
						jeu.joue(motATrouver);
						rejouer = afficheRejouer(); // Proposer de changer de catégorie ou de garder la même (voire proposer une catégorie aléatoire)
					} else {
						affichePlusDeMots();
						rejouer = false;
					}
				}
				while (rejouer);
				break;
			case 9: // Sort de l'application
				enCours = false;
				direAuRevoir();
				break;
			}
		} while (enCours);
	}

}
