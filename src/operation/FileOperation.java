package operation;

import java.io.ObjectInputStream.GetField;

import block.DirectoryDataBlock;
import block.DirectoryMetadataBlock;
import block.FileMetadataBlock;
import block.FreeBlock;
import block.SuperBlock;
import exception.AlreadyExistNameException;
import exception.DirectoryNameTooLongException;
import exception.FileNameTooLongException;
import exception.FileNotFoundException;

public class FileOperation {
	
	private DiskOperation disk;
	private double blockSize;
	private int operationTime;
	
	public FileOperation(DiskOperation disk) {
		this.disk = disk;
		this.blockSize = ((SuperBlock) disk.read(0)).getBlockSize();
	}
	
	public void createDirectory(String pathname) {
		
	}
	
	public void createFile(String name) {
		operationTime = 0;
		SuperBlock superblock = this.getSuperBlock();
		int root = superblock.getLocationOfRootDirectory();
		int blockNumber = getEmptyBlockNumber(1)[0];
		
		// for writeDisk(0) ....
		
		try {
			DirectoryDataBlock dataBlock = this.getDirectoryDataBlock(root, name);
			
			FileMetadataBlock fileMetadataBlock = new FileMetadataBlock(this.getNameFromPath(name), blockNumber);
			dataBlock.addMetadataBlock(fileMetadataBlock);
			
			System.out.println("writeDisk("+fileMetadataBlock.getBlockNumber()+")\t-> "+fileMetadataBlock.getType());
			
		} catch (AlreadyExistNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNameTooLongException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public void writeFile(String name, int size) {
		operationTime = 0;
		int useBlock = (int) Math.ceil(size/blockSize);
		
		
			
	}
	
	

	private int findFreeBlockHasEmpty() {
		int i = 1;
		while(!((FreeBlock) disk.read(i)).hasEmptyBlock()) {
			i++;
		}
		return i;
	}
	
	private int[] getEmptyBlockNumber(int number) {
		int[] blockNumbers = new int[number];
		int freeBlockNumber = this.findFreeBlockHasEmpty();
		
		FreeBlock freeBlock = (FreeBlock) disk.read(freeBlockNumber);
		operationTime++;
		System.out.println("readDisk("+freeBlockNumber+")\t-> "+freeBlock.getType());
	
		operationTime++;	
		System.out.println("writeDisk("+freeBlockNumber+")\t-> "+freeBlock.getType());
		
		for(int i=0; i<number; i++) {
			
			freeBlock = (FreeBlock) disk.read(freeBlockNumber);
			
			if(!freeBlock.hasEmptyBlock()) {
				freeBlockNumber = this.findFreeBlockHasEmpty();
				freeBlock = (FreeBlock) disk.read(freeBlockNumber);
				operationTime++;
				System.out.println("readDisk("+freeBlockNumber+")\t-> "+freeBlock.getType());
			
				operationTime++;	
				System.out.println("writeDisk("+freeBlockNumber+")\t-> "+freeBlock.getType());
			}
			
			int offset = freeBlock.getBlockEmpty();
			freeBlock.setBlockAsUsed(offset);
			disk.write(freeBlock);
			
			blockNumbers[i] = (int) ((freeBlockNumber-1)*blockSize + offset);
			
		}
		
		return blockNumbers;
	}
	
	private SuperBlock getSuperBlock() {
		operationTime++;
		SuperBlock superblock = (SuperBlock) disk.read(0);
		System.out.println("readDisk(0)\t-> "+superblock.getType());
		return superblock;
	}
	
	private DirectoryDataBlock getDirectoryDataBlock(int root, String pathname) throws AlreadyExistNameException, FileNotFoundException {
		String[] path = pathname.split("\\\\");
		int dirBlockNumber = root;
		DirectoryDataBlock currentDirDataBlock = (DirectoryDataBlock) disk.read(dirBlockNumber);
		for(int i=1; i<path.length-1; i++) {
			
			if(currentDirDataBlock.contain(path[i])) {
				operationTime++;
				int dirMetadataBlockNumber = currentDirDataBlock.getBlockNumber(path[i]);
				DirectoryMetadataBlock dirMetadataBlock = (DirectoryMetadataBlock) disk.read(dirMetadataBlockNumber);
				System.out.println("readDisk("+dirMetadataBlockNumber+")\t-> "+dirMetadataBlock.getType());
				
				operationTime++;
				dirBlockNumber = dirMetadataBlock.getDirectoryDataBlockNumber();
				System.out.println("readDisk("+dirBlockNumber+")\t-> "+currentDirDataBlock.getType());
				
				currentDirDataBlock = (DirectoryDataBlock) disk.read(dirBlockNumber);
			} 
			else throw new FileNotFoundException();
		}
		
		if(currentDirDataBlock.contain(path[path.length-1])) throw new AlreadyExistNameException();
		
		return currentDirDataBlock;
	}
	
	private String getNameFromPath(String path) {
		String[] splited = path.split("/");
		return splited[splited.length-1];
	}

	
}
