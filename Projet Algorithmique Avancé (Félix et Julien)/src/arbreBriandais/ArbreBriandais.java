package arbreBriandais;

import java.util.ArrayList;
import java.util.List;

import utils.OrdreLettre;
import utils.UtilitaireMots;

public class ArbreBriandais implements IArbreBriandais{

	private char clef; // '\0' sera le caractère de fin de mot
	private ArbreBriandais frereDroit;
	private ArbreBriandais fils;

	public ArbreBriandais() {
		this('\0');
	}
	
	public ArbreBriandais(char character) {
		this.clef=character;
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

	

	public void insererPhrase(String phrase) {
		if (phrase != null && !phrase.isEmpty()) {
//			TODO: String[] mots = UtilitaireMots.phraseToMots(phrase);
		}
	}
	
	public void insererMot(String mot) {
		if (mot != null && !mot.isEmpty()) {
		}
	}
	
	/**
	 * Insère une lettre à la bonne position dans l'arbre (si n'existe pas)
	 * @param character
	 * @return l'arbre s'il est crée, null sinon
	 */
	public ArbreBriandais insererLettre(char character){
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
	public ArbreBriandais insererLettreCommeFils(char character){
		if(this.getFils()==null) return this.fils=new ArbreBriandais(character);
		else{
			return this.getFils().insererLettre(character);
		}
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
	
	public String toString(){
		return String.valueOf(clef);
	}
	
	
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
