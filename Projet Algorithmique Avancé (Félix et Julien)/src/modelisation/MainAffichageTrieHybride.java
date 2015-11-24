package modelisation;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;

import trieHybride.TrieHybride;

public class MainAffichageTrieHybride {

	public static void main(String[] args) {
		//Ce briandais est le dernier généré lors des Tests
//		TrieHybride racinePourAffichage = new TrieHybride('/');
		TrieHybride debutDico = new TrieHybride();
//		racinePourAffichage.setEq(debutDico);
		debutDico.insererMot("bonjour");
		debutDico.insererMot("hello");
		debutDico.insererMot("bonsoir");
		
		final TreeModel modele = new AdaptateurOfTrieHybride(debutDico);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// création et affichage de la fenetre avec un JTree affichant
				// les données de 'arbre'
				// NB : le JTree fait simplement référence au modèle
				JFrame fenetre = new JFrame("Affichage d'un Trie Hybride");
				fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fenetre.add(new JScrollPane(new JTree(modele)),
						BorderLayout.CENTER);
				fenetre.setSize(400, 600);
				fenetre.setVisible(true);
			}
		});
	}

}