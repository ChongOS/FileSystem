package block;

import java.util.Date;

import exception.DirectoryNameTooLongException;

public abstract class MetadataBlock extends Block {

	private String name;
	private Date createdDate;
	private Date lastModifiedDate;
	
	public MetadataBlock(String name, int number) {
		super(number);
		this.name = name;
		this.createdDate = new Date();
		this.lastModifiedDate = new Date();
	}
	
	public String getName() {
		return name;
	}
	
	public String getCreatedDate() {
		return createdDate.toString();
	}
	
	public String getLastModifiedDate() {
		return lastModifiedDate.toString();
	}
	
}
