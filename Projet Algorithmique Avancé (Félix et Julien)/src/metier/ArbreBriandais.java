package metier;

import interfaces.ITrie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import utils.OrdreLettre;
import utils.UtilitaireMots;

public class ArbreBriandais implements ITrie, Serializable{

	private static final long serialVersionUID = 1L; // Sert pour la sérialization
	
	private char clef; // '\0' sera le caractère de fin de mot
	private ArbreBriandais frereDroit;
	private ArbreBriandais fils;
	public static char finDeMot = '\0';
	private static char init = '_';

	public ArbreBriandais() {
		this(init);
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
		this.frereDroit = briandais.frereDroit;
		this.fils = briandais.fils;
	}

	public ArbreBriandais(char clef, ArbreBriandais frereDroit, ArbreBriandais fils) {
		this.clef = clef;
		this.frereDroit = frereDroit;
		this.fils = fils;
	}

	
	@Override
	public void insererPhrase(String phrase) {
		if (phrase != null && !phrase.isEmpty()) {
			String[] mots = UtilitaireMots.phraseToMots(phrase);
			for(String mot : mots){
				this.insererMot(mot);
			}
		}
	}
	
	@Override
	public void insererListeMots(List<String> mots) {
		if (mots != null && !mots.isEmpty()) {
			for(String mot : mots){
				this.insererMot(mot);
			}
		}
	}
	
	
	@Override
	public void insererMot(String mot) {
		if (mot != null && !mot.isEmpty()) {
			mot=UtilitaireMots.toMinuscule(mot);
			char[] caracteres = UtilitaireMots.motToChars(mot);
			ArbreBriandais abrNewLetter = this.insererLettre(caracteres[0]);
			
			ArbreBriandais abr_tmp = abrNewLetter;
			if(caracteres.length>1){
				for(int i=1; i<caracteres.length; i++){
					abr_tmp = abr_tmp.insererLettreCommeFils(caracteres[i]);
				}
			}
			
			abr_tmp.insererLettreCommeFils(finDeMot);
		}
	}
	
	@Override
	public boolean rechercherMot(String mot){
		if(mot==null || mot.isEmpty()) return false;
		
		char first = mot.charAt(0);
		if( first==this.clef ){
			if(mot.length()==1){
				if( this.fils.clef==finDeMot) return true;
				else if (this.frereDroit!=null) return this.frereDroit.rechercherMot(mot);
				else return false;
			} else {
				return this.fils.rechercherMot(mot.substring(1, mot.length()));
			}
		}
	
		if(this.frereDroit!=null && first>=this.frereDroit.clef) return this.frereDroit.rechercherMot(mot);
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
		this.listeMotsAvecLettresPrecedentes("",listeMots);
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
		if(this.clef == finDeMot){
			return (this.frereDroit==null) ? 1 : this.frereDroit.hauteur();
		
		} else {
			int hauteur_fils = (this.fils==null) ? 0 : this.fils.hauteur();
			int hauteur_frere = (this.frereDroit==null) ? 0 : this.frereDroit.hauteur();
			return Math.max(1+hauteur_fils, hauteur_frere);
		}
	}
	
	@Override
	public double profondeurMoyenne(){
		if(this.comptageMots()==0) return 0;
		return this.profondeurTotale()/this.comptageMots();
	}
	
	private int profondeurTotale() {
		int cptFils = (this.frereDroit == null) ? 0 : this.frereDroit.profondeurTotale();
		int cptFrere = (this.fils == null) ? 0 : this.fils.profondeurTotale();
		return 1 + cptFils + cptFrere;
	}
	
	@Override
	public int prefixe(String str){
		
		if(str==null || str.isEmpty()) return 0;
		if( str.length() == 1 ){
			
			if( str.charAt(0) == this.clef ){
				if(this.fils != null) return this.fils.comptageMots();
				else return 0;
			
			} else {
				if(this.frereDroit != null) return this.frereDroit.prefixe(str);
				else return 0;
			}
		
		} else {
			if( str.charAt(0) == this.clef ){
				if(this.fils != null) return this.fils.prefixe(str.substring(1, str.length()));
				else return 0;
			
			} else {
				if(this.frereDroit != null) return this.frereDroit.prefixe(str);
				else return 0;
			}
		}
		
	}
	
	
	@Override
	public void suppression(String mot) {
		if (rechercherMot(mot)) {
			String s = mot.substring(0, 1);
			// L'endroit où on est ne contient qu'un seul mot (celui à suppr)
			if (this.clef == mot.charAt(0) && this.prefixe(s) == 1) {
				ArbreBriandais tmp = this.getFrereDroit();
				this.frereDroit = null;
				this.clef = tmp.clef;
				this.frereDroit = tmp.frereDroit;
				this.fils = tmp.fils;
			} else {
				this.suppressionRec(mot);
			}
		}
	}
	
	private void suppressionRec(String mot) {

		if (mot.charAt(0) != this.clef) {
			if (mot.charAt(0) == this.frereDroit.clef) { // Comme la fonction suppression(String mot) a detecté un prefixe > 1, le frere existe
				if (this.prefixe(mot.substring(0, 1)) == 1) {
					
					if (this.frereDroit.frereDroit != null) {
						this.frereDroit = this.frereDroit.frereDroit;
						return;
					
					} else {
						this.frereDroit = null;
						return;
					}
				} else { // Si y'a plusieurs mots qui dépendent
					
					// Si soit : le mot n'est qu'une lettre et que le fils du frere est un finDeMot
					// ou soit : la cle du fils du frere est egal au deuxieme element du mot et que les lettres parcourues
					//           du mot + la briandaise lettre du mot actuel ne sont pas les prefixes d'autres mots
					if( (mot.length()==1 && this.frereDroit.fils.clef == finDeMot )
						|| (this.frereDroit.fils.clef== mot.charAt(1) && prefixe(mot.substring(0,1) )  == 1) ){
						this.frereDroit.fils = this.frereDroit.fils.frereDroit;
						return;
					}
					
					this.frereDroit.suppressionRec(mot);
					return;
				}
				
			//sinon si la 1er lettre du mot n'est pas egal a la cle du frereDroit
			} else {
				this.frereDroit.suppression(mot);
				return;
			}
			
			//sinon si la 1er lettre du mot est egal a la cle du noeud courant
		} else {
			String s = mot.substring(0, 1);
			if (mot.substring(1, mot.length()).length() > 0) { // reste du mot > 0 ?
				String tmp = s + mot.charAt(1);
				if (prefixe(tmp) == 1 && this.fils.clef == mot.charAt(1)) {
					this.fils = this.fils.frereDroit;
					return;
				} else {
					this.fils.suppressionRec(mot.substring(1, mot.length()));
					return;
				}
			} else {
				this.fils = this.fils.frereDroit;
				return;
			}
		}
	}
	
	public static void main(String[] args) {
		ArbreBriandais ab = new ArbreBriandais();
		ab.insererMot("bo");
		
		ArbreBriandais other = new ArbreBriandais();
		other.insererMot("ad");
		ab.fusion(other);
		System.out.println(ab.listeMots());
		
	}
	
	public void fusion(ArbreBriandais briandais){
		if (briandais != null){
			if(briandais.clef < this.clef){
				this.frereDroit = new ArbreBriandais(this.clef,this.frereDroit,this.fils);
				this.clef = briandais.clef;
				this.fils = briandais.fils;
				this.frereDroit.fusion(briandais.frereDroit);
			
			} else if(briandais.clef > this.clef){
				if(this.frereDroit==null) this.frereDroit=briandais;
				else this.frereDroit.fusion(briandais);
			
			} else{
				if(this.fils==null) this.fils=briandais.fils;
				else this.fils.fusion(briandais.fils);
				if(this.frereDroit==null) this.frereDroit=briandais.frereDroit;
				else this.frereDroit.fusion(briandais.frereDroit);
			}
		}
		
	}
	
	@Override
	public TrieHybride conversion() {
		TrieHybride trie = new TrieHybride();
		if(this.frereDroit!=null) trie.setSup(this.frereDroit.conversion());
		if(this.clef!=finDeMot) trie.setClef(this.clef);
		
		if (this.fils!=null && this.fils.clef==finDeMot){
			trie.setFinDeMot(true);
			if(this.fils.frereDroit!=null) trie.setEq(this.fils.frereDroit.conversion());
		} else {
			if (fils != null) trie.setEq(this.fils.conversion());
		}
		return trie;
	}
	
	
	
	public String toString(){
		return String.valueOf(this.clef);
	}
	
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
	
	
	
	//////////////// PRIVATE /////////////
	
	/**
	 * Insère une lettre à la bonne position dans l'arbre (si n'existe pas)
	 * @param character
	 * @return l'arbre s'il est crée, null sinon
	 */
	private ArbreBriandais insererLettre(char character){
		if(this.clef == init){ // Si l'arbre ne contient qu'un seul noeud (l'initial :  '\0') 
			this.clef = character;
			return this;
		}
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
			
		default: // On ne fait rien
			return this;
		}
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
			if(this.frereDroit!=null) this.frereDroit.listeMotsAvecLettresPrecedentes(lettresPrec, listeMots);
		} else {
			String lettresPrec_tmp = lettresPrec;
			lettresPrec = lettresPrec + this.clef;
			if(this.fils!=null) this.fils.listeMotsAvecLettresPrecedentes(lettresPrec, listeMots);
			if(this.frereDroit!=null) this.frereDroit.listeMotsAvecLettresPrecedentes(lettresPrec_tmp, listeMots);
		}
	}

	
	// //////////// GETTERS / SETTERS ///////////
	
	public char getClef() { return clef; }

	public void setClef(char clef) { this.clef = clef; }
	
	public ArbreBriandais getFrereDroit() { return frereDroit; }

	public void setFrereDroit(ArbreBriandais frereDroit) { this.frereDroit = frereDroit; }

	public ArbreBriandais getFils() { return fils; }
	
	public void setFils(ArbreBriandais fils) { this.fils = fils; }
	
}
