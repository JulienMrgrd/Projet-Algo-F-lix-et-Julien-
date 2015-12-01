package utils.modelisation;

import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import metier.TrieHybride;
 
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
        if(parent != null && parent instanceof TrieHybride && ((TrieHybride) parent).getClef()!='.') {
            List<TrieHybride> list = ((TrieHybride) parent).getAllFilsExistants();
            if(list.get(index)!=null) return list.get(index);
        }
        return null;
    }
 
    public int getChildCount(Object parent) {
        if(parent != null && parent instanceof TrieHybride 
        		&& ((TrieHybride) parent).getClef()!='.' && !((TrieHybride) parent).isLeaf()) {
        	return 3;
        }
        return 0;
    }
 
    public boolean isLeaf(Object node) {
        return getChildCount(node) == 0;
    }
 
    public void valueForPathChanged(TreePath path, Object newValue) {
        // pas implementÃ©
    }
 
    public int getIndexOfChild(Object parent, Object child) {
      if(parent != null && child != null && parent instanceof TrieHybride && child instanceof TrieHybride) {
          	List<TrieHybride> freres = ((TrieHybride) parent).getAllFilsExistants();
          	if(freres.indexOf(child)!=-1) return freres.indexOf(child);
      }
      return -1;
    }
 
    public void addTreeModelListener(TreeModelListener l) {
        // pas implÃ©mentÃ©
    }
 
    public void removeTreeModelListener(TreeModelListener l) {
        // pas implÃ©mentÃ©
    }
}
