import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
//parent of File and Directory 
//Abstract since we dont want to initialize an item 
public abstract class Item implements Comparable<Item>{
	//an item has a name and creation date
	final int MAX_NAME_LENTH = 32;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	protected String name;
	protected Date creationDate;
	
	public Item(String name) throws Exception {
		setName(name);
		this.creationDate = new Date();
	}
	//comparator by name(string), the default way to compare items(files) 
	@Override
	public int compareTo(Item arg0) {
		return (this.getName().compareTo(arg0.getName()));
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) throws Exception{
		if(name.length() > MAX_NAME_LENTH)
			throw new Exception("Name is too long"); 
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}
	
	//print the Item according to its depth from root
	//depth(int) number of tabs required before string
	public String toString(int depth) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < depth; i++)
			builder.append("\t");
		builder.append("Name:" + this.getName() + "\tDate:" + this.getCreationDate());
		return builder.toString();  
	}
	
}
