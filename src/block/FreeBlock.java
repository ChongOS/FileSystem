package block;


public class FreeBlock extends Block {
	
	private int[] freeblock;
	
	public FreeBlock(double diskSize, double blockSize, int blockNumber) {
		super(blockNumber);
		
		freeblock = new int[(int) blockSize];
		for(int i=0; i<(int) blockSize; i++) {
			freeblock[i] = 1;
		}
	}
 	
	public void setBlockAsUsed(int blockNumber) {
		freeblock[blockNumber] = 0;
	}
	
	public void setBlockAsEmpty(int blockNumber) {
		freeblock[blockNumber] = 1;
	}
	
	public boolean hasEmptyBlock() {
		for(int i : freeblock) {
			if(i == 1)
				return true;
		}
		return false;
	}
	
	public int getBlockEmpty() {
		for(int i=0; i<freeblock.length; i++) {
			if(freeblock[i] == 1)
				return i;
		}
		return -1;
	}
	
	@Override
	public String getType() {
		return "free block";
	}
	
}
