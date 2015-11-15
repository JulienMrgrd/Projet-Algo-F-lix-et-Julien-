package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import arbreBriandais.ArbreBriandais;

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
	
}
