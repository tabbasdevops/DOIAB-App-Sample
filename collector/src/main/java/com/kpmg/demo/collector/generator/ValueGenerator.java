/**
 * 
 */
package com.kpmg.demo.collector.generator;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Value generator interface
 * @author aantipov
 */
public interface ValueGenerator {

	/**
	 * Generated value data object 
	 */
	@Data @AllArgsConstructor
	public static class ValuePoint {
		String code;
		String name;
		float price;
		LocalDateTime created;
	}
	
	/**
	 * Provides current data point for given code
	 * @param code Code string
	 * @return ValuePoint object
	 */
	ValuePoint getCurrentValue(String code);
}
