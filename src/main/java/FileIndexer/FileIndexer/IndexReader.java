package FileIndexer.FileIndexer;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.io.IOException;

public class IndexReader {
	private Map<String,List<String>> index = new TreeMap();
	
	IndexReader(String path) throws IOException,ClassNotFoundException{
		readIndex(path);
	}
	
	private void readIndex(String path) throws IOException,ClassNotFoundException{
		try{
		   FileInputStream fis = new FileInputStream(path);
	       ObjectInputStream ois = new ObjectInputStream(fis);
	       this.index = (Map<String,List<String>>) ois.readObject();
		}
		catch(IOException ex){
			throw ex;
		}
		catch(ClassNotFoundException ex){
			throw ex;
		}
	}
	
	public List<String> getFiles(String elem){
		return this.index.get(elem);
	}
	
	public static void main(String[] args){
		try{
			String path = "/home/abhinit/Index.se";
			IndexReader instance= new IndexReader(path);
			List<String> list = instance.getFiles("Palindrome");
			if(list!=null){
				for(String file : list)
					System.out.println(file);
			}
			list = instance.getFiles("AnagramPairs");
			if(list!=null){
				for(String file : list)
					System.out.println(file);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
