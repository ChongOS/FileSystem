import java.util.ArrayList;

import util.ConvertSize;
import block.FreeBlock;
import block.SuperBlock;




public class FileSystem {
	
	private static final double DISK_SIZE = ConvertSize.convertToBit("1G");
	private double blockSize;
	private Disk disk;
	
	public FileSystem(String blockSize) {
		this.blockSize = ConvertSize.convertToBit(blockSize);
	}
	
	public void initFileSystem() {
		// init superblock
		SuperBlock superblock = new SuperBlock(DISK_SIZE, blockSize, 0);
		
		// init freeblock
		int numberOfFreeblock = (int) Math.ceil(DISK_SIZE/(blockSize*blockSize));
		ArrayList<FreeBlock> freeblocks = new ArrayList<FreeBlock>();
		for(int i=0; i<numberOfFreeblock; i++) {
			FreeBlock fb = new FreeBlock(DISK_SIZE, blockSize, i+1);
			freeblocks.add(fb);
		}
		
		// init disk
		disk = new Disk(superblock, freeblocks);
		System.out.println(disk);
	}
	
	public void consistencyCheck() {
		
	}
	
	public void efficiencyCheck() {
		
	}
	
	
	public static void main(String[] args) {
		FileSystem fs = new FileSystem("2K");
		fs.initFileSystem();
	}
	
}
