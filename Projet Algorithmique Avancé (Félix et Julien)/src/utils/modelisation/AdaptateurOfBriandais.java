package utils.modelisation;

import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import metier.ArbreBriandais;
 
/**
 * 
 */
public class AdaptateurOfBriandais implements TreeModel {
    private final ArbreBriandais racine;
     
    /**
     * Construit un nouvel adaptateur pour l'arbre dont on donne la racine.
     * 
     * @param racine la racine de l'arbre
     */
    public AdaptateurOfBriandais(ArbreBriandais racine) {
        this.racine = racine;
    }
     
    public Object getRoot() {
        return racine;
    }
     
    public Object getChild(Object parent, int index) {
        if(parent != null && parent instanceof ArbreBriandais) {
            ArbreBriandais fils = ((ArbreBriandais)parent).getFils();
            if(fils!=null){
            	List<ArbreBriandais> freres = fils.getAllFreres();
            	if(!freres.isEmpty() && freres.get(index)!=null) return freres.get(index);
            }
            return null;
        } else return null;
    }
 
    public int getChildCount(Object parent) {
        if(parent != null && parent instanceof ArbreBriandais) {
        	ArbreBriandais fils = ((ArbreBriandais)parent).getFils();
            if(fils!=null) return fils.getAllFreres().size();
            else return 0;
        }  else return 0;
    }
 
    public boolean isLeaf(Object node) {
        return getChildCount(node) == 0;
    }
 
    public void valueForPathChanged(TreePath path, Object newValue) {
        // pas implementÃ©
    }
 
    public int getIndexOfChild(Object parent, Object child) {
        if(parent != null && parent instanceof ArbreBriandais && child instanceof ArbreBriandais) {
        	ArbreBriandais fils = ((ArbreBriandais)parent).getFils();
            if(fils!=null){
            	List<ArbreBriandais> freres = fils.getAllFreres();
            	if(!freres.isEmpty() && freres.indexOf(child)!=-1) return freres.indexOf(child);
        	}
        	return -1;
        } else return -1;
    }
 
    public void addTreeModelListener(TreeModelListener l) {
        // pas implÃ©mentÃ©
    }
 
    public void removeTreeModelListener(TreeModelListener l) {
        // pas implÃ©mentÃ©
    }
}
