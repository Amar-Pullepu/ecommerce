package com.ecommerce.service.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Part;

import org.springframework.stereotype.Component;

@Component
public class Utilities {
	
	private final String BaseUrl = "src/main/webapp";
	
	public Map<String, String> getTypes(Field[] fields){
		Map<String, String> types = new HashMap<String, String>();
		for(int itr = 0; itr < fields.length; itr++) {
			if(fields[itr].getName().equals("ImageUrl"))	types.put(fields[itr].getName(), "file");
			else if(fields[itr].getName().equals("Id"))	continue;
			else if(fields[itr].getType().toString().equals(Set.class.toString()))	continue;
			else if(fields[itr].getType().toString().equals(Timestamp.class.toString()))	continue;
			else if(fields[itr].getType().toString().equals(Double.class.toString()))	types.put(fields[itr].getName(), "number");
			else if(fields[itr].getType().toString().equals(Integer.class.toString()))	types.put(fields[itr].getName(), "number");
			else if(fields[itr].getType().toString().equals(String.class.toString()))	types.put(fields[itr].getName(), "text");
			else if(fields[itr].getType().toString().equals(Boolean.class.toString()))	types.put(fields[itr].getName(), "checkbox");
			else types.put(fields[itr].getName(), "select");
		}
		return types;
	}
	
	public void saveImage(Part img, String url) throws IOException{
		File imgFile = new File(BaseUrl+url);
		imgFile.createNewFile();
		OutputStream iOut = new FileOutputStream(imgFile);
		InputStream imgContent = img.getInputStream();
		int read = 0;
        final byte[] bytes = new byte[1024];
        while ((read = imgContent.read(bytes)) != -1) {
            iOut.write(bytes, 0, read);
        }
	}
}
