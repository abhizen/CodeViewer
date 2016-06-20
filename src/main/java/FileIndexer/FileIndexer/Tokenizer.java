package FileIndexer.FileIndexer;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer implements Operator{
private Operator opt = null;

	public Tokenizer(Operator opt){
		this.opt = opt;
	}
		
	public List<String> getStream() {
		
		List<String> stream = opt.getStream();
		List<String> tokenStream = new ArrayList();
		
		if(stream!=null){
			for(String line : stream){
				String[] tokens = line.split("[\\s]+");
				for(String token : tokens){
					tokenStream.add(token);
				}
			}
			return tokenStream;
		}
		else
			return null;
	}

}
