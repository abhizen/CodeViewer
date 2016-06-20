package FileIndexer.FileIndexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class FileReader implements Operator{
BufferedReader reader = null;
	
	public FileReader(BufferedReader reader){
		this.reader = reader;
	}
	
	public List<String> getStream(){

		List<String> stream = new ArrayList();
		String line = null;
		try{
			if((line=reader.readLine())!=null){
				stream.add(line);
			}
			else
				return null;
		}
		catch(IOException ex){
				ex.printStackTrace();
		}
		return stream;
	}
}