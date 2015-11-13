package arbreBriandais;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import utils.UtilitaireMots;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest {
	
	private static String textExo1 = "A quel genial professeur de dactylographie sommes nous redevables de la superbe " 
			+ "phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait " 
			+ " appel a chacune des touches du clavier de la machine a ecrire ?";
	
	
	static ArbreBriandais racine;
	char[] testLettres = {'b','e','a','z','h'};
	static int cptLettresRacines;
	
	@BeforeClass
	public static void runBeforeClass(){
		racine = new ArbreBriandais(ArbreBriandais.finDeMot, null, null);
		cptLettresRacines = 0;
	}
	
    @AfterClass
    public static void runAfterClass()  {
    	System.out.println ("====== Tests finis ========");
    	printClefs(racine.getFils());
    	
    	try {
    		// Enregistrement de l'arbre dans un fichier (utile pour affichage)
        	OutputStream file = new FileOutputStream( "documents/arbreBriandais.bin" );
        	OutputStream buffer = new BufferedOutputStream( file );
        	ObjectOutput output = new ObjectOutputStream( buffer );
			output.writeObject(racine);
			output.close();
			buffer.close();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
    //// =====> Tests INSERTION <==== /////
	@Test
	public void TestA_InsertionNouveauxMots1Lettre() {

		for (char lettre : testLettres) {
			racine.insererMot(String.valueOf(lettre));
		}
		int size = racine.getFils().getAllFreres().size();
		assertTrue("Nb de mots insérés = " + testLettres.length + " ?",
				size == testLettres.length);
		
		cptLettresRacines = testLettres.length;
	}

	@Test
	public void TestB_InsertionMotExistant1Lettre() {
		racine.insererMot(String.valueOf('a'));
		int size = racine.getFils().getAllFreres().size();
		assertFalse("Nouveau mot 'a' existant non inséré ?",
				size > testLettres.length);
	}

	@Test
	public void TestC_InsertionNouveauxMotsPlusieuresLettres() {
		racine.insererMot("rimk");
		int size = racine.getFils().getAllFreres().size();
		assertTrue(
				"Nouveau mot 'rimk' (avec 1iere lettre non existante) inséré ?",
				size == cptLettresRacines + 1);
		
		cptLettresRacines++;
	}

	@Test
	public void TestD_InsertionMotsExistantsPlusieuresLettres() {
		racine.insererMot("zoo");
		int size = racine.getFils().getAllFreres().size();
		assertFalse("Nouveau mot 'zoo' (avec 1iere lettre existante) inséré ?",
				size == cptLettresRacines + 1);
	}
	
	@Test
	public void TestE_InsertionPhrase() {

		int nbMotsAvant = racine.comptageMots();
		racine.insererPhrase(textExo1);
		int nbMotsApres = racine.comptageMots();
		assertTrue(nbMotsApres > nbMotsAvant);
		
	}
	@Test
	public void TestF_InsertionPhrase() {
//
//		UtilitaireMots.phraseToMots(phrase)
//		int size = racine.getFils().getAllFreres().size();
//		assertTrue("Nb de mots insérés = " + testLettres.length + " ?",
//				size == testLettres.length);
//		
//		cptLettresRacines = testLettres.length;
	}
	////===== FIN de TESTS INSERTION <==== /////
	
	
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
