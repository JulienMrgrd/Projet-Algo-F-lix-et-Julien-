package modelisation;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;

import arbreBriandais.ArbreBriandais;

public class MainAffichage {

	private static String textExo1 = "A quel genial professeur de dactylographie sommes nous redevables de la superbe " 
			+ "phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait " 
			+ " appel a chacune des touches du clavier de la machine a ecrire ?";
	
	public static void main(String[] args) {

		//Ce briandais est le dernier généré lors des Tests
		ArbreBriandais racinePourAffichage = new ArbreBriandais('/');
		ArbreBriandais debutDico = new ArbreBriandais();
		racinePourAffichage.setFils(debutDico);
		debutDico.insererPhrase(textExo1);
		
		final TreeModel modele = new AdaptateurOfBriandais(racinePourAffichage);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// création et affichage de la fenetre avec un JTree affichant
				// les données de 'arbre'
				// NB : le JTree fait simplement référence au modèle
				JFrame fenetre = new JFrame("Test arbres");
				fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fenetre.add(new JScrollPane(new JTree(modele)),
						BorderLayout.CENTER);
				fenetre.setSize(400, 600);
				fenetre.setVisible(true);
			}
		});
	}
}