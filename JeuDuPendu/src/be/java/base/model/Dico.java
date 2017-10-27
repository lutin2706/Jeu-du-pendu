package be.java.base.model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import be.java.base.gui.Console;

/*
 * Optimisation : insérer tous les nouveaux mots en même temps dans le fichier (writeAllLines d'une ArrayList)
 */
public class Dico {
	private static Dico instance;
	private final String PATH = "./Categories.txt";
	private HashMap<String, ArrayList<String>> dico;
	private Random random;
	
	private Dico() {
//		Path root;
//		File[] files = Files.list(root);
		ArrayList<String> categories = new ArrayList<>();
		// En premier, on retrouve les catégories dans un fichier
		if (Files.exists(Paths.get(PATH))) {
			try {
				categories = (ArrayList<String>) Files.readAllLines(Paths.get(PATH));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Ensuite, pour chaque catégorie, on retrouve les mots, on les stocke dans un arrayList, elle-même stockée dans la HashMap
		dico = new HashMap<>();
		for (String categorie : categories) {
			String path = "./" + categorie;
			if (Files.exists(Paths.get(path))) {
				ArrayList<String> mots = new ArrayList<>();
				try {
					mots = (ArrayList<String>) Files.readAllLines(Paths.get(path));
				} catch (IOException e) {
					e.printStackTrace();
				}
				dico.put(categorie, mots);
//			} else {
//				this.supprimerCategorie;
			}
		}
		random = new Random();
	}
	
	public static Dico getInstance() {
		if (instance == null) {
			instance = new Dico();
		}
		return instance;
	}

	private void ajouterCategorie(String categorie) {
		dico.put(categorie, new ArrayList<>());
		try {
			String ligne = (dico.keySet().size() > 1 ? System.lineSeparator() : "") + categorie;
			Files.write(Paths.get(PATH), ligne.getBytes(StandardCharsets.UTF_8) , 
					StandardOpenOption.APPEND, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void ajouterMot(String categorie, String mot) {
		dico.get(categorie).add(mot);
		try {
			String ligne = (dico.get(categorie).size() > 1 ? System.lineSeparator() : "") + mot;
			String path = "./" + categorie;
			Files.write(Paths.get(path), ligne.getBytes(StandardCharsets.UTF_8) , 
					StandardOpenOption.APPEND, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void supprimerCategorie() {
		
	}
	
	public String donneMot(String categorie) {
		ArrayList<String> mots = dico.get(categorie);
		String mot = null;
		if (mots.size() > 0)
			mot = mots.remove(random.nextInt(mots.size()));
		return mot;
	}
	
	public void encodeMots() {
		boolean encoderMots = true;
		int choix;
		do {
			choix = Console.afficheMenuDico();
			String nomCategorie;
			switch (choix) {
			case 1: // Créer une nouvelle catégorie
				nomCategorie = Console.nouvelleCategorie();
				ajouterCategorie(nomCategorie);
				break;
			case 2: // Utiliser une catégorie existante
				nomCategorie = Console.choisirCategorie(dico.keySet());
				boolean encodageEnCours = true;
				do {
					String mot = Console.encoderMot();
					if (!mot.equals("q") && !mot.equals("Q"))	
						ajouterMot(nomCategorie, mot);
					else
						encodageEnCours = false;
				} while (encodageEnCours);
				break;
			case 9: // Revenir au menu précédent
				encoderMots = false;
				break;
			}
		} while (encoderMots);
	}

	public Set<String> getCategories() {
		return dico.keySet();
	}
}
