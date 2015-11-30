package testsAvecMinuteurs;

import interfaces.IArbre;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import utils.FileUtils;

public abstract class TestsTempsSuperclass {

	protected static List<BigDecimal> tpsConstruction;  
	protected static List<BigDecimal> tpsInsertion;  
	protected static List<BigDecimal> tpsRecherche;  
	protected static List<BigDecimal> tpsSuppression;  
	protected static List<BigDecimal> tpsComptageMots; 
	protected static List<BigDecimal> tpsPrefixe;
	protected static IArbre arbre;
	static final int NB_TESTS = 20;
	List<String> liste_mots;
	
	/**
	 * Initialise toutes les listes et charge dans liste_mots
	 * tous les mots des oeuvres de Shakespeare
	 */
	public TestsTempsSuperclass(){
		initAllLists();
		liste_mots = FileUtils.getListMotsShakespeareSansDoublons();
	}
	
	protected static void initAllLists() {
		tpsConstruction = new ArrayList<BigDecimal>(NB_TESTS);
		tpsInsertion = new ArrayList<BigDecimal>(NB_TESTS);
		tpsRecherche = new ArrayList<BigDecimal>(NB_TESTS);
		tpsSuppression = new ArrayList<BigDecimal>(NB_TESTS);
		tpsComptageMots = new ArrayList<BigDecimal>(NB_TESTS);
		tpsPrefixe = new ArrayList<BigDecimal>(NB_TESTS);
	}
	
	public abstract void resetArbre();
	public IArbre getArbre(){ return arbre; }
	
	protected abstract BigDecimal tempsConstruction();
	protected abstract BigDecimal tempsInsertion(String mot);
	protected abstract BigDecimal tempsRecherche(String mot);
	protected abstract BigDecimal tempsSuppression(String mot);
	protected abstract BigDecimal tempsComptageMots();
	protected abstract BigDecimal tempsPrefixe(String mot);

	public List<BigDecimal> getTpsConstruction(){ return tpsConstruction; }
	public List<BigDecimal> getTpsInsertion(){ return tpsInsertion; }
	public List<BigDecimal> getTpsRecherche(){ return tpsRecherche; }
	public List<BigDecimal> getTpsSuppression(){ return tpsSuppression; }
	public List<BigDecimal> getTpsComptageMots(){	return tpsComptageMots; }
	public List<BigDecimal> getTpsPrefixe(){	return tpsPrefixe; }
	
	public void delete5firstElementInAllList(){
		tpsConstruction = tpsConstruction.subList(5, tpsConstruction.size());
		tpsInsertion = tpsInsertion.subList(5, tpsInsertion.size());
		tpsRecherche = tpsRecherche.subList(5, tpsRecherche.size());
		tpsSuppression = tpsSuppression.subList(5, tpsSuppression.size());
		tpsComptageMots = tpsComptageMots.subList(5, tpsComptageMots.size());
		tpsPrefixe = tpsPrefixe.subList(5, tpsPrefixe.size());
	}

	public void addTpsConstruction(BigDecimal res){ tpsConstruction.add(res); }
	public void addTpsInsertion(BigDecimal res){ tpsInsertion.add(res); }
	public void addTpsRecherche(BigDecimal res){ tpsRecherche.add(res); }
	public void addTpsSuppression(BigDecimal res){ tpsSuppression.add(res); }
	public void addTpsComptageMots(BigDecimal res){ tpsComptageMots.add(res); }
	public void addTpsPrefixe(BigDecimal res){ tpsPrefixe.add(res); }
	
}
