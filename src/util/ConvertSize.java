package util;

public class ConvertSize {

	public static double convertToBit(String size) {
		int val = Integer.parseInt(size.substring(0, size.length()-1));
		String unit = size.substring(size.length()-1, size.length());
		
		switch(unit) {
		case "K":
			return 8*val*Math.pow(2, 10);
		case "M":
			return 8*val*Math.pow(2, 20);
		case "G":
			return 8*val*Math.pow(2, 30);
		}
		return 8*Double.parseDouble(size);
		
	}

}
