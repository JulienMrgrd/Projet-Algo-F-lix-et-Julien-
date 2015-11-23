package testsAvecMinuteurs;

import interfaces.IArbre;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import utils.FileUtils;
import arbreBriandais.ArbreBriandais;

public class TestsTempsBriandaisAvecShakespear extends TestsTemps {
	
	// Liste de TestsTemps
//	protected static List<Double> tpsConstruction;  
//	protected static List<Double> tpsInsertions;  
//	protected static List<Double> tpsRecherche;  
//	protected static List<Double> tpsSuppression;  
//	protected static List<Double> tpsComptageMots; 
	
	public static void main(String[] args){
		initAllLists();
		arbre = new ArbreBriandais();
		
		List<String> listeMots = getListMotsShakespear();
		
//		for(int i=0; i<NB_TESTS; i++){
//			tpsConstruction.set(i,testConstruction());
//		}
	}

	@Override
	public long testConstruction() {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
//	protected static long testConstruction() {
//		long avant1 = System.currentTimeMillis();
//		for(String mot : listeMots) arbre.insererMot(mot);
//		long apres1 = System.currentTimeMillis();
//
//		long temps1 = apres1 - avant1;
//	}
	
	
}
