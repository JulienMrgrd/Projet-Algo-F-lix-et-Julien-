package arbreBriandais;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import utils.OrdreLettre;
import utils.UtilitaireMots;

public class ArbreBriandais implements IArbreBriandais, Serializable{

	private static final long serialVersionUID = 1L; // Sert pour la sérialization
	
	private char clef; // '\0' sera le caractère de fin de mot
	private ArbreBriandais frereDroit;
	private ArbreBriandais fils;
	public static char finDeMot = '\0';

	public ArbreBriandais() {
		this(finDeMot);
	}
	
	public ArbreBriandais(char character) {
		this.clef=character;
		this.fils=null;
		this.frereDroit=null;
	}
	
	/**
	 * Constructeur par copie
	 * @param briandais
	 */
	public ArbreBriandais(ArbreBriandais briandais) {
		this.clef = briandais.clef;
		this.setFrereDroit(briandais.frereDroit);
		this.fils = briandais.fils;
	}

	public ArbreBriandais(char clef, ArbreBriandais frereDroit, ArbreBriandais fils) {
		this.clef = clef;
		this.setFrereDroit(frereDroit);
		this.fils = fils;
	}

	
	/**
	 * Insère la nouvelle phrase dans l'arbre (phrase = suite de mots) dans l'arbre, 
	 * avec un ArbreBriandais vide à la fin
	 * 
	 */
	@Override
	public void insererPhrase(String phrase) {
		if (phrase != null && !phrase.isEmpty()) {
			String[] mots = UtilitaireMots.phraseToMots(phrase);
			for(String mot : mots){
				this.insererMot(mot);
			}
		}
	}
	
	/**
	 * Insère le nouveau mot dans l'arbre, avec un ArbreBriandais vide à la fin
	 */
	@Override
	public void insererMot(String mot) {
		if (mot != null && !mot.isEmpty()) {
			char[] caracteres = UtilitaireMots.motToChars(mot);
			ArbreBriandais frere = this.insererLettreCommeFils(caracteres[0]);
			
			ArbreBriandais fils_tmp = frere;
			if(caracteres.length>1){
				for(int i=1; i<caracteres.length; i++){
					fils_tmp = fils_tmp.insererLettreCommeFils(caracteres[i]);
				}
			}
			
			fils_tmp.insererLettreCommeFils(finDeMot);
		}
	}
	
	@Override
	public boolean rechercherMot(String mot){
		if(mot==null || mot.isEmpty()) return false;
		
		
		if( mot.substring(0, 1)==String.valueOf(this.clef)){
			if(mot.length()==1){
				if( this.fils.clef==finDeMot) return true;
				else return this.frereDroit.rechercherMot(mot);
			} else {
				return this.fils.rechercherMot(mot.substring(1, mot.length()));
			}
		}
	
		if(this.frereDroit!=null) return this.frereDroit.rechercherMot(mot);
		return false;
	}
	
	@Override
	public int comptageMots() {
		if(this.clef==finDeMot){
			return (this.frereDroit!=null) ? 1 + this.frereDroit.comptageMots() : 1;
		
		} else {
			int cpt = 0;
			if(this.frereDroit != null) cpt+= this.frereDroit.comptageMots();
			if(this.fils != null) cpt+= this.fils.comptageMots();
			return cpt;
		}
	}
	
	@Override
	public List<String> listeMots() {
		List<String> listeMots = new ArrayList<>();
		listeMotsAvecLettresPrecedentes("",listeMots);
		// TODO : Trie ???
		return listeMots;
	}
	
	@Override
	public int comptageNil() {
		int cpt = 0;
		
		if(this.frereDroit==null) cpt++;
		else cpt += this.frereDroit.comptageNil();
		
		if(this.fils==null) cpt++;
		else cpt += this.fils.comptageNil();
		
		return cpt;
	}
	
	@Override
	public int hauteur(){
		if(this.fils != null){
			if(this.frereDroit != null) return Math.max( (1+this.fils.hauteur()), this.frereDroit.hauteur() );
			else return 1+this.fils.hauteur();
		} else {
			if(this.frereDroit != null) return this.frereDroit.hauteur();
			else return 0;
		}
	}
	

	public String toString(){
		return String.valueOf(clef);
	}
	
	
	// ==== PRIVATE ====
	
	
	/**
	 * Insère une lettre à la bonne position dans l'arbre (si n'existe pas)
	 * @param character
	 * @return l'arbre s'il est crée, null sinon
	 */
	private ArbreBriandais insererLettre(char character){
		OrdreLettre ordre = UtilitaireMots.ordreLettre(character, this.clef);
		switch (ordre) {
		
		case BEFORE:	// Le caractère à ajouter est avant notre clef
			ArbreBriandais copie_of_this = new ArbreBriandais(this);
			this.setClef(character);
			this.setFils(null);
			this.setFrereDroit(copie_of_this);
			return this;
		
		case AFTER:		// Le caractère à ajouter est après notre clef
			return this.insererLettreADroite(character);
			
		case EQUAL: // On ne fait rien
			return this;
		}
		return null;
		
	}
	
	/**
	 * Insère une lettre à la bonne position dans le fils de l'arbre (si n'existe pas)
	 * @param character
	 * @return le fils de l'arbre s'il est crée, null sinon
	 */
	private ArbreBriandais insererLettreCommeFils(char character){
		
		if(this.getFils()==null) return this.fils=new ArbreBriandais(character);
		return this.getFils().insererLettre(character);
	
	}
	
	/**
	 * Fonction utilisée lors des récursions pour réduire le nombre de comparaisons
	 * @param character
	 */
	private ArbreBriandais insererLettreADroite(char character){
		if(this.frereDroit==null){
			return this.frereDroit = new ArbreBriandais(character,null,null);
		
		} else if (this.frereDroit.clef == character){
			return this.frereDroit;
			
		}else if(character < this.frereDroit.clef){ // Si le caractère est entre sa clef et celle de son frère droit
			ArbreBriandais nouveau = new ArbreBriandais(character,this.frereDroit,null);
			return this.frereDroit = nouveau;
		
		} else {
			return this.frereDroit.insererLettreADroite(character);
		}
	}
	
	private void listeMotsAvecLettresPrecedentes(String lettresPrec, List<String> listeMots) {
		if(this.clef == finDeMot){
			listeMots.add(lettresPrec);
			if(this.frereDroit!=null) listeMotsAvecLettresPrecedentes(lettresPrec, listeMots);
		}
		String lettresPrec_tmp = lettresPrec;
		lettresPrec = lettresPrec + this.clef;
		if(this.fils!=null) listeMotsAvecLettresPrecedentes(lettresPrec, listeMots);
		if(this.frereDroit!=null) listeMotsAvecLettresPrecedentes(lettresPrec_tmp, listeMots);
	}
	//// FIN PRIVATE
	
	
	// ==== GETTERS / SETTERS =====
	
	public char getClef() { return clef; }

	public void setClef(char clef) { this.clef = clef; }
	
	public ArbreBriandais getFrereDroit() { return frereDroit; }

	public void setFrereDroit(ArbreBriandais frereDroit) { this.frereDroit = frereDroit; }

	public ArbreBriandais getFils() { return fils; }
	
	public ArbreBriandais getFilsByChar(char c) { 
		if(this.fils!=null){
			List<ArbreBriandais> freres = this.getFils().getAllFreres();
			for(ArbreBriandais ab : freres){
				if(ab.clef==c) return ab;
			}
		}
		return null;
	}

	public void setFils(ArbreBriandais fils) { this.fils = fils; }
	
	/**
	 * Retourne l'arbre et tous ses frères à droite.
	 * @return Toute la fraterie
	 */
	public List<ArbreBriandais> getAllFreres(){
		List<ArbreBriandais> freres = new ArrayList<ArbreBriandais>();
		freres.add(this);
		ArbreBriandais frere_tmp = this.frereDroit;
		while(frere_tmp!=null){
			freres.add(frere_tmp);
			frere_tmp = frere_tmp.frereDroit;
		}
		return freres;
	}

}
