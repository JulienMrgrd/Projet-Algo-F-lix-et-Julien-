package testsAvecMinuteurs;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import java.util.List;

import utils.FileUtils;

public class MainTestTempsTrieHybride {
	
	private static String doc = "documents/hybrideTemps.txt";
	
	public static void main(String[] args) {

		String[] mots = {"journee", "tante", "f", "lit", "tomate", "maman", "decoupe", "smartphone", "aiguille", "riz"};

		System.out.println("==== Test sur Trie Hybride ===");
		TestsTempsSuperclass testHybride = new TestsTempsTrieHybride();
		
		for(int i=0; i<TestsTempsSuperclass.NB_TESTS/2; i++){
			testHybride.addTpsConstruction(testHybride.tempsConstruction());
			testHybride.addTpsInsertion(testHybride.tempsInsertion(mots[i]));
			testHybride.addTpsRecherche(testHybride.tempsRecherche(mots[i]));
			testHybride.addTpsSuppression(testHybride.tempsSuppression(mots[i]));
			testHybride.addTpsComptageMots(testHybride.tempsComptageMots());
			testHybride.resetArbre();
		}
		
		for(int i=0; i<TestsTempsSuperclass.NB_TESTS/2; i++){
			testHybride.addTpsConstruction(testHybride.tempsConstruction());
			testHybride.addTpsInsertion(testHybride.tempsInsertion(mots[i]));
			testHybride.addTpsRecherche(testHybride.tempsRecherche(mots[i]));
			testHybride.addTpsSuppression(testHybride.tempsSuppression(mots[i]));
			testHybride.addTpsComptageMots(testHybride.tempsComptageMots());
			testHybride.resetArbre();
		}
		
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

		FileUtils.storeInTxt(doc, "", "\n===============================================\n\n");
	}
	
	/**Affiche les 10 derniers temps de la liste passée en paramètres
	 * (les premiers temps étant anormalement plus grands)
	 * 
	 * @param list
	 */
	private static void printTenLastTimesAndAverage(List<BigDecimal> list){
		list = list.subList(4, TestsTempsSuperclass.NB_TESTS-1);
		
		BigDecimal tot = new BigDecimal(0);
		int i = 1;
		for(BigDecimal res : list){
			System.out.println(i+ " : " + res+" ms");
			tot = tot.add(res);
			i++;
		}
		
		System.out.println("Moyenne de : "+tot.divide(new BigDecimal(list.size()), MathContext.DECIMAL32)+" ms\n");
	}


}
