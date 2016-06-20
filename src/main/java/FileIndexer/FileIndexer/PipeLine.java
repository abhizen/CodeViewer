package FileIndexer.FileIndexer;

import java.io.BufferedReader;

public class PipeLine {
private BufferedReader reader = null;
private Operator operator = null; 
	
	public PipeLine(BufferedReader reader){
		this.reader= reader; 
		createPipeLine();
	}
	
	private void createPipeLine(){
	
		operator = new Tokenizer(new FileReader(reader));
		
	}
	public Operator getOperator(){
		return operator;
	}
}
