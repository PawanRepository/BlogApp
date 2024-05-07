package com.pawan.blogapp.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileUpload_Service {

	String uploadFile(String path, MultipartFile file) throws IOException;
	
	InputStream getFileDetails(String path, String fileNawme) throws FileNotFoundException;
	
}
