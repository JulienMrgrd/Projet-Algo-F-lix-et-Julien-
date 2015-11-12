package arbreBriandais;

import utils.OrdreLettre;
import utils.UtilitaireMots;

public class ArbreBriandais implements IArbreBriandais{

	private char clef; // '\0' sera le caractère de fin de mot
	private ArbreBriandais frereDroit;
	private ArbreBriandais fils;

	public ArbreBriandais() {
		clef = '\0';
		setFrereDroit(null);
		fils = null;
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
	 */
	public void insererLettre(char character){
		OrdreLettre ordre = UtilitaireMots.ordreLettre(character, this.clef);
		switch (ordre) {
		
		case BEFORE:	// Le caractère à ajouter est avant notre clef
			ArbreBriandais copie_of_this = new ArbreBriandais(this);
			this.setClef(character);
			this.setFils(null);
			this.setFrereDroit(copie_of_this);
			break;
		
		case AFTER:		// Le caractère à ajouter est après notre clef
			
			this.insererLettreADroite(character);
			
		case EQUAL: // On ne fait rien
			return;
		}
		
	}
	
	/**
	 * Fonction utilisée lors des récursions pour réduire le nombre de comparaisons
	 * @param character
	 */
	private void insererLettreADroite(char character){
		if(this.frereDroit==null){
			this.frereDroit = new ArbreBriandais(character,null,null);
		
		} else if (this.frereDroit.clef == character){
			return;
			
		}else if(character < this.frereDroit.clef){ // Si le caractère est entre sa clef et celle de son frère droit
			ArbreBriandais nouveau = new ArbreBriandais(character,this.frereDroit,null);
			this.frereDroit = nouveau;
		
		} else {
			this.frereDroit.insererLettreADroite(character);
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

	public void setFils(ArbreBriandais fils) { this.fils = fils; }
	
}
