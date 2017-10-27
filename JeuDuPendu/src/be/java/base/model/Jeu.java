package be.java.base.model;

import java.util.ArrayList;

import be.java.base.gui.Console;

/*
 * Optimisation : tenir compte des mots composés (retirer espaces et traits d'union des lettres à chercher)
 */
public class Jeu {
	private int tentativesMax;
	private int tentatives;

	public Jeu() {
		tentativesMax = 3;
	}

	public void joue(String mot) {
		tentatives = 0;
		char[] motATrouverChar = mot.toCharArray();
		boolean enCours = true;
		int nbreLettresTrouvees = 0;
		char[] lettresTrouvees = new char[motATrouverChar.length];
		ArrayList<Character> lettresProposees = new ArrayList<>();

		do {
			if (tentatives >= tentativesMax) {
				enCours = false;
				Console.affichePerdu();
			} else {
				char lettreProposee = Console.afficheJeu(lettresTrouvees, tentativesMax - tentatives);
				if (lettresProposees.contains(lettreProposee))
					Console.afficheLettreDejaProposee(lettreProposee);
				else {
					lettresProposees.add(lettreProposee);
					nbreLettresTrouvees += compteLettresTrouvees(lettreProposee, motATrouverChar, lettresTrouvees);

					if (nbreLettresTrouvees == 0)
						tentatives++;

					if (nbreLettresTrouvees == motATrouverChar.length) {
						enCours = false;
						Console.afficheGagne(mot);
					}
				}
			}
		} while (enCours);
	}

	private int compteLettresTrouvees(char lettreProposee, char[] motATrouverChar, char[] lettresTrouvees) {
		int nbreLettresTrouvees = 0;
		for (int i = 0; i < motATrouverChar.length; i++) {
			if (lettreProposee == motATrouverChar[i]) {
				lettresTrouvees[i] = lettreProposee;
				nbreLettresTrouvees++;
			}
		}
		if (nbreLettresTrouvees > 0)
			Console.afficheBonneLettre();
		else {
			Console.afficheMauvaiseLettre(lettreProposee);
			tentatives++;
		}
		return nbreLettresTrouvees;
	}

}
