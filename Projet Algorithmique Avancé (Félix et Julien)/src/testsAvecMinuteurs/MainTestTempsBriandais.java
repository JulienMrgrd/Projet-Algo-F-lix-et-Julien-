package testsAvecMinuteurs;

import java.math.BigDecimal;
import java.util.List;

public class MainTestTempsBriandais {
	
	public static void main(String[] args) {
		
//		String[] mots = {"year", "bianca", "rjkbrr", "e", "pomfret", "what", "this", "read", "a", "see"};
//		String[] motsAInserer = {"journee", "tante", "f", "lit", "tomate", "maman", "decoupe", "smartphone", "aiguille", "riz"};

		System.out.println("==== Test sur Arbre de la Briandais ===");
		TestsTempsSuperclass testBriandais = new TestsTempsBriandais();
		
		for(int i=0; i<TestsTempsSuperclass.NB_TESTS; i++){
			testBriandais.addTpsConstruction(testBriandais.tempsConstruction());
			testBriandais.addTpsInsertion(testBriandais.tempsInsertion("journee"));
			testBriandais.addTpsRecherche(testBriandais.tempsRecherche("year"));
			testBriandais.addTpsSuppression(testBriandais.tempsSuppression("journee"));
			testBriandais.addTpsComptageMots(testBriandais.tempsComptageMots());
			testBriandais.resetArbre();
		}
		
		System.out.println("-> Temps de constructions : ");
		printAllTimesAndAverage(testBriandais.getTpsConstruction());
		System.out.println("-> Temps d'insertion : ");
		printAllTimesAndAverage(testBriandais.getTpsInsertion());
		System.out.println("-> Temps de recherche : ");
		printAllTimesAndAverage(testBriandais.getTpsRecherche());
		System.out.println("-> Temps de suppressions : ");
		printAllTimesAndAverage(testBriandais.getTpsSuppression());
		System.out.println("-> Temps de comptage de mots : ");
		printAllTimesAndAverage(testBriandais.getTpsComptageMots());
	}
	
	private static void printAllTimesAndAverage(List<BigDecimal> list){
		BigDecimal tot = new BigDecimal(0);
		for(BigDecimal res : list){
			System.out.println(res+" ms");
			tot = tot.add(res);
		}
		
		System.out.println("Moyenne de : "+tot.divide(new BigDecimal(list.size()))+" ms\n");
	}

}
