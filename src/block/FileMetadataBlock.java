package block;

import java.util.ArrayList;

import exception.FileNameTooLongException;


public class FileMetadataBlock extends MetadataBlock {
	
	private double size;
	private int linkCount;
	private ArrayList<Integer> directAddresses;
	
	public FileMetadataBlock(String name, int number) throws FileNameTooLongException {
		super(name, number);
		
		String ext = "";
		if(name.contains(".")) {
			String[] splited = name.split(".");
			name = splited[0];
			ext = splited[1];
		}
		
		if(name.length() > 32 || ext.length() > 32) throw new FileNameTooLongException();
		
		this.linkCount = 1;
		this.directAddresses = new ArrayList<Integer>();
	}
	
	public void increaseLinkCount() {
		this.linkCount++;
	}
	
	public void decreaseLinkCount() {
		this.linkCount--;
	}
	
	public boolean hasOneLinkCount() {
		return this.linkCount == 1;
	}
	
	public int getFileSize() {
		return (int) (size/8);
	}
	
	@Override
	public String getType() {
		return "file metadata";
	}

}
