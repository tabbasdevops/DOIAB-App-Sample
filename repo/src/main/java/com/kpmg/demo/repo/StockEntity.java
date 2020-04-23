/**
 * 
 */
package com.kpmg.demo.repo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.Data;

/**
 * Share entity, represents company that emitted securities.
 * 
 * @author aantipov
 */
@Data
public class StockEntity {
	@Id 
	String id;
	
	@Indexed(unique = true) 
	String symbol;
	
	String name;
}
