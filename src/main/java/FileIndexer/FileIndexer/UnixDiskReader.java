package FileIndexer.FileIndexer;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class UnixDiskReader implements DiskReader{
private List<File> files = new ArrayList();

	public List<File> getFiles(String path) {
		File file = new File(path);
		search(file);
		
		return files;
	}
	
	private void search(File file){
		
		if(file.isDirectory()){
			if(file.canRead()){
				for(File temp : file.listFiles()){
					if(temp.isDirectory()){
						search(temp);
					}
					else{
						files.add(temp);
					}
				}
			}
		}
	}

}
