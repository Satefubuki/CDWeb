package com.springAPI.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
public interface FilesStorageService {
	public void init();

	  public String save(MultipartFile file);

	  public Resource load(String filename);

	  public void deleteAll();
	  
	  public void deleteOne(String name);

	  public Stream<Path> loadAll();
	  
	  public String doUpload(MultipartFile file);
}
