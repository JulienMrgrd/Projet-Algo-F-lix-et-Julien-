package testsAvecMinuteurs;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import java.util.List;

import utils.FileUtils;
import utils.testsAvecMinuteurs.TestsTempsSuperclass;
import utils.testsAvecMinuteurs.TestsTempsTrieHybride;

public class MainTestTempsTrieHybride {
	
	private static String doc = "documents/Temps/hybrideTemps.txt";
	private static String[] mots = {"journee", "tante", "f", "lit", "tomate", "maman", "decoupe", "smartphone", "aiguille", "riz"};
	
	public static void main(String[] args) {
		
		System.out.println("==== Test sur Trie Hybride ===");
		TestsTempsSuperclass testHybride = new TestsTempsTrieHybride();
		init(testHybride);
		
		for(int cpt=0; cpt<2; cpt++){
			for(int i=0; i<TestsTempsSuperclass.NB_TESTS/2; i++){
				testHybride.addTpsConstruction(testHybride.tempsConstruction());
				testHybride.addTpsInsertion(testHybride.tempsInsertion(mots[i]));
				testHybride.addTpsRecherche(testHybride.tempsRecherche(mots[i]));
				testHybride.addTpsSuppression(testHybride.tempsSuppression(mots[i]));
				testHybride.addTpsComptageMots(testHybride.tempsComptageMots());
				testHybride.addTpsPrefixe(testHybride.tempsPrefixe(mots[i]));
				testHybride.resetArbre();
			}
		}
		
		testHybride.delete5firstElementInAllList();
		
		FileUtils.storeInTxt(doc, "", Calendar.getInstance().getTime().toString()+"\n" );
		System.out.println("-> Temps de constructions : ");
		printTenLastTimesAndAverage(testHybride.getTpsConstruction());
		FileUtils.storeInTxt(doc, "-> Temps de constructions : ",testHybride.getTpsConstruction());
		
		System.out.println("-> Temps d'insertion : ");
		printTenLastTimesAndAverage(testHybride.getTpsInsertion());
		FileUtils.storeInTxt(doc, "-> Temps d'insertion : ",testHybride.getTpsInsertion());
		
		System.out.println("-> Temps de recherche : ");
		printTenLastTimesAndAverage(testHybride.getTpsRecherche());
		FileUtils.storeInTxt(doc, "-> Temps de recherche : ", testHybride.getTpsRecherche());
		
		System.out.println("-> Temps de suppressions : ");
		printTenLastTimesAndAverage(testHybride.getTpsSuppression());
		FileUtils.storeInTxt(doc, "-> Temps de suppressions : ",testHybride.getTpsSuppression());
		
		System.out.println("-> Temps de comptage de mots : ");
		printTenLastTimesAndAverage(testHybride.getTpsComptageMots());
		FileUtils.storeInTxt(doc, "-> Temps de comptage de mots : ",testHybride.getTpsComptageMots());
		
		System.out.println("-> Temps de prefixe : ");
		printTenLastTimesAndAverage(testHybride.getTpsPrefixe());
		FileUtils.storeInTxt(doc, "-> Temps de prefixe : ",testHybride.getTpsPrefixe());

		FileUtils.storeInTxt(doc, "", "\n===============================================\n\n");
	}
	
	/**Affiche les 10 derniers temps de la liste passée en paramètres
	 * (les premiers temps étant anormalement plus grands)
	 * 
	 * @param list
	 */
	private static void printTenLastTimesAndAverage(List<BigDecimal> list){
		
		BigDecimal tot = new BigDecimal(0);
		int i = 1;
		for(BigDecimal res : list){
			System.out.println(i+ " : " + res+" ms");
			tot = tot.add(res);
			i++;
		}
		
		System.out.println("Moyenne de : "+tot.divide(new BigDecimal(list.size()), MathContext.DECIMAL32)+" ms\n");
	}
	
	private static void init(TestsTempsSuperclass testHybride) {
		for(int i=0; i<5; i++){ 
			testHybride.addTpsConstruction(testHybride.tempsConstruction());
			testHybride.addTpsInsertion(testHybride.tempsInsertion(mots[i]));
			testHybride.addTpsRecherche(testHybride.tempsRecherche(mots[i]));
			testHybride.addTpsSuppression(testHybride.tempsSuppression(mots[i]));
			testHybride.addTpsComptageMots(testHybride.tempsComptageMots());
			testHybride.addTpsPrefixe(testHybride.tempsPrefixe(mots[i]));
			testHybride.resetArbre();
		}
	}


}
