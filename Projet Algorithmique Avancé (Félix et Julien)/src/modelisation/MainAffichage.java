package modelisation;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;

import utils.FileUtils;
import arbreBriandais.ArbreBriandais;

public class MainAffichage {

	public static void main(String[] args) {

		ArbreBriandais racine = FileUtils.briandaisFromFile();
		final TreeModel modele = new AdaptateurOfBriandais(racine);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// création et affichage de la fenetre avec un JTree affichant
				// les
				// données de 'arbre'
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