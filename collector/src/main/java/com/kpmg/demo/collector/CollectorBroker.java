/**
 * 
 */
package com.kpmg.demo.collector;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.kpmg.demo.collector.generator.RandomCurrencyGenerator;
import com.kpmg.demo.collector.generator.AlphadvantageGenerator;
import com.kpmg.demo.collector.generator.ValueGenerator;
import com.kpmg.demo.collector.generator.ValueGenerator.ValuePoint;
import com.kpmg.demo.collector.model.Price;
import com.kpmg.demo.collector.model.Stock;

import lombok.extern.slf4j.Slf4j;

/**
 * Broker that collects and saves data
 * 
 * @author aantipov
 */
@Component
@Slf4j
public class CollectorBroker {
	
	@Value("#{'${collector.collect_from}'.split(',')}") List<String> collectFrom;
	@Value("${alphadvange.api.key}") String apiKey;
	
	@Autowired RepoClient repo;
	@Autowired BeanFactory factory;
	ValueGenerator generator;

	/**
	 * Post construction initialization 
	 */
	@PostConstruct
	public void init() {
		if (StringUtils.isEmpty(apiKey)) {
			generator = factory.getBean(RandomCurrencyGenerator.class);
			log.info("Random generator is created");
		} else {
			generator = factory.getBean(AlphadvantageGenerator.class);
			log.info("Alphadvantage generator is created");
		}
	}
	
	/**
	 * Entrypoint for collector
	 */
	@Scheduled(cron = "${collector.cron}")
	public void collectData() {
		log.debug("Data collection has started");
		collectFrom.forEach(symbol -> collectSingleDataObject(symbol));
		log.debug("Data collection has completed");
	}
	
	/**
	 * Collects price for single stock price.
	 * @param symbol Stock symbol
	 */
	protected void collectSingleDataObject(String symbol) {
		log.debug("{} started", symbol);
		
		var v = generator.getCurrentValue(symbol);
		if (v != null) {
			ensureStockEnityRecorded(v);
			savePriceEntity(v);
		} else {
			log.warn("Data for whatever reason is not recieved for {}", symbol);
		}
	}

	/**
	 * Ensures that DB has entity record
	 * @param rate Exchange rate object
	 */
	protected void ensureStockEnityRecorded(ValuePoint rate) {
		var stock = repo.findStockSymbol(rate.getCode());
		if (stock == null) {
			stock = new Stock();
			stock.setSymbol(rate.getCode());
			stock.setName(rate.getName());
			repo.saveStockSymbol(stock);
			log.info("New stock entity is created: {}/{}", stock.getSymbol(), stock.getName());
		} else {
			log.trace("Stock symbol is already in DB: {}", stock.getSymbol());
		}
	}
	
	/**
	 * Saves price entity
	 * @param rate Exchange rate object
	 */
	protected void savePriceEntity(ValuePoint rate) {
		var price = new Price();
		price.setSymbol(rate.getCode());
		price.setCreated(rate.getCreated());
		price.setPrice(BigDecimal.valueOf(rate.getPrice()));
		repo.saveStockPrice(price);
		log.debug("Price is recorded: {}/{}", price.getSymbol(), price.getPrice());
	}
}
