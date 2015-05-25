package block;

import exception.DirectoryNameTooLongException;


public class DirectoryMetadataBlock extends MetadataBlock {
	
	private int directoryDataBlockNumber;
	
	public DirectoryMetadataBlock(String name, int number) throws DirectoryNameTooLongException {
		super(name, number);
		if(name.length() > 32) throw new DirectoryNameTooLongException();
	}

	public void setDirectoryDataBlockNumber(int number) {
		this.directoryDataBlockNumber = number;
	}
	
	public int getDirectoryDataBlockNumber() {
		return this.directoryDataBlockNumber;
	}
	
	@Override
	public String getType() {
		return "dir. metadata";
	}

}
