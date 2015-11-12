package modelisation;
 
import java.awt.BorderLayout;
 
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;
 
 
public class Main {
	
    public static void main(String[] args) {
        // creation de l'arbre, uniquement à l'aide de la classe Noeud
        final Noeud arbre = new Noeud("Langue", 
                new Noeud("Asiatique", new Noeud("Mandarin"), new Noeud("Japonais")),
                new Noeud("Indo-Européenne", new Noeud("Latine", 
                        new Noeud("Francais"), new Noeud("Espagnol")), new Noeud("Germanique")));
         
        // affichage
        System.out.println(arbre.sousArbre());
         
        // création de l'adaptateur, pour avoir un TreeModel sur l'arbre
        final TreeModel modele = new Adaptateur(arbre);
         
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