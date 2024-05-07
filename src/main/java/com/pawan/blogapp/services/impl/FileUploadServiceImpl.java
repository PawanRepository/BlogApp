package com.pawan.blogapp.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.pawan.blogapp.service.FileUpload_Service;

@Service
public class FileUploadServiceImpl implements FileUpload_Service {

	@Override
	public String uploadFile(String path, MultipartFile file) throws IOException {
		
		// get filename 
		String filename = file.getOriginalFilename();
		
		// generating random filename
		
		String randomID = UUID.randomUUID().toString();
		String tempfilename = randomID.concat(filename.substring(filename.lastIndexOf('.')));
		// creating full path of file
		
		String fullpath = path+ File.separator+tempfilename;
		
		// create folder if not existed
		
		File file1 = new File(path);
		
		if(!file1.exists()) {
			file1.mkdir();
		}
		
		// copy file
		
		Files.copy(file.getInputStream(), Paths.get(fullpath));
		
		
		return tempfilename;
	}

	@Override
	public InputStream getFileDetails(String path, String fileName) throws FileNotFoundException {
		
		String fullpath = path+ File.separator+fileName;
		InputStream iS = new FileInputStream(fullpath);
		return iS;
	}

}
