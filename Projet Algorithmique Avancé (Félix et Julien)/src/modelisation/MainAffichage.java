package modelisation;

import java.awt.BorderLayout;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;

import arbreBriandais.ArbreBriandais;

public class MainAffichage {

	public static void main(String[] args) {

		InputStream file;
		try {
			file = new FileInputStream( "documents/arbreBriandais.bin" );
			InputStream buffer = new BufferedInputStream( file );
			ObjectInput input = new ObjectInputStream ( buffer );
			ArbreBriandais racine = (ArbreBriandais)input.readObject();
			
			input.close();
			buffer.close();
			file.close();
			
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
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}