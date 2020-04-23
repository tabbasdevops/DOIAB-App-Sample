/**
 * 
 */
package com.kpmg.demo.collector.generator;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Random generator of currency exchange rate. Code is based on three "well known" currency.
 * Purpose of the class is to replace actual exchange rate provider
 * 
 * @author aantipov
 */
@Lazy @Component
public class RandomCurrencyGenerator implements ValueGenerator {

	
	@Autowired RandomGenProperties pro;
	Random rand = new Random();
	
	/**
	 * Randomly generates stock price
	 * @param symbol Stock symbol
	 */
	@Override
	public ValuePoint getCurrentValue(String code) {
		var t = pro.getTemplates().stream().filter(x -> code.equals(x.getCode())).findFirst();
		if (t.isEmpty()) {
			return new ValuePoint(code, String.format("Name for %s", code), rand.nextFloat(), LocalDateTime.now()); 
		} else {
			return new ValuePoint(code, t.get().getName(), 
					t.get().getLow() + (t.get().getHigh() - t.get().getLow())*rand.nextFloat(), LocalDateTime.now()); 
		}
		
	}

}
