package block;


public class FreeBlock extends Block {
	
	private int[] freeblock;
	
	public FreeBlock(double diskSize, double blockSize, int blockNumber) {
		super(blockNumber);
		
		int length = (int) (diskSize / blockSize);
		freeblock = new int[length];
		for(int i=0; i<length; i++) {
			freeblock[i] = 1;
		}
	}
 	
	public void setBlockAsNotEmpty(int blockNumber) {
		freeblock[blockNumber-1] = 0;
	}
	
	public void setBlockAsEmpty(int blockNumber) {
		freeblock[blockNumber-1] = 1;
	}
	
}
