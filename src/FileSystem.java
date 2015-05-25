import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import operation.DiskOperation;
import operation.FileOperation;
import util.ConvertSize;
import block.DirectoryDataBlock;
import block.FreeBlock;
import block.SuperBlock;

public class FileSystem {
	
	private static final double DISK_SIZE = ConvertSize.convertToBit("100M");
	private double blockSize;
	private DiskOperation disk;
	private FileOperation file;
	
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
		
		for(int i=0; i<(int) Math.ceil((numberOfFreeblock+2)/blockSize); i++) {
			FreeBlock fb = freeblocks.get(i);
			int x = (int) ((numberOfFreeblock+2) - i*blockSize);
			for(int n=0; n<x ; n++) {
				fb.setBlockAsUsed(n);
			}
		}
		
		
		// init root directory
		int blockNumberOfRoot = numberOfFreeblock+1;
		DirectoryDataBlock root = new DirectoryDataBlock(blockNumberOfRoot);
		superblock.setLocationOfRootDirectory(blockNumberOfRoot);
		
		// init disk operation
		disk = new DiskOperation(superblock, freeblocks);
		disk.write(root);
		System.out.println(disk);
		
		// init file operation
		file = new FileOperation(disk);
	}
	
	public void consistencyCheck() {
		
	}
	
	public void efficiencyCheck() {
		
	}
	
	public FileOperation getFileOperation() {
		return file;
	}
	
	
	public static void main(String[] args) {
//		// for set output to file using System.out.println
//		File file = new File("test.txt");
//		FileOutputStream fis;
//		try {
//			fis = new FileOutputStream(file);
//			PrintStream out = new PrintStream(fis);
//			System.setOut(out);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		FileSystem fs = new FileSystem("2K");
		fs.initFileSystem();
		
		FileOperation fOperation = fs.getFileOperation();
		fOperation.createFile("one");
		fOperation.createFile("two");
	}
	
}
