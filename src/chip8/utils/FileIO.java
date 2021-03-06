package chip8.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

public class FileIO {
	
	private static String fontSetLocation = "resources/fontset.txt";
	
	public static int[] getProgram(File f) {
		int[] result = new int[(int) f.length()];
		int idx = 0;
		try {
			FileInputStream fi = new FileInputStream(f);
			
			int len;
			byte data[] = new byte[32];
			
			do{
				len = fi.read(data);
				for(int i = 0; i < len; i++, idx++) {
					//TODO Check why some values have an 1 on the left.
					if(data[i] < 0) {
						result[idx] = data[i] + 256;
					}else {
						result[idx] = data[i];
					}
				}
			}while(len != -1);
			
			fi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static int[] getFontSet() {
		int[] result = null;
		try {
			File fontset = new File(fontSetLocation);
			FileReader fr = new FileReader(fontset);
			BufferedReader bfr = new BufferedReader(fr);
			
			String line;
			String fontsetString = "";
			
			while((line = bfr.readLine()) != null) {
				fontsetString += line;
			}
			String[] fontSetArray = fontsetString.split(",");
			result = new int[fontSetArray.length];
			
			for(int i = 0; i < result.length; i++) {
				result[i] = parseFullHex(fontSetArray[i]);
			}
			bfr.close();
			fr.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static int parseFullHex(String hexString) {
		return Integer.parseInt(hexString.replace("0x", ""), 16);
	}

}
