package interfaces;

import java.util.List;

public interface ITrie {
	
	/**
	 * Insère le nouveau mot dans l'arbre, 
	 * avec un Arbre vide (cle = '\0') à la fin
	 * 
	 */
	public void insererMot(String mot);
	
	/**
	 * Insère la nouvelle phrase (phrase = suite de mots) dans l'arbre, 
	 * avec un Arbre vide (cle = '\0') à la fin
	 * 
	 */
	public void insererPhrase(String phrase);

	public void insererListeMots(List<String> mots);
	
	/**
	 * Retourne vrai si le mot est contenu dans l'arbre
	 */
	public boolean rechercherMot(String mot);
	
	public int comptageMots();
	
	public int comptageNil();
	
	public List<String> listeMots();
	
	public int hauteur();
	
	public double profondeurMoyenne();
	
	public int prefixe(String mot);
	
	public void suppression(String mot);
	
	public ITrie conversion();

}
