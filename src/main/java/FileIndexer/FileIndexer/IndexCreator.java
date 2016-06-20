package FileIndexer.FileIndexer;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileReader;

/**
 * @author abhinit
 * This class calls DiskReader and creates pipeline for
 * each file. Then processes the stream into index.
 */
public class IndexCreator {
	private Map<String,List<String>> index = new TreeMap();
	private UnixDiskReader reader = new UnixDiskReader();
	private FileOutputStream fos = null;
            
	
	public void createIndex(String path){
		List<File> files = reader.getFiles(path);
		PipeLine pipeLine = null;
		Operator operator = null;
		List<String> stream = null;
		
		for(File file : files){
			try{
				FileReader fr = new FileReader(file);
				
				pipeLine = new PipeLine(new BufferedReader(fr));
				operator = pipeLine.getOperator();
				while((stream=operator.getStream())!=null){
					insertIntoIndex(stream,file);
				}
				serializeIndex();
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}
	
	private void insertIntoIndex(List<String> stream,File file){
		String fileId = file.getAbsolutePath();
		List<String> filePostList = null;
		int found = 0;
		
		for(String elem : stream){
			filePostList = index.get(elem);
			
			if(filePostList==null){
				filePostList = new ArrayList();
				filePostList.add(fileId);
				this.index.put(elem,filePostList);
				continue;
			}
			found = 0;
			for(String result : filePostList){
				if(result.equals(fileId)){
					found =1;
					break;
				}
			}
			
			if(found==0)
				filePostList.add(fileId);
		}
	}
	
	private void serializeIndex() throws IOException{
		try{
			fos = new FileOutputStream("/home/abhinit/Index.se");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.index);
            oos.close();
            fos.close();
		}
		catch(IOException ex){
			throw ex;
		}
	}
	
	
	public static void main(String[] args){
		String path = "/home/abhinit/HackerRankDPProblems/src";
		IndexCreator instance = new IndexCreator();
		instance.createIndex(path);
	}
}
