package utils;

import java.util.Locale;

public abstract class UtilitaireMots {
	
	private static String regex = "\\P{Alpha}+"; // Traduction = "Tous les non-alphabetics"
	
	public static String[] phraseToMots(String phrase){
		if (phrase != null && !phrase.isEmpty()){
			phrase = toMinuscule(phrase);
			return phrase.split(regex);
		}
		return null;
	}
	
	public static char[] motToChars(String mot) {
		if(mot != null && !mot.isEmpty()){
			return mot.toCharArray();
		}
		return null;
	}
	
	/**
	 * Transforme chaine en minuscule
	 * @param chaine
	 * @return
	 */
	public static String toMinuscule(String chaine){
		if (chaine != null && !chaine.isEmpty()){
			return chaine.toLowerCase(Locale.ROOT);
		}
		return null;
	}
	
	/**
     * Compare 2 {@code char} numériquement, et retourne 
     * l'ordre {@link utils.OrdreLettre OrdreLettre} correspondant  
     *
     * @param  x le 1ier {@code char} à comparer
     * @param  y le 2ème
     * @return x AFTER y, x BEFORE y, ou x EQUAL y
     */
	public static OrdreLettre ordreLettre ( char x, char y ){
		int result = (x-y);
		if(result==0) return OrdreLettre.EQUAL;
		else if (result<0) return OrdreLettre.BEFORE;
		else return OrdreLettre.AFTER;
	}

	
}
