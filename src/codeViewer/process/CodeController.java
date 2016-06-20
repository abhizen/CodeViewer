package codeViewer.process;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

public class CodeController {
	private static CodeController instance = new CodeController();
	private static IndexReader readerInstance = null;
	private static final String path = "/home/abhinit/Index.se";
	
	private CodeController(){}
	
	public static CodeController getInstance() throws ClassNotFoundException, IOException{
		readerInstance = new IndexReader(path);
		return instance;
	}
	
	public String searchFiles(String input){
		List<String> result = readerInstance.getFiles(input);
		return  new Gson().toJson(result!=null?result:"");
	}
}
