package trieHybride;

import interfaces.IArbre;

import java.util.ArrayList;
import java.util.List;

import utils.OrdreLettre;
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
	 * @param hybride
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
		if (mot != null && !mot.isEmpty()) {
			mot = UtilitaireMots.toMinuscule(mot);
			char[] caracteres = UtilitaireMots.motToChars(mot);
			TrieHybride trieNewLetter = this.insererLettre(caracteres[0]);

			TrieHybride trie_tmp = trieNewLetter;
			if (caracteres.length > 1) {
				for (int i = 1; i < caracteres.length; i++) {
					trie_tmp = trie_tmp.insererLettreCommeSuite(caracteres[i]);
				}
			}

			trie_tmp.insererLettreCommeSuite(finDeMot);
		}
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
	
	
	/**
	 * Retourne l'arbre et tous ses frères.
	 * @return Toute la fraterie
	 */
	public List<TrieHybride> getAllFreres(){
		return null;
//		List<ArbreBriandais> freres = new ArrayList<ArbreBriandais>();
//		freres.add(this);
//		ArbreBriandais frere_tmp = this.frereDroit;
//		while(frere_tmp!=null){
//			freres.add(frere_tmp);
//			frere_tmp = frere_tmp.frereDroit;
//		}
//		return freres;
	}
	
	
	//////////// PRIVATE /////////////
	
	/**
	 * Insère une lettre à la bonne position dans l'arbre (si n'existe pas)
	 * @param character
	 * @return l'arbre s'il est crée, null sinon
	 */
	private TrieHybride insererLettre(char character){
		if(this.clef == init){ // Si l'arbre ne contient qu'un seul noeud (l'initial :  '\0') 
			this.clef = character;
			return this;
		}
		
		OrdreLettre ordre = UtilitaireMots.ordreLettre(character, this.clef);
		switch (ordre) {
		
		case BEFORE:	// Le caractère à ajouter est avant notre clef
			if(this.inf==null) {
				this.inf = new TrieHybride(character);
				return this.inf;
			} else {
				return this.inf.insererLettre(character);
			}
		
		case AFTER:		// Le caractère à ajouter est après notre clef
			if(this.sup==null) {
				this.sup = new TrieHybride(character);
				return this.sup;
			} else {
				return this.sup.insererLettre(character);
			}
			
		default: // On ne fait rien
			return this;
		}
	}
	
	/**
	 * Insère une lettre à la bonne position dans le fils eq de l'arbre (si n'existe pas)
	 * @param character
	 * @return le fils eq de l'arbre s'il est crée, null sinon
	 */
	private TrieHybride insererLettreCommeSuite(char character){
		
		if(this.eq==null) return this.eq=new TrieHybride(character);
		return this.eq.insererLettre(character);
	
	}


}
