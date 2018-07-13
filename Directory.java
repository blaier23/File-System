import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Directory extends Item{
	//A Directory is an Item which may contain items
	private Set<Item> contained = new TreeSet<Item>();

	public Directory(String name) throws Exception {
		super(name);
	} 
	 
	//change the ordering of items in the directory
		public void setComparator(Comparator<Item> comp) {
			Set<Item> temp = new TreeSet<Item>(comp);
			temp.addAll(this.contained);
			this.contained = temp;
			
		}
	//adding file or folder(adding the actual item(reference)) - CUT
	public boolean addItem(String parentDirName, Item item) {
		if(parentDirName.equals(this.getName()))
			return this.contained.add(item);
		
		//otherwise iterate over all sub directories and files, if directory found, add into it		
		Iterator<Item> iterator = this.contained.iterator();
		while(iterator.hasNext()) {
			Item temp = iterator.next();
			if(temp instanceof Directory)
				if(((Directory) temp).addItem(parentDirName, item))
					return true;
		}
		return false;
	}
	
	public boolean deleteItem(String fileName) throws Exception {
		//create item to compareTo for Deletion
		Item toDelete = new Directory(fileName);
		
		// Is it within the container?
		if(this.contained.contains(toDelete))
			return this.contained.remove(toDelete);
		
		//otherwise iterate over all sub directories and files, if found , delete it
		Iterator<Item> iterator = this.contained.iterator();
		while(iterator.hasNext()) {
			Item temp = iterator.next();
			if(temp instanceof Directory)
				if(((Directory) temp).deleteItem(fileName))
					return true;
		}
		return false;
	}
	 
	//print the Folder and all its contained Items according to its depth from root
	//depth(int) number of tabs required before string
	@Override
	public String toString(int depth) {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString(depth));
		Iterator<Item> iterator = this.contained.iterator();
		while(iterator.hasNext()) {
			Item temp = iterator.next();
			builder.append("\n\t" + temp.toString(depth+1));
		}
		return builder.toString();
	}

}
