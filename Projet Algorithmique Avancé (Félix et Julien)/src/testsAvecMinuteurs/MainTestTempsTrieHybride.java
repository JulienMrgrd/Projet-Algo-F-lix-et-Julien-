package testsAvecMinuteurs;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class MainTestTempsTrieHybride {
	
	public static void main(String[] args) {
		
//		String[] mots = {"year", "bianca", "rjkbrr", "e", "pomfret", "what", "this", "read", "a", "see"};
//		String[] motsAInserer = {"journee", "tante", "f", "lit", "tomate", "maman", "decoupe", "smartphone", "aiguille", "riz"};

		System.out.println("==== Test sur Trie Hybride ===");
		TestsTempsSuperclass testHybride = new TestsTempsTrieHybride();
		
		for(int i=0; i<TestsTempsSuperclass.NB_TESTS; i++){
			testHybride.addTpsConstruction(testHybride.tempsConstruction());
			testHybride.addTpsInsertion(testHybride.tempsInsertion("journee"));
			testHybride.addTpsRecherche(testHybride.tempsRecherche("year"));
			testHybride.addTpsSuppression(testHybride.tempsSuppression("journee"));
			testHybride.addTpsComptageMots(testHybride.tempsComptageMots());
			testHybride.resetArbre();
		}
		
		System.out.println("-> Temps de constructions : ");
		printTenLastTimesAndAverage(testHybride.getTpsConstruction());
		System.out.println("-> Temps d'insertion : ");
		printTenLastTimesAndAverage(testHybride.getTpsInsertion());
		System.out.println("-> Temps de recherche : ");
		printTenLastTimesAndAverage(testHybride.getTpsRecherche());
		System.out.println("-> Temps de suppressions : ");
		printTenLastTimesAndAverage(testHybride.getTpsSuppression());
		System.out.println("-> Temps de comptage de mots : ");
		printTenLastTimesAndAverage(testHybride.getTpsComptageMots());
	}
	
	/**Affiche les 10 derniers temps de la liste passée en paramètres
	 * (les premiers temps étant anormalement plus grands)
	 * 
	 * @param list
	 */
	private static void printTenLastTimesAndAverage(List<BigDecimal> list){
		list = list.subList(4, TestsTempsSuperclass.NB_TESTS-1);
		
		BigDecimal tot = new BigDecimal(0);
		for(BigDecimal res : list){
			System.out.println(res+" ms");
			tot = tot.add(res);
		}
		
		System.out.println("Moyenne de : "+tot.divide(new BigDecimal(list.size()), MathContext.DECIMAL32)+" ms\n");
	}

}
