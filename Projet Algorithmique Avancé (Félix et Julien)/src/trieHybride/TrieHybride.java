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
		if (mot == null || mot.isEmpty())
			return 0;
		if (mot.charAt(0) == this.clef) {
			if (this.eq != null) {
				if (mot.length() == 1)
					return this.eq.comptageMots();
				else
					return this.eq.prefixe(mot.substring(1, mot.length()));
			}
			return 0;
		}
		if (mot.charAt(0) < this.clef) {
			if (this.inf != null)
				return this.inf.prefixe(mot);
			else
				return 0;
		}
		if (mot.charAt(0) > this.clef) {
			return this.sup.prefixe(mot);
		}
		return 0;
	}

	@Override
	public void suppression(String mot) {
		if (this.rechercherMot(mot)) {
			this.suppressionRec(mot);
		}

	}

	private void modifThis(char clef, boolean fin, TrieHybride inf,
			TrieHybride eq, TrieHybride sup) {
		this.clef = clef;
		this.finDeMot = fin;
		this.inf = inf;
		this.eq = eq;
		this.sup = sup;
	}

	private void suppressionRec(String mot) {
		if (mot.charAt(0) == this.clef) {
			if (mot.length() == 1) {
				this.finDeMot=false;
				return;
			}
			if (this.prefixe(mot.substring(0, 1)) == 1) {
				if (this.inf != null) {
					if (this.sup != null) {
						List<String> listeMots = this.inf.listeMots();

						TrieHybride tmp = this.sup;
						this.modifThis(tmp.clef, tmp.finDeMot, tmp.inf, tmp.eq,
								tmp.sup);

						this.insererListeMots(listeMots);
						return;
					} // this.sup == NULL && this.inf != NULL
					TrieHybride tmp = this.inf;
					this.modifThis(tmp.clef, tmp.finDeMot, tmp.inf, tmp.eq,
							tmp.sup);
					return;
				}// this.inf == NULL
				if (this.sup != null) { // this.inf == NULL && this.sup != NULL
					TrieHybride tmp = this.sup;
					this.modifThis(tmp.clef, tmp.finDeMot, tmp.inf, tmp.eq,
							tmp.sup);
					return;
				} // this.inf == NULL && this.sup == NULL
				TrieHybride tmp = new TrieHybride();
				this.modifThis(tmp.clef, tmp.finDeMot, tmp.inf, tmp.eq, tmp.sup);
				return;
			}
			if (mot.length() >= 1) { // MOT.LENGTH()>=1 && MOT[0]==THIS.CLE;
				if (this.inf != null) {
					if (this.inf.clef == mot.charAt(1)) {
						if (this.inf.prefixe(mot.substring(1, 2)) == 1) {
							this.inf = this.inf.inf;
							return;
						}
					}
					this.inf.suppressionRec(mot.substring(1, mot.length()));
				}
				if (this.eq != null) {
					if (this.eq.clef == mot.charAt(1)) {
						if (this.eq.prefixe(mot.substring(1, 2)) == 1) {
							if(this.inf != null){
								this.eq = this.inf;
								return;
							}
							this.eq=this.sup;
							return;
						}
					}
					this.eq.suppressionRec(mot.substring(1, mot.length()));
				}
				if (this.sup != null) {
					if (this.sup.clef == mot.charAt(1)) {
						if (this.sup.prefixe(mot.substring(1, 2)) == 1) {
							this.sup = this.sup.sup;
							return;
						}
					}
					this.sup.suppressionRec(mot.substring(1, mot.length()));
					return;
				}
			}
		}
		else {
			if (mot.charAt(0)<this.clef){
				if (mot.charAt(0)==this.inf.clef){
					if(this.inf.prefixe(mot.substring(0, 1))==1){
							this.inf=this.inf.inf;
							return;
					}
				}
				this.inf.suppressionRec(mot);
				return;
			}
			if (this.sup.prefixe(mot.substring(0, 1))==1){
				this.sup=this.sup.sup;
				return;
			}
			this.sup.suppressionRec(mot.substring(1,mot.length()));
		}
	}

	@Override
	public IArbre conversion() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Retourne les fils existants d'un arbre
	 */
	public List<TrieHybride> getAllFilsExistants() {
		List<TrieHybride> freres = new ArrayList<TrieHybride>();
		if (this.inf != null)
			freres.add(this.inf);
		else
			freres.add(new TrieHybride('.'));

		if (this.eq != null)
			freres.add(this.eq);
		else
			freres.add(new TrieHybride('.'));

		if (this.sup != null)
			freres.add(this.sup);
		else
			freres.add(new TrieHybride('.'));
		return freres;
	}

	public String toString() {
		return String.valueOf(this.clef);
	}

	// ////////////// PRIVATE /////////////

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

}
