package be.java.base.gui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Console {
	static Scanner scanner= new Scanner(System.in);

	public static int afficheMenu() {
		System.out.println("Que voulez-vous faire ? [1]Entrer des mots dans le dictionnaire [2]Jouer au pendu [9]Sortir de l'application");
		return Integer.parseInt(scanner.nextLine());
	}

	public static char afficheJeu(char[] mot, int tentativesRestantes) {
//		nettoieConsole();
		afficheMotATrouver(mot);
		System.out.println("Tentatives restantes: " + tentativesRestantes);
		
		System.out.println("Quelle lettre proposez-vous ?");
		char car = scanner.nextLine().charAt(0);
		
		return car;
	}
	
	public static String encoderMot() {
		System.out.println("Entrez le mot à ajouter au dictionnaire ou [Q]Quitter:");
		return scanner.nextLine();
	}

	public static int afficheMenuDico() {
		nettoieConsole();
		System.out.println("Que voulez-vous faire ? [1]Créer une nouvelle catégorie [2]Utiliser une catégorie existante "
				+ "[9]Revenir au menu précédent");
		return Integer.parseInt(scanner.nextLine());
	}

	private static void afficheMotATrouver(char[] mot) {
		String motAffiche = "";
		for (char car : mot) {
			if (Character.isLetter(car)) 
				motAffiche += " " + String.valueOf(car) + " ";
			else
				motAffiche += " _ ";
		}
		System.out.println(motAffiche);
	}

	// Affiche des petites phrases
	public static void affichePerdu() {
		System.out.println("PERDU ! Vous avez atteint le nombre maximum de tentatives");
	}

	public static void afficheGagne(String mot) {
		System.out.println("GAGNE ! Le mot à trouver était bien " + mot);
	}

	public static void afficheBonneLettre() {
		System.out.println("Bravo ! La lettre est dans le mot à trouver !");		
	}

	public static void afficheMauvaiseLettre(char lettreProposee) {
		System.out.println("Dommage ! La lettre " + lettreProposee + " n'est pas dans le mot à trouver");
	}
	
	public static void nettoieConsole() {
		String clean = "";
		for (int i = 0; i < 50; i++) {
			clean += "\n";
		}
		System.out.println(clean);
	}

	public static void direAuRevoir() {
		System.out.println("Au revoir et à bientôt dans notre application");
	}

	public static String nouvelleCategorie() {
		System.out.println("Quel est le nom de votre catégorie ?");
		return scanner.nextLine();
	}

	public static String choisirCategorie(Set<String> set) {
		String display = "";
		int i = 1;
		ArrayList<String> categories = new ArrayList<>();
		for (String categorie : set) {
			display += i + ". " + categorie + "\n";
			categories.add(categorie);
			i++;
		}
		System.out.println(display);
		System.out.println("Quelle catégorie choisissez-vous ?");
		return categories.get(Integer.parseInt(scanner.nextLine())-1);
	}

	public static boolean afficheRejouer() {
		System.out.println("Voulez-vous rejouer ? [O/N]");
		char resp = scanner.nextLine().charAt(0);
		return (resp == 'O' || resp == 'o')?true:false;
	}

	public static void afficheLettreDejaProposee(char lettreProposee) {
		System.out.println("La lettre '" + lettreProposee + "' a déjà été proposée.");
	}

	public static void affichePlusDeMots() {
		System.out.println("Il n'y a plus de mots dans la catégorie choisie, retour au menu principal");
	}

}
