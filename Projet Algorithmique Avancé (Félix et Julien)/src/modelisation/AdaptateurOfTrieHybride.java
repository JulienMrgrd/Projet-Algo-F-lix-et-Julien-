package modelisation;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import trieHybride.TrieHybride;
 
/**
 * 
 */
public class AdaptateurOfTrieHybride implements TreeModel {
    private final TrieHybride racine;
     
    /**
     * Construit un nouvel adaptateur pour l'arbre dont on donne la racine.
     * 
     * @param racine la racine de l'arbre
     */
    public AdaptateurOfTrieHybride(TrieHybride racine) {
        this.racine = racine;
    }
     
    public Object getRoot() {
        return racine;
    }
     
    public Object getChild(Object parent, int index) {
        if(parent != null && parent instanceof TrieHybride) {
            switch (index) {
			case 1:
				return ((TrieHybride) parent).getInf();
			case 2:
				return ((TrieHybride) parent).getEq();
			case 3:
				return ((TrieHybride) parent).getSup();

			default:
				return null;
			}
        } else return null;
    }
 
    public int getChildCount(Object parent) {
        if(parent != null && parent instanceof TrieHybride) {
        	int cpt = 0;
        	if (((TrieHybride)parent).getInf() != null) cpt++;
        	if (((TrieHybride)parent).getEq() != null) cpt++;
        	if (((TrieHybride)parent).getSup() != null) cpt++;
        	return cpt;
        }  else return 0;
    }
 
    public boolean isLeaf(Object node) {
        return getChildCount(node) == 0;
    }
 
    public void valueForPathChanged(TreePath path, Object newValue) {
        // pas implementé
    }
 
    public int getIndexOfChild(Object parent, Object child) {
    	return 0;
//      if(parent != null && parent instanceof TrieHybride && child instanceof TrieHybride) {
//      	TrieHybride fils = ((TrieHybride)parent).getFils();
//          if(fils!=null){
//          	List<TrieHybride> freres = fils.getAllFreres();
//          	if(!freres.isEmpty() && freres.indexOf(child)!=-1) return freres.indexOf(child);
//      	}
//      	return -1;
//      } else return -1;
    }
 
    public void addTreeModelListener(TreeModelListener l) {
        // pas implémenté
    }
 
    public void removeTreeModelListener(TreeModelListener l) {
        // pas implémenté
    }
}
