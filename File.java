
public class File extends Item{
	//A File is an Item with Data(Size)
	private long size;
	 
	//Construct a File with positive size
	public File(String name,long size) throws Exception {
		super(name);
		if(size < 0)
			throw new Exception("File size must be a positive number");
		this.size = size;
	}
	
	public File(String name) throws Exception {
		super(name);
		this.size = 0;
	}
	
	public long getSize() {
		return size;
	}  
	
	//print the File according to its depth from root
	//depth(int) number of tabs required before string
	@Override
	public String toString(int depth) {
		return super.toString(depth) + "\tSize:" + this.getSize(); 
	}
}
