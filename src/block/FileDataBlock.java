package block;

public class FileDataBlock extends Block {

	public FileDataBlock(int number) {
		super(number);
	}
	
	@Override
	public String getType() {
		return "file block";
	}

}
