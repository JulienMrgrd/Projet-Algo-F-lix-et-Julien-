package testsAvecMinuteurs;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import java.util.List;

import utils.FileUtils;

public class MainTestTempsBriandais {
	
	private static String doc = "documents/briandaisTemps.txt";
	
	public static void main(String[] args) {
		
		String[] mots = {"journee", "tante", "f", "lit", "tomate", "maman", "decoupe", "smartphone", "aiguille", "riz"};

		System.out.println("==== Test sur Arbre de la Briandais ===");
		TestsTempsSuperclass testBriandais = new TestsTempsBriandais();

		for(int i=0; i<TestsTempsSuperclass.NB_TESTS/2; i++){
			testBriandais.addTpsConstruction(testBriandais.tempsConstruction());
			testBriandais.addTpsInsertion(testBriandais.tempsInsertion(mots[i]));
			testBriandais.addTpsRecherche(testBriandais.tempsRecherche(mots[i]));
			testBriandais.addTpsSuppression(testBriandais.tempsSuppression(mots[i]));
			testBriandais.addTpsComptageMots(testBriandais.tempsComptageMots());
			testBriandais.resetArbre();
		}
		for(int i=0; i<TestsTempsSuperclass.NB_TESTS/2; i++){
			testBriandais.addTpsConstruction(testBriandais.tempsConstruction());
			testBriandais.addTpsInsertion(testBriandais.tempsInsertion(mots[i]));
			testBriandais.addTpsRecherche(testBriandais.tempsRecherche(mots[i]));
			testBriandais.addTpsSuppression(testBriandais.tempsSuppression(mots[i]));
			testBriandais.addTpsComptageMots(testBriandais.tempsComptageMots());
			testBriandais.resetArbre();
		}
		
		FileUtils.storeInTxt(doc, "", Calendar.getInstance().getTime().toString()+"\n" );
		System.out.println("-> Temps de constructions : ");
		printTenLastTimesAndAverage(testBriandais.getTpsConstruction());
		FileUtils.storeInTxt(doc, "-> Temps de constructions : ",testBriandais.getTpsConstruction());
		
		System.out.println("-> Temps d'insertion : ");
		printTenLastTimesAndAverage(testBriandais.getTpsInsertion());
		FileUtils.storeInTxt(doc, "-> Temps d'insertion : ",testBriandais.getTpsInsertion());
		
		System.out.println("-> Temps de recherche : ");
		printTenLastTimesAndAverage(testBriandais.getTpsRecherche());
		FileUtils.storeInTxt(doc, "-> Temps de recherche : ", testBriandais.getTpsRecherche());
		
		System.out.println("-> Temps de suppressions : ");
		printTenLastTimesAndAverage(testBriandais.getTpsSuppression());
		FileUtils.storeInTxt(doc, "-> Temps de suppressions : ",testBriandais.getTpsSuppression());
		
		System.out.println("-> Temps de comptage de mots : ");
		printTenLastTimesAndAverage(testBriandais.getTpsComptageMots());
		FileUtils.storeInTxt(doc, "-> Temps de comptage de mots : ",testBriandais.getTpsComptageMots());
		
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
