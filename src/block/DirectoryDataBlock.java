package block;

import java.util.HashMap;

public class DirectoryDataBlock extends Block {

	private HashMap<String, Integer> nameWithBlockNumber;
	
	public DirectoryDataBlock(int number) {
		super(number);
		this.nameWithBlockNumber = new HashMap<String, Integer>();
	}
	
	public void addMetadataBlock(MetadataBlock block) {
		this.nameWithBlockNumber.put(block.getName(), block.getBlockNumber());
	}
	
	public boolean contain(String name) {
		return this.nameWithBlockNumber.containsKey(name);
	}
	
	public int getBlockNumber(String name) {
		return this.nameWithBlockNumber.get(name);
	}
	
	@Override
	public String getType() {
		return "dir. block";
	}

}
