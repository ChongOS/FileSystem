package block;


import java.util.Date;

public class SuperBlock extends Block {
	
	private double diskSize;
	private double blockSize;
	private int locationOfRootDirectory;
	private Date lastConsistencyCheck;
	
	public SuperBlock(double diskSize, double blockSize, int blockNumber) {
		super(blockNumber);
		this.diskSize = diskSize;
		this.blockSize = blockSize;
	}
	
	public double getDiskSize() {
		return this.diskSize;
	}
	
	public double getBlockSize() {
		return this.blockSize;
	}

	public void setLocationOfRootDirectory(int blockNumber) {
		this.locationOfRootDirectory = blockNumber;
	}
	
	public int getLocationOfRootDirectory() {
		return this.locationOfRootDirectory;
	}

	@Override
	public String getType() {
		return "super block";
	}
}
