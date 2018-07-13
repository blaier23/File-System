import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

// File system Root : singleton
public class FileSystem {
	private static FileSystem the_instance = null;
	
	private Set<Item> root;
	
	//only single root possible
	private FileSystem() {
		//tree set for comparator options
		root = new TreeSet<Item>();
	}
	//get singleton instance - only a single root
	public static FileSystem getInstance()
    {
        if (the_instance == null)
        	the_instance = new FileSystem();
 
        return the_instance;
    }
	
	//change the ordering of items in the root(directory)
	public void setComparator(Comparator<Item> comp) {
		Set<Item> temp = new TreeSet<Item>(comp);
		temp.addAll(this.root);
		this.root = temp;
		
	}
	
	//adding file or folder(adding the actual item(reference)) - CUT
	public boolean addItem(String parentDirName, Item item) throws Exception {
		if(parentDirName.isEmpty())
			return root.add(item);
		
		//otherwise iterate over all sub directories and files, if directory found, add into it		
		Iterator<Item> iterator = this.root.iterator();
		while(iterator.hasNext()) {
			Item temp = iterator.next();
			if(temp instanceof Directory)
				if(((Directory) temp).addItem(parentDirName, item))
					return true;
		}
		return false;
	}
	
	public boolean delete(String name) throws Exception {
		//create item to compareTo for Deletion
		Item toDelete = new Directory(name);
		
		if(name.isEmpty())
			return false;
		
		//check if its in the root
		else if(this.root.contains(toDelete))
			return this.root.remove(toDelete);
		
		//otherwise iterate over all sub directories and files, if found , delete it 
		Iterator<Item> iterator = this.root.iterator();
		while(iterator.hasNext()) {
			Item temp = iterator.next();
			if(temp instanceof Directory)
				if(((Directory) temp).deleteItem(name))
					return true;
		}
		return false;
	}
	
	//print root and all contained files and folders with identation   
	public void showFileSystem() {
		Iterator<Item> iterator = this.root.iterator();
		while(iterator.hasNext()) {
			Item temp = iterator.next();
			System.out.println(temp.toString(0));	
		}
	}
}
