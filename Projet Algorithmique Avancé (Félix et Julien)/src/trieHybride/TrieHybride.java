package trieHybride;

import interfaces.IArbre;

import java.util.ArrayList;
import java.util.List;

import arbreBriandais.ArbreBriandais;
import utils.OrdreLettre;
import utils.UtilitaireMots;

public class TrieHybride implements IArbre {

	private char clef;
	private boolean finDeMot;
	private TrieHybride inf;
	private TrieHybride eq;
	private TrieHybride sup;
	private static char init = '_';

	public TrieHybride() {
		this(init);
	}

	public TrieHybride(char character) {
		this.clef = character; // clef = la lettre
		this.finDeMot = false;
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
		this.finDeMot = hybride.finDeMot;
		this.inf = hybride.inf;
		this.eq = hybride.eq;
		this.sup = hybride.sup;
	}

	public TrieHybride(char clef, boolean finDeMot, TrieHybride inf,
			TrieHybride eq, TrieHybride sup) {
		this.clef = clef;
		this.finDeMot = finDeMot;
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

			trie_tmp.finDeMot = true; // La dernière lettre du mot est mise à
										// true
		}
	}
	
	public void insererMotPuisEquilibre(String mot) {
		if (mot != null && !mot.isEmpty()) {
			insererMot(mot);
			if( !isEquilibre() ) equilibre();
		}
	}

	@Override
	public void insererListeMots(List<String> mots) {
		if (mots != null && !mots.isEmpty()) {
			for (String mot : mots) {
				this.insererMot(mot);
			}
		}
	}

	@Override
	public void insererPhrase(String phrase) {
		if (phrase != null && !phrase.isEmpty()) {
			String[] mots = UtilitaireMots.phraseToMots(phrase);
			for (String mot : mots) {
				this.insererMot(mot);
			}
		}
	}

	@Override
	public boolean rechercherMot(String mot) {
		if (mot == null || mot.isEmpty())
			return false;

		if (mot.charAt(0) == this.clef) {
			if (mot.length() == 1) {
				if (this.finDeMot)
					return true;
				else
					return false;
			}
			if (this.eq != null) {
				return this.eq.rechercherMot(mot.substring(1, mot.length()));
			}
			return false;
		}

		if (mot.charAt(0) < this.clef) {
			if (this.inf != null)
				return this.inf.rechercherMot(mot);
			else
				return false;
		}

		if (this.sup != null)
			return this.sup.rechercherMot(mot);
		else
			return false;
	}

	@Override
	public int comptageMots() {
		int cptInf = (this.inf == null) ? 0 : this.inf.comptageMots();
		int cptEq = (this.eq == null) ? 0 : this.eq.comptageMots();
		int cptSup = (this.sup == null) ? 0 : this.sup.comptageMots();
		int cpt = (this.finDeMot) ? 1 : 0;
		return cpt + cptInf + cptEq + cptSup;
	}

	@Override
	public int comptageNil() {
		int cptInf = (this.inf == null) ? 1 : this.inf.comptageNil();
		int cptEq = (this.eq == null) ? 1 : this.eq.comptageNil();
		int cptSup = (this.sup == null) ? 1 : this.sup.comptageNil();
		return cptInf + cptEq + cptSup;
	}

	@Override
	public List<String> listeMots() {
		List<String> listeMots = new ArrayList<>();
		this.listeMotsAvecLettresPrecedentes("", listeMots);
		return listeMots;
	}

	@Override
	public int hauteur() {
		int cptInf = (this.inf == null) ? 0 : this.inf.hauteur();
		int cptEq = (this.eq == null) ? 0 : this.eq.hauteur();
		int cptSup = (this.sup == null) ? 0 : this.sup.hauteur();
		return 1 + Math.max(cptInf, Math.max(cptEq, cptSup));
	}

	@Override
	public double profondeurMoyenne() {
		if(this.comptageMots()==0) return 0;
		return ((double)this.profondeurTotale())/this.comptageMots();
	}
	
	private int profondeurTotale() {
		int cptInf = (this.inf == null) ? 0 : this.inf.profondeurTotale();
		int cptEq = (this.eq == null) ? 0 : this.eq.profondeurTotale();
		int cptSup = (this.sup == null) ? 0 : this.sup.profondeurTotale();
		return 1 + cptInf + cptEq + cptSup;
	}

	@Override
	public int prefixe(String chaine) {
		if (chaine == null || chaine.isEmpty())
			return 0;
		
		char premiereLettre = chaine.charAt(0);
		if (premiereLettre == this.clef) {
			if(chaine.length()==1){
				if (this.finDeMot)	return (this.eq != null) ? 1 + this.eq.comptageMots() : 1;
				else return (this.eq == null) ? 0 : this.eq.comptageMots();
			
			} else return (this.eq == null) ? 0 : this.eq.prefixe(chaine.substring(1, chaine.length()));
		
		} else if (premiereLettre < this.clef) {
			if (this.inf != null) return this.inf.prefixe(chaine);
			else return 0;
		
		} else if (premiereLettre > this.clef) {
			if (this.sup!=null)	return this.sup.prefixe(chaine);
			else return 0;
		}
		return 0;
	}

	@Override
	public void suppression(String mot) {
		if (this.rechercherMot(mot)) {
			this.suppressionRec(mot);
		}
	}

	private void suppressionRec(String mot) {
		if (mot.charAt(0) == this.clef) {
			if (mot.length() == 1) {
				this.finDeMot=false;
				return;
			
			} else if (this.prefixe(mot.substring(0, 1)) == 1) { // Aucune dépendance d'un autre mot
				
				if (this.inf != null) {
					
					if (this.sup != null) {
						this.modifThis(this.sup);
						this.insererListeMots(this.inf.listeMots());
						return;
					} // this.sup == NULL && this.inf != NULL
					this.modifThis(this.inf);
					return;
				
				} else if (this.sup != null) { // this.inf == NULL && this.sup != NULL
					this.modifThis(this.sup);
					return;
				} // this.inf == NULL && this.sup == NULL
				
				this.modifThis(new TrieHybride());
				return;
			
			} else {  // Mot.length>1 & dependance d'autres mots
				char deuxiemeLettre = mot.charAt(1);
				if (this.eq != null) {
					if (this.eq.clef == deuxiemeLettre && this.eq.prefixe(mot.substring(1, 2)) == 1) {
						if(this.inf != null){
							this.eq = this.inf;
							return;
						}
						this.eq=this.eq.sup;
						return;
					}
					this.eq.suppressionRec(mot.substring(1, mot.length()));
					return;
				}
				
				TrieHybride frere = (deuxiemeLettre>clef) ? this.sup : this.inf;
				
				if (frere != null) {
					if (frere.clef == deuxiemeLettre && frere.prefixe(mot.substring(1, 2)) == 1) {
						frere = frere.inf;
						return;
					}
					frere.suppressionRec(mot.substring(1, mot.length()));
					return;
				}
				
			}
		
		} else if (mot.charAt(0)<this.clef && this.inf != null){  // 1iere lettre motASuppr < clef
			if (mot.charAt(0)==this.inf.clef){
				if(this.inf.prefixe(mot.substring(0, 1))==1){
						this.inf=this.inf.inf;
						return;
				}
			}
			this.inf.suppressionRec(mot);
			return;
		
		} else if(this.sup != null){ // 1iere lettre motASuppr > clef
			if ((this.sup.prefixe(mot.substring(0, 1)) == 1) && (this.sup.clef==mot.charAt(0))) {
				this.sup = this.sup.sup;
				return;
			} 
			this.sup.suppressionRec(mot);
			
		}
	}

	public ArbreBriandais conversion() {
		if(this.isLeaf()){
			if(this.clef==init) return null; 
			else return new ArbreBriandais(this.clef,null,new ArbreBriandais(ArbreBriandais.finDeMot));
		}
		
		ArbreBriandais briandaisInf = (this.inf==null) ? null : this.inf.conversion();
		ArbreBriandais briandaisSup = (this.sup == null) ? null : this.sup.conversion();
		ArbreBriandais briandais2 = null;
		
		if(this.finDeMot){
			briandais2 = new ArbreBriandais(this.clef, briandaisSup, new ArbreBriandais(ArbreBriandais.finDeMot) );
			if(this.eq!=null) briandais2.getFils().setFrereDroit(this.eq.conversion());
			else briandais2.getFils().setFrereDroit(null);
		
		} else{
			ArbreBriandais eqConv = (this.eq==null) ? null : this.eq.conversion();
			briandais2 = new ArbreBriandais( this.clef, briandaisSup, eqConv );
		}

		if(briandaisInf!=null) briandaisInf.fusion(briandais2);
		else briandaisInf = briandais2;
		
		return briandaisInf;
	}

	/**
	 * Retourne les fils existants d'un arbre
	 */
	public List<TrieHybride> getAllFilsExistants() {
		List<TrieHybride> freres = new ArrayList<TrieHybride>();
		if (this.inf != null) freres.add(this.inf);
		else freres.add(new TrieHybride('.'));

		if (this.eq != null) freres.add(this.eq);
		else freres.add(new TrieHybride('.'));

		if (this.sup != null) freres.add(this.sup);
		else freres.add(new TrieHybride('.'));
		
		return freres;
	}

	public String toString() {
		return String.valueOf(this.clef);
	}

	
	public boolean isLeaf(){
		if(this.inf== null && this.eq==null && this.sup==null) return true;
		return false;
	}
	
	public void equilibre() {
		int hauteurInf = this.inf==null ? 0 : this.inf.hauteur();
		int hauteurSup = this.sup==null ? 0 : this.sup.hauteur();
		int dif = hauteurInf - hauteurSup;

		if (dif >= 2) {
			if(this.inf!=null){
				int hauteurInfInf = this.inf.inf==null ? 0 : this.inf.inf.hauteur();
				int hauteurInfSup = this.inf.sup==null ? 0 : this.inf.sup.hauteur();
				if( hauteurInfInf < hauteurInfSup ) this.inf.rotationGauche();
			}
			this.rotationDroite();

		} else if (dif <= -2) {
			if(this.sup!=null){
				int hauteurSupSup = this.sup.sup==null ? 0 : this.sup.sup.hauteur();
				int hauteurSupInf = this.sup.inf==null ? 0 : this.sup.inf.hauteur();
				if( hauteurSupSup < hauteurSupInf ) this.sup.rotationGauche();
			}
			this.rotationGauche();
		}
	}
	
	private void rotationDroite(){
		  TrieHybride aux = this.inf;
		  this.inf=aux.sup;
		  aux.sup=this;
		  modifThis(aux);
	}
	
	private void rotationGauche(){
		  TrieHybride aux = this.sup;
		  this.sup=aux.inf;
		  aux.inf=this;
		  modifThis(aux);
	}
	
	private boolean isEquilibre() {
		if(this.eq !=null && this.sup!=null && this.inf !=null){
			if(Math.abs((this.inf.hauteur()-this.eq.hauteur()))<=1 
					&& Math.abs( (this.inf.hauteur()-this.sup.hauteur()) )<=1 
					&& Math.abs( (this.eq.hauteur()-this.sup.hauteur()) )<=1){
				return true;
			}
		}
		return false;
	}
	

	//////////////// PRIVATE /////////////

	/**
	 * Insère une lettre à la bonne position dans l'arbre (si n'existe pas)
	 * 
	 * @param character
	 * @return l'arbre s'il est crée, null sinon
	 */
	private TrieHybride insererLettre(char character) {
		if (this.clef == init) { // Si l'arbre ne contient qu'un seul noeud
									// (l'initial : '\0')
			this.clef = character;
			return this;
		}

		OrdreLettre ordre = UtilitaireMots.ordreLettre(character, this.clef);
		switch (ordre) {

		case BEFORE: // Le caractère à ajouter est avant notre clef
			if (this.inf == null) {
				this.inf = new TrieHybride(character);
				return this.inf;
			} else {
				return this.inf.insererLettre(character);
			}

		case AFTER: // Le caractère à ajouter est après notre clef
			if (this.sup == null) {
				this.sup = new TrieHybride(character);
				return this.sup;
			} else {
				return this.sup.insererLettre(character);
			}

		default: // On ne fait rien
			return this;
		}
	}

	private void listeMotsAvecLettresPrecedentes(String lettrePrec,
			List<String> listeMots) {
		if (this.finDeMot) {
			if (this.inf != null)
				this.inf.listeMotsAvecLettresPrecedentes(lettrePrec, listeMots);
			listeMots.add(lettrePrec + this.clef);
			if (this.eq != null)
				this.eq.listeMotsAvecLettresPrecedentes(lettrePrec + this.clef,
						listeMots);
			if (this.sup != null)
				this.sup.listeMotsAvecLettresPrecedentes(lettrePrec, listeMots);
		} else {
			if (this.inf != null)
				this.inf.listeMotsAvecLettresPrecedentes(lettrePrec, listeMots);
			if (this.eq != null)
				this.eq.listeMotsAvecLettresPrecedentes(lettrePrec + this.clef,
						listeMots);
			if (this.sup != null)
				this.sup.listeMotsAvecLettresPrecedentes(lettrePrec, listeMots);
		}
	}

	/**
	 * Insère une lettre à la bonne position dans le fils eq de l'arbre (si
	 * n'existe pas)
	 * 
	 * @param character
	 * @return le fils eq de l'arbre s'il est crée, null sinon
	 */
	private TrieHybride insererLettreCommeSuite(char character) {

		if (this.eq == null)
			return this.eq = new TrieHybride(character);
		return this.eq.insererLettre(character);

	}
	
	private void modifThis(TrieHybride trie) {
		this.clef = trie.clef;
		this.finDeMot = trie.finDeMot;
		this.inf = trie.inf;
		this.eq = trie.eq;
		this.sup = trie.sup;
	}

	// //////////// GETTERS / SETTERS ///////////
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
	
	public void setFinDeMot(boolean finDeMot){
		this.finDeMot=finDeMot;
	}
	
	public boolean isFinDeMot(){
		return finDeMot;
	}
	
}
