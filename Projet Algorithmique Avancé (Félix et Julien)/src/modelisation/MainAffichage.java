package modelisation;
 
import java.awt.BorderLayout;
 


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;

import arbreBriandais.ArbreBriandais;
 
 
public class MainAffichage {
	
    public static void main(String[] args) {
        
        ArbreBriandais arbreBri = new ArbreBriandais('\0', null, new ArbreBriandais('b',null,null));
        ArbreBriandais fils = arbreBri.getFils();
        fils.insererLettre('e');
        fils.insererLettre('a');
        fils.insererLettre('r');
        fils.insererLettre('a');
        fils.insererLettre('z');
        fils.insererLettre('h');
        ArbreBriandais filsR = arbreBri.getFilsByChar('r');
        filsR.setFils(new ArbreBriandais('i', null, new ArbreBriandais('z', null, null)));

        ArbreBriandais filsA = arbreBri.getFilsByChar('a');
        filsA.setFils(new ArbreBriandais('k', null, new ArbreBriandais('a', null, null)));

        
        // création de l'adaptateur, pour avoir un TreeModel sur l'arbre
        final TreeModel modele = new AdaptateurOfBriandais(arbreBri);
         
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // création et affichage de la fenetre avec un JTree affichant les
                // données de 'arbre'
                // NB : le JTree fait simplement référence au modèle
                JFrame fenetre = new JFrame("Test arbres");
                fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fenetre.add(new JScrollPane(new JTree(modele)), BorderLayout.CENTER);
                fenetre.pack();
                fenetre.setVisible(true);
            }
        });
    }
}