

import java.util.ArrayList;
import java.util.HashMap;

import block.Block;
import block.FreeBlock;
import block.SuperBlock;

public class Disk {

	private HashMap<Integer, Block> blocks;
	
	public Disk(SuperBlock superblock, ArrayList<FreeBlock> freeblocks) {
		this.blocks = new HashMap<Integer, Block>();
		this.blocks.put(superblock.getBlockNumber(), superblock);
		for(FreeBlock fb : freeblocks) {
			this.blocks.put(fb.getBlockNumber(), fb);
		}
	}
	
	public void read(int blockNumber) {
		
	}
	
	public void write(int blockNumber, int size) {
		
	}
	
	public String toString() {
		String str = "";
		for(Block b : blocks.values()) {
			str += "block: " + b.getBlockNumber() + "\tsize: " + b.getClass() + "\n";
		}
		return str;
	}
	
}
