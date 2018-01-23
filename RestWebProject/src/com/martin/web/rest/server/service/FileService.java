package com.martin.web.rest.server.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/fileUpload")
public class FileService {
	private @Context HttpServletRequest request;
	private @Context HttpServletResponse response;
	
	@POST
	@Path("/formData")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	@Produces({MediaType.TEXT_PLAIN})
	public Response uploadFileFromForm(
		@FormDataParam("file") InputStream fileInputStream,
		@FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {
		
		String UPLOAD_PATH = "c:/Temporary/";
		try {
			int read = 0;
			byte[] bytes = new byte[1024];

			OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()));
			while ((read = fileInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new WebApplicationException("Error while uploading file. Please try again !!");
		}
		return Response.ok("Data uploaded successfully !!").build();
	}
	
	@POST
	@Path("/streamData/{fileName}")
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	@Produces({MediaType.TEXT_PLAIN})
	public Response uploadFileStreaming(@PathParam("fileName") String fileName) throws Exception {
		
		String UPLOAD_PATH = "c:/Temporary/";
		try (InputStream is = request.getInputStream();
			 OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileName));) {
			
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = is.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
		} catch (IOException e) {
			throw new WebApplicationException("Error while uploading file. Please try again !!");
		}
		return Response.ok("Data uploaded successfully !!").build();
	}
	
	//http://localhost:8080/RestWebProject/rest/fileUpload/download/xxx
	@GET
	@Path("/download/{fileName}")
	@Consumes(MediaType.WILDCARD)
	@Produces({MediaType.APPLICATION_OCTET_STREAM})
	public Response downloadFile(@PathParam("fileName") String fileName) {
		final String fileN = "C:/Temporary/Client Centre.pdf";
		StreamingOutput out = os -> {
	        try (FileInputStream is = new FileInputStream(new File(fileN))) {
	        	int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = is.read(bytes)) != -1) {
					os.write(bytes, 0, read);
				}
				os.flush();
	        }
		};
		 
	    return Response.ok(out, MediaType.APPLICATION_OCTET_STREAM).header("content-disposition", "attachment; filename = "+ fileName).build();
	}
}
