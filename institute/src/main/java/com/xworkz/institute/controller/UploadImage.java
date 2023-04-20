package com.xworkz.institute.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class UploadImage {
	
	public UploadImage() {
		log.info("this is upload constructor");
	}
	
	@PostMapping("/imageupload")
	public String onUpload(@RequestParam ("image") MultipartFile multipartFile) throws IOException {
	System.out.println("multipartFile:"+multipartFile);	
	//System.out.println(multipartFile.getContentType());
	
	byte[] bytes=multipartFile.getBytes();
	Path path=Paths.get("F:\\Webapplication\\Images"+multipartFile.getOriginalFilename());	
	Files.write(path, bytes);
	return "Upload";
	}
	
	@GetMapping("/download")
	public void onDownload(HttpServletResponse response, @RequestParam String fileName) throws IOException {
		response.setContentType("image/jpeg");
		File file = new File("F:\\Webapplication\\Images" + fileName);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ServletOutputStream out = response.getOutputStream();
	    IOUtils.copy(in, out);
		response.flushBuffer();
	}
}
