package block;

import java.util.Date;

import exception.NameTooLongException;

public class DirectoryMetadataBlock extends Block {

	private String name;
	private Date createdDate;
	private Date lastModifiedDate;
	
	public DirectoryMetadataBlock(String name, int number) {
		super(number);
		
		if(name.length() > 32) throw new NameTooLongException();
		
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
