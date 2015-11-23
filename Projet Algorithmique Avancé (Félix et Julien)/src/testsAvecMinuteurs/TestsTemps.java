package testsAvecMinuteurs;

import interfaces.IArbre;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import utils.FileUtils;

public abstract class TestsTemps implements ITestsTemps {

	protected static List<Long> tpsConstruction;  
	protected static List<Long> tpsInsertions;  
	protected static List<Long> tpsRecherche;  
	protected static List<Long> tpsSuppression;  
	protected static List<Long> tpsComptageMots; 
	protected static IArbre arbre;
	
	static final int NB_TESTS = 5;
	
	protected static List<String> getListMotsShakespear() {
		File dir = new File("documents/Shakespear");
		return FileUtils.readAllFiles(dir);
	}
	
	protected static void initAllLists() {
		tpsConstruction = new ArrayList<>(5);
		tpsInsertions = new ArrayList<>(5);
		tpsRecherche = new ArrayList<>(5);
		tpsSuppression = new ArrayList<>(5);
		tpsComptageMots = new ArrayList<>(5);
	}
	
}
