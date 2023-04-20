package com.xworkz.cropinsurance.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.xworkz.cropinsurance")
public class CropInsurationConfiguration {

	public CropInsurationConfiguration() {
		System.out.println("this is CropInsurationConfiguration");
	}
	@Bean
	public ViewResolver  resolver() {
		return new InternalResourceViewResolver("/", ".jsp");
	}
	@Bean
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
	LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();	
		return bean;
		
	}
}
