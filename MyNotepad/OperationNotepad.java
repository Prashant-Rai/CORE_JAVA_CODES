import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class OperationNotepad {
	
	static String readFile(String path) throws IOException{
		FileInputStream fs = new FileInputStream(path);
		StringBuffer sb = new StringBuffer();
		int singleByte = fs.read();  // read singleByte
		while(singleByte!=-1){
			sb.append((char)singleByte);
			singleByte = fs.read();
		}
		fs.close();  // close the file
		return sb.toString();
	}
	
	static void writeFile(String path , String data) throws IOException{
		FileOutputStream fo = new FileOutputStream(path,true);
		fo.write(data.getBytes());
		fo.close();
		//System.out.println("Done...");
	}

}
