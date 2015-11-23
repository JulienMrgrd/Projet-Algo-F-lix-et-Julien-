package trieHybride;

import interfaces.IArbre;

import java.util.ArrayList;
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
	public void insererListeMots(List<String> mots) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insererPhrase(String phrase) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public boolean rechercherMot(String mot) {
		if (mot == null || mot.isEmpty()){
			return false;
		}
		if(mot.charAt(0)==this.clef){
			if(mot.length()==1){
				if(this.valeur==finDeMot){
					return true;
				}
				return false;
			}
			if (this.eq != null){
				return this.eq.rechercherMot(mot.substring(1, mot.length()));
			}
			return false;
		}
		if (mot.charAt(0)<this.clef){
			if(this.inf != null){
				return this.inf.rechercherMot(mot);
			}
			return false;
		}
		if (this.sup != null){
			return this.sup.rechercherMot(mot);
		}
		return false;
	}

	@Override
	public int comptageMots() {
		int cptInf = (this.inf == null)? 0 : this.inf.comptageMots();
		int cptEq = (this.eq == null)? 0 : this.eq.comptageMots();
		int cptSup = (this.sup == null)? 0 : this.sup.comptageMots();
		int cpt = (this.clef==finDeMot)? 1 : 0;
		return cpt + cptInf + cptEq + cptSup;
	}

	@Override
	public int comptageNil() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> listeMots() {
		List<String> listeMots = new ArrayList<>();
		this.listeMotsAvecLettresPrecedentes("",listeMots);
		return listeMots;
	}
	
	

	private void listeMotsAvecLettresPrecedentes(String lettrePrec, List<String> listeMots) {
		if(this.valeur == finDeMot){
			if(this.inf != null){
				this.inf.listeMotsAvecLettresPrecedentes(lettrePrec, listeMots);
			}
			listeMots.add(lettrePrec+this.clef);
			if (this.eq != null){
				this.eq.listeMotsAvecLettresPrecedentes(lettrePrec+this.clef, listeMots);
			}
			if (this.sup != null){
				this.sup.listeMotsAvecLettresPrecedentes(lettrePrec, listeMots);
			}
		} else {
			if(this.inf != null){
				this.inf.listeMotsAvecLettresPrecedentes(lettrePrec, listeMots);
			}
			if (this.eq != null){
				this.eq.listeMotsAvecLettresPrecedentes(lettrePrec+this.clef, listeMots);
			}
			if (this.sup != null){
				this.sup.listeMotsAvecLettresPrecedentes(lettrePrec, listeMots);
			}
		}
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
		if(mot == null || mot.isEmpty()) return 0;
		if(mot.charAt(0) == this.clef){
			if(this.eq != null){
				if (mot.length()==1){
					return this.eq.comptageMots();
				}
				return this.eq.prefixe(mot.substring(1, mot.length()));
			}
			return 0;
		}
		if (mot.charAt(0)<this.clef){
			if(this.inf != null){
				return this.inf.prefixe(mot);
			}
			return 0;
		}
		if(mot.charAt(0)>this.clef){
			return this.sup.prefixe(mot);
		}
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
