package modelisation;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

import arbreBriandais.ArbreBriandais;
import trieHybride.TrieHybride;
import utils.FileUtils;

public class MainAffichageTrieHybride {

	public static void main(String[] args) {
		displayClassicTrie();
		//displayShakespeareTrie();
	}

	private static void displayShakespeareTrie() {
		TrieHybride racinePourAffichage = new TrieHybride('/');
		TrieHybride debutDico = new TrieHybride();
		racinePourAffichage.setEq(debutDico);
		debutDico.insererListeMots(FileUtils.getListMotsShakespeareSansDoublons());

		final TreeModel modele = new AdaptateurOfTrieHybride(
				racinePourAffichage);
		final JTree tree = new JTree(modele);
		DefaultTreeCellRenderer renderer = new MyRenderer();
		tree.setCellRenderer(renderer);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame fenetre = new JFrame("Trie Hybride avec Shakespeare");
				fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fenetre.add(new JScrollPane(tree), BorderLayout.CENTER);
				fenetre.setSize(400, 600);
				fenetre.setVisible(true);
			}
		});
	}

	private static void displayClassicTrie() {

		TrieHybride racinePourAffichage = new TrieHybride('/');
		ArbreBriandais debutDico2 = new ArbreBriandais();
		//racinePourAffichage.setEq(debutDico2);
		debutDico2.insererMot("bonjour");
		debutDico2.insererMot("hello");
		debutDico2.insererMot("annee");
		debutDico2.insererMot("bonsoir");
		debutDico2.insererMot("bonsoirs");
		System.out.println(debutDico2.listeMots());
		TrieHybride debutDico = new TrieHybride();
		
		debutDico=debutDico2.conversion();
		racinePourAffichage.setEq(debutDico);
		
		final TreeModel modele = new AdaptateurOfTrieHybride(
				racinePourAffichage);
		final JTree tree = new JTree(modele);
		DefaultTreeCellRenderer renderer = new MyRenderer();
		tree.setCellRenderer(renderer);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame fenetre = new JFrame("Trie Hybride avec 5 mots");
				fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fenetre.add(new JScrollPane(tree), BorderLayout.CENTER);
				fenetre.setSize(400, 600);
				fenetre.setVisible(true);
			}
		});
	}

	/**
	 * Returns an ImageIcon, or null if the path was invalid.
	 * 
	 * @throws FileNotFoundException
	 */
	protected static ImageIcon createImageIcon(String path) {
		URL ressource = MainAffichageTrieHybride.class.getClass().getResource(
				path);
		if (ressource != null) {
			return new ImageIcon(ressource);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}