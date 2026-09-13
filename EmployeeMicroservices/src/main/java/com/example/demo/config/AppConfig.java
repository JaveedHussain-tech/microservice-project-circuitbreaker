package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;

@Configuration
public class AppConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		org.modelmapper.ModelMapper modelMapper=new ModelMapper();
			modelMapper.getConfiguration()
			.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		return modelMapper;
	}

}
