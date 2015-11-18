package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import utils.UtilitaireMots;
import arbreBriandais.ArbreBriandais;

public class BriandaisTest {
	
	public static String textExo1 = "A quel genial professeur de dactylographie sommes nous redevables de la superbe " 
			+ "phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait " 
			+ " appel a chacune des touches du clavier de la machine a ecrire ?";
	
	
	private ArbreBriandais racine;
	
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
	
	@Test
	public void TestComptageMot(){
		assertEquals(racine.comptageMots(),0);
		racine.insererMot("Bonjour");
		racine.insererMot("a");
		racine.insererMot("Bonjours");
		assertEquals(racine.comptageMots(),3);
	}
	
	@Test
	public void TestComptageNil(){
		racine.insererMot("Bonjour");
		racine.insererMot("a");
		racine.insererMot("Bonjours");
		assertEquals(racine.comptageNil(),13);
	}
	
	@Test
	public void TestListeMots(){
		racine.insererMot("Bonjour");
		racine.insererMot("a");
		racine.insererMot("quocquelicot");
		racine.insererMot("zozo");
		racine.insererMot("Bonjour");
		racine.insererMot("Bonjours");
		racine.insererMot("abra");
		List<String> mots = racine.listeMots();
		assertEquals(mots.size(),6);
		
		racine = new ArbreBriandais();
		racine.insererPhrase(textExo1);
		// HashSet retire les doublons
		HashSet<String> tab_mots = new HashSet<>(Arrays.asList(UtilitaireMots.phraseToMots(textExo1)));
		assertEquals(racine.listeMots().size(),tab_mots.size());
	}
	
	@Test
	public void TestSuppressionMot(){
		racine.insererPhrase(textExo1);
		
		// Test sur un mot en début de dictionnaire
		String motASuppr = "appel";
		boolean existBefore = racine.rechercherMot(motASuppr);
		racine.suppression(motASuppr);
		boolean existAfter = racine.rechercherMot(motASuppr);
		assertTrue(existBefore);
		assertFalse(existAfter);
		
		// Test sur un mot en milieu de dictionnaire
		motASuppr = "professeur";
		existBefore = racine.rechercherMot(motASuppr);
		racine.suppression(motASuppr);
		existAfter = racine.rechercherMot(motASuppr);
		assertTrue(existBefore);
		assertFalse(existAfter);
		
		// Test sur mot inexistant
		motASuppr = "loqnscpmoc";
		existBefore = racine.rechercherMot(motASuppr);
		racine.suppression(motASuppr);
		existAfter = racine.rechercherMot(motASuppr);
		assertFalse(existBefore);
		assertFalse(existAfter);
		
		// Test sur un mot prefixe d'un autre
		motASuppr = "dactylo";
		existBefore = racine.rechercherMot(motASuppr);
		racine.suppression(motASuppr);
		existAfter = racine.rechercherMot(motASuppr);
		assertTrue(existBefore);
		assertFalse(existAfter);
		assertTrue(racine.rechercherMot("dactylographie"));
		
		// Test sur une lettre seule
		assertFalse(racine.rechercherMot("z"));
		racine.insererMot("z");
		motASuppr = "z";
		existBefore = racine.rechercherMot(motASuppr);
		racine.suppression(motASuppr);
		existAfter = racine.rechercherMot(motASuppr);
		assertTrue(existBefore);
		assertFalse(existAfter);
	}
	

	///// ==== METHODES PRIVATE ==== ///// 
	
}
