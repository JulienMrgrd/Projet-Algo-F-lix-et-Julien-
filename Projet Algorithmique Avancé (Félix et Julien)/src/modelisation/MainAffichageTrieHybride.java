package modelisation;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

import trieHybride.TrieHybride;
import utils.FileUtils;

public class MainAffichageTrieHybride {

	public static void main(String[] args) {
		displayClassicTrie();
		displayShakespeareTrie();
	}

	private static void displayShakespeareTrie() {
		// Ce briandais est le dernier gÃ©nÃ©rÃ© lors des Tests
		TrieHybride racinePourAffichage = new TrieHybride('/');
		TrieHybride debutDico = new TrieHybride();
		racinePourAffichage.setEq(debutDico);
		debutDico.insererListeMots(FileUtils.getListMotsShakespeareSansDoublons());

		final TreeModel modele = new AdaptateurOfTrieHybride(
				racinePourAffichage);
		JTree tree = new JTree(modele);
		DefaultTreeCellRenderer renderer = new MyRenderer();
		tree.setCellRenderer(renderer);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				System.out.println();
			}
		});

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// crÃ©ation et affichage de la fenetre avec un JTree affichant
				// les donnÃ©es de 'arbre'
				// NB : le JTree fait simplement rÃ©fÃ©rence au modÃ¨le
				JFrame fenetre = new JFrame("Trie Hybride avec Shakespeare");
				fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fenetre.add(new JScrollPane(tree), BorderLayout.CENTER);
				fenetre.setSize(400, 600);
				fenetre.setVisible(true);
			}
		});
	}

	private static void displayClassicTrie() {
		// Ce briandais est le dernier gÃ©nÃ©rÃ© lors des Tests
		TrieHybride racinePourAffichage = new TrieHybride('/');
		TrieHybride debutDico = new TrieHybride();
		racinePourAffichage.setEq(debutDico);
		debutDico.insererMot("bonjour");
		debutDico.insererMot("hello");
		debutDico.insererMot("annee");
		debutDico.insererMot("bonsoir");

		final TreeModel modele = new AdaptateurOfTrieHybride(
				racinePourAffichage);
		JTree tree = new JTree(modele);
		DefaultTreeCellRenderer renderer = new MyRenderer();
		tree.setCellRenderer(renderer);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				System.out.println();
			}
		});

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// crÃ©ation et affichage de la fenetre avec un JTree affichant
				// les donnÃ©es de 'arbre'
				// NB : le JTree fait simplement rÃ©fÃ©rence au modÃ¨le
				JFrame fenetre = new JFrame("Trie Hybride avec 4 mots");
				fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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