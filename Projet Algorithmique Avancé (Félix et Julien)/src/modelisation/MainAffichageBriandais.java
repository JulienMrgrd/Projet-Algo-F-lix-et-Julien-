package modelisation;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;

import arbreBriandais.ArbreBriandais;
import trieHybride.TrieHybride;
import utils.FileUtils;

public class MainAffichageBriandais {

	public static void main(String[] args) {
		displayClassicBriandais();
		//displayShakespeareBriandais();

	}

	private static void displayShakespeareBriandais() {
		ArbreBriandais racinePourAffichage = new ArbreBriandais('/');
		ArbreBriandais debutDico = new ArbreBriandais();
		racinePourAffichage.setFils(debutDico);
		List<String> motsShakespeare = FileUtils
				.getListMotsShakespeareSansDoublons();
		debutDico.insererListeMots(motsShakespeare);

		final TreeModel modele = new AdaptateurOfBriandais(racinePourAffichage);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame fenetre = new JFrame(
						"Arbre de la Briandais avec Shakespeare");
				fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fenetre.add(new JScrollPane(new JTree(modele)),
						BorderLayout.CENTER);
				fenetre.setSize(400, 600);
				fenetre.setVisible(true);
			}
		});
	}

	private static void displayClassicBriandais() {
		ArbreBriandais racinePourAffichage = new ArbreBriandais('/');
		TrieHybride debutDico = new TrieHybride();
		//racinePourAffichage.setEq(debutDico);
		debutDico.insererMot("balles");
		debutDico.insererMot("balle");
		debutDico.insererMot("bowling");
		debutDico.insererMot("thym");
		debutDico.insererMot("algav");
		
		ArbreBriandais debutDico2 = debutDico.conversion();
		racinePourAffichage.setFils(debutDico2);
		final TreeModel modele = new AdaptateurOfBriandais(racinePourAffichage);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame fenetre = new JFrame(
						"Arbre de la Briandais classique");
				fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fenetre.add(new JScrollPane(new JTree(modele)),
						BorderLayout.CENTER);
				fenetre.setSize(400, 600);
				fenetre.setVisible(true);
			}
		});
	}

}