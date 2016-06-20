package codeViewer.codeViewer;

import java.io.File;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import codeViewer.process.*;

@Path("/v1/rest")
public class CodeSearchService {
	private CodeController controller = null;
	private static final String errorMessage = "Service not available"; 
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "Hello";
	}
	
	@GET
	@Path("/getMatch/{url}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMatchingFiles(@PathParam("url") String name){
		String result = null;
		try{
			controller = CodeController.getInstance();
			result = controller.searchFiles(name);
			return result;
		}
		catch(Exception ex){
			return errorMessage;
		}
	}
	
	@GET
	@Path("/getFile/{url}")
	@Produces("text/plain")
	public Response getFile(@PathParam("url") String name){
		System.out.println("path: "+name);
		name = name.replaceAll("-", "/");
		System.out.println("path: "+name);
		File file = new File("/"+name);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=\"file_from_server.log\"");
		return response.build();
	}

}
