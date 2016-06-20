package FileIndexer.FileIndexer;

import java.io.File;
import java.util.List;

public interface DiskReader {
	
	public List<File> getFiles(String path);
}
