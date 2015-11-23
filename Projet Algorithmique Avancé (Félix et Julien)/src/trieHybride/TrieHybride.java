package trieHybride;

import interfaces.IArbre;

import java.util.List;

import arbreBriandais.ArbreBriandais;
import utils.UtilitaireMots;

public class TrieHybride implements IArbre {

	private char clef; // '\0' sera le caractère de fin de mot
	private char valeur;
	private TrieHybride inf;
	private TrieHybride eq;
	private TrieHybride sup;
	public static char finDeMot = '\0';
	private static char init = '_';

	public TrieHybride() {
		this(init);
	}

	public TrieHybride(char character) {
		this.clef = character; 	//clef = la lettre
		this.valeur = ' ';		//valeur sert a savoir si c'est la fin d'un mot ou pas, si on est a la derniere lettre d'un mot valeur ='\0'
		this.inf = null;	
		this.eq = null;
		this.sup = null;
	}

	/**
	 * Constructeur par copie
	 * 
	 * @param briandais
	 */
	public TrieHybride(TrieHybride hybride) {
		this.clef = hybride.clef;
		this.valeur = hybride.valeur;
		this.inf = hybride.inf;
		this.eq = hybride.eq;
		this.sup = hybride.sup;
	}

	public TrieHybride(char clef, char valeur, TrieHybride inf, TrieHybride eq, TrieHybride sup) {
		this.clef = clef;
		this.valeur = valeur;
		this.inf = inf;
		this.eq = eq;
		this.sup = sup;
	}

	@Override
	public void insererMot(String mot) {
		// TODO Auto-generated method stub
	}

	@Override
	public void insererPhrase(String phrase) {
		// TODO Auto-generated method stub

	}

	
	// TODO C'est moche faut la refaire
	@Override
	public boolean rechercherMot(String mot) {
		if (mot == null || mot.isEmpty())
			return false;

		char first = mot.charAt(0);
		if (first == this.clef) {
			if (mot.length() == 1) {
				if (this.valeur == finDeMot) {
					return true;
				}
				return false;
			}
			if (this.inf != null) {
				if (this.eq != null) {
					if (this.sup != null) {
						return this.inf.rechercherMot(mot) || this.eq.rechercherMot(mot) || this.sup.rechercherMot(mot);
					}
					return this.inf.rechercherMot(mot) || this.eq.rechercherMot(mot);
				}
				if (this.sup != null) {
					return this.inf.rechercherMot(mot) || this.sup.rechercherMot(mot);
				}
				return this.inf.rechercherMot(mot);
			} 
			else {
				if (this.eq != null) {
					if (this.sup != null) {
						return this.eq.rechercherMot(mot) || this.sup.rechercherMot(mot);
					}
					return this.eq.rechercherMot(mot);
				}
				if (this.sup != null) {
					return this.sup.rechercherMot(mot);
				}
				return false;
			}
		}
		return false; // TODO : vérifier
		
				
	}

	@Override
	public int comptageMots() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int comptageNil() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> listeMots() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hauteur() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int profondeurTotale() { // TODO: private
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int profondeurMoyenne() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int prefixe(String mot) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void suppression(String mot) {
		// TODO Auto-generated method stub

	}

	public char getClef() {
		return clef;
	}

	public void setClef(char clef) {
		this.clef = clef;
	}

	public TrieHybride getInf() {
		return inf;
	}

	public void setInf(TrieHybride inf) {
		this.inf = inf;
	}

	public TrieHybride getEq() {
		return eq;
	}

	public void setEq(TrieHybride eq) {
		this.eq = eq;
	}

	public TrieHybride getSup() {
		return sup;
	}

	public void setSup(TrieHybride sup) {
		this.sup = sup;
	}

	@Override
	public void fusion(IArbre briandais) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IArbre conversion() {
		// TODO Auto-generated method stub
		return null;
	}

}
