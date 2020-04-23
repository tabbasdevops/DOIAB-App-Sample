/**
 * 
 */
package com.kpmg.demo.collector.model;

import lombok.Data;

/**
 * Stock DTO, represents company that emitted securities.
 * 
 * @author aantipov
 */
@Data
public class Stock {
	String id;
	String symbol;
	String name;
}
