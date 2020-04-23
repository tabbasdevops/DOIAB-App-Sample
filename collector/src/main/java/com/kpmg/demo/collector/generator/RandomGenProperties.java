/**
 * 
 */
package com.kpmg.demo.collector.generator;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


import lombok.Getter;
import lombok.Setter;

/**
 * Properties for Random Generator class
 * 
 * @author aantipov
 */
@Getter @Setter 
@Configuration
@ConfigurationProperties("random-gen")
public class RandomGenProperties {
	
	/**
	 * Template item 
	 */
	@Getter @Setter 
	public static class Template {
		String code;
		String name;
		float low;
		float high;
	}
	
	List<Template> templates;
}

