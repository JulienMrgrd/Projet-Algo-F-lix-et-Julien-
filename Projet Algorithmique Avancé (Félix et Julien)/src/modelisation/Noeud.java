package modelisation;

import java.util.Arrays;
import java.util.List;

public class Noeud {
	
	private final String etiquette;
    private final List<Noeud> enfants;
     
    /**
     * Construit un noeud étant données une étiquette et une liste d'enfants.
     * 
     * @param etiquette étiquette du noeud
     * @param enfants une liste contenant les enfants
     */
    public Noeud(String etiquette, List<Noeud> enfants) {
        this.etiquette = etiquette;
        this.enfants = enfants;
    }
 
    /**
     * Construit un noeud étant données une étiquette et des enfants tous
     * passés en paramètres.
     * 
     * @param etiquette étiquette du noeud
     * @param enfants les enfants
     */
    public Noeud(String etiquette, Noeud ... enfants) {
        this(etiquette, Arrays.asList(enfants));
    }
     
    /**
     * Renvoie l'enfant numéro i.
     * 
     * @param i numéro de l'enfant
     * @return l'objet Noeud correspondant
     */
    public Noeud enfantNumero(int i) {
        return enfants.get(i);
    }
     
    /**
     * Renvoie le numéro d'un enfant donné.
     * 
     * @param enfant l'enfant dont on veut connaître le numéro
     * @return le numéro de l'enfant, ou -1 si ce n'est pas un enfant
     */
    public int numeroEnfant(Noeud enfant) {
        return enfants.indexOf(enfant);
    }
     
    /**
     * Renvoie le nombre d'enfants du noeud courant.
     * 
     * @return le nombre d'enfants
     */
    public int nombreEnfants() {
        return enfants.size();
    }
     
    /**
     * Renvoie une représentation textuelle du noeud.
     * 
     * @return une chaîne répresentant le noeud
     */
    public String toString() {
        return etiquette;
    }
     
    /**
     * Renvoie une répresentation textuelle du sous-arbre correspondant à ce
     * noeud.
     * 
     * @return une chaîne representant le sous-arbre
     */
    public String sousArbre() {
        String res = etiquette + "(";
        for(Noeud enfant : enfants) res += enfant.sousArbre() + " ";
        return res + ")";
    }
}