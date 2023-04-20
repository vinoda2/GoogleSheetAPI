package com.xworkz.institute.configuration;

import java.io.File;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class InstituteIWebInit extends AbstractAnnotationConfigDispatcherServletInitializer
		implements WebMvcConfigurer {
	private int maxUploadSizeInMb = 5 * 1024 * 1024; 
	public InstituteIWebInit() {
		System.out.println(" InstituteIWebInit ");
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println(" InstituteIWebInit getRootConfigClasses");
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println(" InstituteIWebInit getServletConfigClasses");
		return new Class[] { InstituteConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		System.out.println(" InstituteIWebInit getServletMappings");
		return new String[] { "/" };
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	protected void customizeRegistration(Dynamic registration) {
		String tempDir="F:\\Webapplication\\Images";
		int maxUploadZiseInMb=3*1024*1024;
		File uploadDir=new File(tempDir);
		 MultipartConfigElement multipartConfigElement =
	                new MultipartConfigElement(uploadDir.getAbsolutePath(),
	                        maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);

	        registration.setMultipartConfig(multipartConfigElement);
	}
	
}
