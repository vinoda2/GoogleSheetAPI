package com.xworkz.event.configuration;

import java.io.File;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class EventInit extends AbstractAnnotationConfigDispatcherServletInitializer implements WebMvcConfigurer {
	private int maxUploadSizeInMb = 5 * 1024 * 1024;

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { Event.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	protected void customizeRegistration(Dynamic registration) {
		String tempDir = "F:\\Webapplication\\Images";
		int maxUploadZiseInMb = 3 * 1024 * 1024;
		File uploadDir = new File(tempDir);
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(uploadDir.getAbsolutePath(),
				maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);

		registration.setMultipartConfig(multipartConfigElement);
	}

}
