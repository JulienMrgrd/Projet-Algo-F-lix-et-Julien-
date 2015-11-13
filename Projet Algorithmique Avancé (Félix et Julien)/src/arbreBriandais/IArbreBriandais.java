package arbreBriandais;

import java.util.List;

public interface IArbreBriandais {
	
	public void insererMot(String mot);
	public void insererPhrase(String phrase);
	public boolean rechercherMot(String mot);
	public int comptageMots();
	public int comptageNil();
	public List<String> listeMots();
	public int hauteur();
	
	
}
