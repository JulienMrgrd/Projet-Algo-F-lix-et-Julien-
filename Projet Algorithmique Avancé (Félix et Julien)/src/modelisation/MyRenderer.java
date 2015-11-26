package modelisation;

import java.awt.Color;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import trieHybride.TrieHybride;

class MyRenderer extends DefaultTreeCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,ImageIcon> icons;
	HashMap<TrieHybride,ImageIcon> affiches;
	int cpt = 0;
	
    public MyRenderer() {
    	affiches = new HashMap<>();
    	this.icons = new HashMap<>();
    	ImageIcon leaf = createImageIcon("/leaf.png");
    	ImageIcon dirSup = createImageIcon("/dir_sup.png");
    	ImageIcon dirInf = createImageIcon("/dir_inf.png");
    	ImageIcon dirEq = createImageIcon("/dir_eq.png");
    	ImageIcon dirEmpty = createImageIcon("/dir_empty.png");
    	
    	leaf.setDescription("leaf");
    	dirSup.setDescription("dirSup");
    	dirInf.setDescription("dirInf");
    	dirEq.setDescription("dirEq");
    	dirEmpty.setDescription("dirEmpty");
    	
    	icons.put("leaf", leaf );
    	icons.put("sup", dirSup );
    	icons.put("eq", dirEq );
    	icons.put("inf", dirInf );
    	icons.put("empty", dirEmpty );
    }
    
	    @Override
    public Component getTreeCellRendererComponent(
                        JTree tree,
                        Object value,
                        boolean sel,
                        boolean expanded,
                        boolean leaf,
                        int row,
                        boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, sel,
                expanded, leaf, row,
                hasFocus);
                        
        TrieHybride trie = ((TrieHybride)value);
		char c = trie.getClef();
		
		if(trie.isFinDeMot()) setForeground(Color.RED);
		
		if(c=='/') setIcon(icons.get("empty"));

		else if (c=='.') setIcon(icons.get("leaf"));
        
		else{
        	if(affiches.containsKey(trie)){
    			setIcon(affiches.get(trie));
    		} else {
    			TrieHybride parent = (TrieHybride) tree.getPathForRow(row).getParentPath().getLastPathComponent();
    			if(trie.equals(parent.getInf())){
    				setIcon(icons.get("inf"));
    				affiches.put(trie, icons.get("inf"));
    			} else if (trie.equals(parent.getEq())){
    				setIcon(icons.get("eq"));
    				affiches.put(trie, icons.get("eq"));
    			} else if (trie.equals(parent.getSup())){
    				setIcon(icons.get("sup"));
    				affiches.put(trie, icons.get("sup"));
    			}
    		}
        }
		
        return this;
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. 
	 * @throws FileNotFoundException */
    protected static ImageIcon createImageIcon(String path) {
    	URL ressource = MainAffichageTrieHybride.class.getClass().getResource(path);
        if (ressource != null) {
            return new ImageIcon(ressource);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
}

