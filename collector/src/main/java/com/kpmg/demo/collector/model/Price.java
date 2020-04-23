/**
 * 
 */
package com.kpmg.demo.collector.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * Share price DTO. Represent share price at specific time.
 * 
 * @author aantipov
 */
@Data
public class Price {
	String id;
	String symbol;
	BigDecimal price;
	LocalDateTime created;
}
