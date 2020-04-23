/**
 * 
 */
package com.kpmg.demo.repo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.Data;

/**
 * Share price entity. Represent share price at specific time.
 * 
 * @author aantipov
 */
@Data
public class PriceEntity {
	
	@Id
	String id;
	
	@Indexed(unique = false)
	String symbol;
	
	BigDecimal price;
	LocalDateTime created;
}
