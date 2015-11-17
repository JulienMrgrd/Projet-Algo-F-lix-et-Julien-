package tests;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import arbreBriandais.ArbreBriandais;

public class BriandaisTest {
	
	private static String textExo1 = "A quel genial professeur de dactylographie sommes nous redevables de la superbe " 
			+ "phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait " 
			+ " appel a chacune des touches du clavier de la machine a ecrire ?";
	
	
	static ArbreBriandais racine;
	
	@Before
	public void runBefore(){
		racine = new ArbreBriandais();
	}
	
    @AfterClass
    public static void runAfterClass()  {
    	System.out.println ("====== Tests finis ========");
    }
	
	
	@Test
	public void TestInsertion() {
		char[] testLettres = {'b','e','a','z','h'};
		
		for (char lettre : testLettres) {
			racine.insererMot(String.valueOf(lettre));
		}
		int size = racine.getAllFreres().size();
		assertTrue("Nb de mots insérés = " + testLettres.length + " ?",
				size == testLettres.length);
		
		racine.insererMot(String.valueOf('a'));
		size = racine.getAllFreres().size();
		assertFalse("Nouveau mot 'a' existant non inséré ?",
				size > testLettres.length);
		
		racine.insererMot("rigolo");
		boolean exist = racine.rechercherMot("rigolo");
		assertTrue("Nouveau mot 'rigolo' (avec 1iere lettre non "
				+ "existante) inséré ?", exist);
		
		racine.insererMot("zoo");
		exist = racine.rechercherMot("zoo");
		assertTrue("Nouveau mot 'zoo' (avec 1iere lettre existante) inséré ?",
				exist);
	}

	@Test
	public void TestInsertionPhrase() {
		int nbMotsAvant = racine.comptageMots();
		racine.insererPhrase(textExo1);
		int nbMotsApres = racine.comptageMots();
		assertTrue(nbMotsApres > nbMotsAvant);
	}
	
	@Test
	public void TestRechercheMot(){
		racine.insererPhrase(textExo1);
		boolean exist = racine.rechercherMot("dactylo");
		assertTrue(exist);
		exist = racine.rechercherMot("bkakdicbspd");
		assertFalse(exist);
		exist = racine.rechercherMot("dactylogra");
		assertFalse(exist);
	}
	

//	@Test
//	public void testGetBriandaisWithSameKeyAsNextToLastLetterOf(){
//		ArbreBriandais filsTetS = 
//				new ArbreBriandais('t',
//									new ArbreBriandais('s'),
//									new ArbreBriandais());
//		ArbreBriandais filsU = 
//				new ArbreBriandais('u',
//				  				   null,
//				  				   filsTetS);
//		ArbreBriandais abr = 
//				new ArbreBriandais('b',
//								new ArbreBriandais('l', 
//										           null, 
//										           new ArbreBriandais('a')),
//					           	filsU);
//		ArbreBriandais res = abr.getBriandaisWithSameKeyAsNextToLastLetterOf("but");
//		Assert.assertEquals(filsU, res);
//	}
	
	///// ==== METHODES PRIVATE ==== ///// 
	private static Character[] clefsFreres (ArbreBriandais b){
		List<Character> chars = new ArrayList<>();
		ArbreBriandais frere_tmp = b.getFrereDroit();
		while(frere_tmp!=null){
			chars.add((Character)frere_tmp.getClef());
			frere_tmp = frere_tmp.getFrereDroit();
		}
		return chars.toArray(new Character[chars.size()]);
	}
	
	private static void printClefs(ArbreBriandais b){
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
