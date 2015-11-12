package arbreBriandais;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static String textExo1 = "A quel genial professeur de dactylographie sommes nous redevables de la superbe " 
			+ "phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait " 
			+ " appel a chacune des touches du clavier de la machine a ecrire ?";
	
	
	public static void main(String[] args) {
		ArbreBriandais arbre = new ArbreBriandais('b', new ArbreBriandais('e', null, null), null);

		printClefs(arbre);
		arbre.insererLettre('a');
		System.out.println("On ajoute 'a'");
		
		printClefs(arbre);
		arbre.insererLettre('r');
		System.out.println("On ajoute 'r'");
		
		printClefs(arbre);
		arbre.insererLettre('a');
		System.out.println("On ajoute 'a'");
		
		printClefs(arbre);
		arbre.insererLettre('z');
		System.out.println("On ajoute 'z'");
		
		printClefs(arbre);
		arbre.insererLettre('h');
		System.out.println("On ajoute 'h'");
		
		printClefs(arbre);
	}
	
	public static Character[] clefsFreres (ArbreBriandais b){
		List<Character> chars = new ArrayList<>();
		ArbreBriandais frere_tmp = b.getFrereDroit();
		while(frere_tmp!=null){
			chars.add((Character)frere_tmp.getClef());
			frere_tmp = frere_tmp.getFrereDroit();
		}
		return chars.toArray(new Character[chars.size()]);
	}
	
	public static void printClefs(ArbreBriandais b){
		System.out.println("\nLa clef de notre arbre est : "+b.getClef());
		{
			System.out.print("Ses frères ont comme clefs : [");
			Character[] clefs = clefsFreres(b);
			for( int i=0; i<clefs.length; i++){
				System.out.print(clefs[i]);
				if(i!=clefs.length-1) System.out.print(", ");
			}
			System.out.println("]");
		}
	}

}
