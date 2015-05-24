package block;


public abstract class Block {

	private int number;
	
	public Block(int number) {
		this.number = number;
	}
	
	public void setBlockNumber(int number) {
		this.number = number;
	}
	
	public int getBlockNumber() {
		return number;
	}
	
}
