package modelisation;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
 
/**
 * 
 */
public class Adaptateur implements TreeModel {
    private final Noeud racine;
     
    /**
     * Construit un nouvel adaptateur pour l'arbre dont on donne la racine.
     * 
     * @param racine la racine de l'arbre
     */
    public Adaptateur(Noeud racine) {
        this.racine = racine;
    }
     
    public Object getRoot() {
        return racine;
    }
     
    public Object getChild(Object parent, int index) {
        if(parent != null && parent instanceof Noeud) {
            return ((Noeud)parent).enfantNumero(index);
        } else return null;
    }
 
    public int getChildCount(Object parent) {
        if(parent != null && parent instanceof Noeud) {
            return ((Noeud)parent).nombreEnfants();
        }  else return 0;
    }
 
    public boolean isLeaf(Object node) {
        return getChildCount(node) == 0;
    }
 
    public void valueForPathChanged(TreePath path, Object newValue) {
        // pas implementé
    }
 
    public int getIndexOfChild(Object parent, Object child) {
        if(parent != null && parent instanceof Noeud && child instanceof Noeud) {
            return ((Noeud)parent).numeroEnfant((Noeud)child);
        } else return -1;
    }
 
    public void addTreeModelListener(TreeModelListener l) {
        // pas implémenté
    }
 
    public void removeTreeModelListener(TreeModelListener l) {
        // pas implémenté
    }
}
