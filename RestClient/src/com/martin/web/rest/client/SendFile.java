package com.martin.web.rest.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

public class SendFile {
	private static void sendFileFromFrom() throws IOException {
		Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();

		FileDataBodyPart filePart = new FileDataBodyPart("file", new File("C:/Temporary/Client Centre.pdf"));
		FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
		FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("foo", "bar").bodyPart(filePart);

		WebTarget target = client.target("http://localhost:8080/RestWebProject/rest/fileUpload/formData");
		Response response = target.request().post(Entity.entity(multipart, multipart.getMediaType()));
		System.out.println(response.getStatus());
		// Use response object to verify upload success

		formDataMultiPart.close();
		multipart.close();
	}
	
	private static void sendFileFromStream() {
		WebTarget target = ClientBuilder.newBuilder().build().target("http://localhost:8080/RestWebProject/rest/fileUpload/streamData/rest.bin");
		StreamingOutput out = os -> {
	        try (FileInputStream is = new FileInputStream(new File("C:/Temporary/Client Centre.pdf"))) {
	        	int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = is.read(bytes)) != -1) {
					os.write(bytes, 0, read);
				}
				os.flush();
	        }
		};

		Response response = target.request(MediaType.TEXT_PLAIN).post(Entity.entity(out, MediaType.APPLICATION_OCTET_STREAM));
		System.out.println(response.getStatus());
	}
	
	private static void downloadFile() throws FileNotFoundException, IOException {
		WebTarget target = ClientBuilder.newBuilder().build().target("http://localhost:8080/RestWebProject/rest/fileUpload/download/xxx");
		Response response = target.request(MediaType.APPLICATION_OCTET_STREAM).get();
		
		
		if(response.getStatusInfo().getStatusCode() == Status.OK.getStatusCode()) {
			try (FileOutputStream os = new FileOutputStream(new File("C:/Temporary/download.rest"));
				 InputStream is = response.readEntity(InputStream.class)) {
				
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = is.read(bytes)) != -1) {
					os.write(bytes, 0, read);
				}
				os.flush();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		//sendFileFromFrom();
		//sendFileFromStream();
		downloadFile();
	}
}
