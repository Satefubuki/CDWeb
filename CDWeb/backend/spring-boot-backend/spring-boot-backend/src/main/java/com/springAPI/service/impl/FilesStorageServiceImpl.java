package com.springAPI.service.impl;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import javax.swing.plaf.basic.BasicToolBarUI.DockingListener;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.staticmock.MockStaticEntityMethods;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import com.springAPI.service.FilesStorageService;
@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  private final Path root = Paths.get("uploads");

  @Override
  public void init() {
    try {
      Files.createDirectory(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public String save(MultipartFile file) {
	  String currentDate = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
	 // file.getOriginalFilename().replace(file.getOriginalFilename(), FilenameUtils.getBaseName(file.getOriginalFilename()).concat(currentDate) + "." + FilenameUtils.getExtension(file.getOriginalFilename())).toLowerCase();
	  try {
      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename().replace(file.getOriginalFilename(), FilenameUtils.getBaseName(file.getOriginalFilename()).concat(currentDate) + "." + FilenameUtils.getExtension(file.getOriginalFilename())).toLowerCase()));

      return file.getOriginalFilename().replace(file.getOriginalFilename(), FilenameUtils.getBaseName(file.getOriginalFilename()).concat(currentDate) + "." + FilenameUtils.getExtension(file.getOriginalFilename())).toLowerCase();
    } catch (Exception e) {
     // throw new RuntimeException("Could not store the file. Error: " + e.getMessage());

    	return file.getOriginalFilename().replace(file.getOriginalFilename(), FilenameUtils.getBaseName(file.getOriginalFilename()).concat(currentDate) + "." + FilenameUtils.getExtension(file.getOriginalFilename())).toLowerCase();
    }
  }

  @Override
  public Resource load(String filename) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
  }
  
  @Override
  public void deleteOne(String name) {
   
    String rootPath = root.toString();
    File file = new File(rootPath + "/" + name);
    System.out.println(file.getAbsolutePath());
    file.delete();
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }

@Override
public String doUpload(MultipartFile file) {
	return null;
}
}
