package be.java.base.test;

import be.java.base.gui.Console;
import be.java.base.model.Dico;

public class Tester {
	Dico dico;
	
	public Tester() {
		dico = Dico.getInstance();
	}
	
//	public void testDico() {
//	// Ajoute des mots au dico
//	dico.ajouterMot("souris");
//	dico.ajouterMot("chat");
//	
//	System.out.println("Le dico nous donne un mot au hasard :" + dico.donneMot());
//	System.out.println("Le dico nous donne un autre mot au hasard :" + dico.donneMot());
//	System.out.println("Le dico nous donne un 3ème mot au hasard :" + dico.donneMot());
//	}

	public void testConsole() {
		char[] display = new char[6];
		Console.afficheJeu(display, 5);
		display[1] = 'o';
		display[0] = 's';
		display[5] = 's';
		Console.afficheJeu(display, 4);
	}
}
