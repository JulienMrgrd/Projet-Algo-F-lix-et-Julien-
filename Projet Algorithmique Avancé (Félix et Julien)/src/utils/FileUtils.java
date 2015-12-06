package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import metier.ArbreBriandais;

public class FileUtils {

	public static final String Document = "documents/arbreBriandais.bin";

	public static void serializeBriandaisToFile(ArbreBriandais abr) {
    	try {
    		// Enregistrement de l'arbre dans un fichier (utile pour affichage)
        	OutputStream file = new FileOutputStream( Document );
        	OutputStream buffer = new BufferedOutputStream( file );
        	ObjectOutput output = new ObjectOutputStream( buffer );
			output.writeObject(abr);
			output.close();
			buffer.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArbreBriandais briandaisFromFile(){
	
		ArbreBriandais abr = null;
		try {
			InputStream file = new FileInputStream(Document);
			InputStream buffer = new BufferedInputStream( file );
			ObjectInput input = new ObjectInputStream ( buffer );
			abr = (ArbreBriandais)input.readObject();
			
			input.close();
			buffer.close();
			file.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return abr;
	}
	
	/**
	 * Retourne la liste des mots contenus dans tous les fichiers d'un repertoire
	 */
	public static List<String> readAllFiles(File dir) {
		List<String> liste = new ArrayList<>();
		if (dir.isDirectory()) {
			File[] tabFiles = dir.listFiles();
			for (File tmp : tabFiles) {
				if (tmp.isFile()) {
					try {
						liste.addAll(readFile(tmp));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return liste;
	}
	

	/**
	 * Retourne la liste des mots (caractères alphabétiques) contenus dans un fichier
	 */
	private static List<String> readFile(File fin) throws IOException {
		ArrayList<String> liste = new ArrayList<>();

		FileInputStream fis = new FileInputStream(fin);

		Reader br = new BufferedReader(new InputStreamReader(fis));

		String line = null;
		while ((line = ((BufferedReader) br).readLine()) != null) {
			if(UtilitaireMots.isAlpha(line)) liste.add(line);
			else{
				line = line.replaceAll(UtilitaireMots.regexNonAlpha, "");
				if( ! line.isEmpty() ) liste.add(line);
			}
		}

		br.close();
		fis.close();

		return liste;
	}
	
	/**
	 * Retourne tous les mots des oeuvres de W. Shakespeare, sans doublons
	 */
	public static List<String> getListMotsShakespeareSansDoublons() {
		File dir = new File("documents/Shakespeare");
		HashSet<String> set = new HashSet<String>(FileUtils.readAllFiles(dir)); // retire les doublons
		return new ArrayList<String>(set);
	}
	

	public static List<String> getListMotsByFichierSansDoublons(String fic) {
		File dir = new File("documents/Shakespeare/"+fic+".txt");
		HashSet<String> set;
		try {
			set = new HashSet<String>(FileUtils.readFile(dir));
			return new ArrayList<String>(set);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static void storeInTxt(String fichier, String titre, Object arg) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fichier),true));
			if(arg !=null && (arg instanceof List<?>) ){
				writer.newLine();
				writer.write(titre);
				writer.newLine();
				writer.newLine();
				for(BigDecimal big : ((List<BigDecimal>)arg) ) {
					writer.write(big.toString());
					writer.newLine();
				}
			} else {
				writer.write(arg.toString());
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
