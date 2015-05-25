package operation;


import java.util.ArrayList;
import java.util.HashMap;

import block.Block;
import block.FreeBlock;
import block.SuperBlock;

public class DiskOperation {

	private HashMap<Integer, Block> blocks;
	
	public DiskOperation(SuperBlock superblock, ArrayList<FreeBlock> freeblocks) {
		this.blocks = new HashMap<Integer, Block>();
		this.blocks.put(superblock.getBlockNumber(), superblock);
		for(FreeBlock fb : freeblocks) {
			this.blocks.put(fb.getBlockNumber(), fb);
		}
	}
	
	public Block read(int blockNumber) {
		return blocks.get(blockNumber);
	}
	
	public void write(Block block) {
		this.blocks.put(block.getBlockNumber(), block);
	}
	
	public String toString() {
		String str = "";
		for(Block b : blocks.values()) {
			str += "block: " + b.getBlockNumber() + "\t-> " + b.getType() + "\n";
		}
		return str;
	}
	
}
