/**
 * 
 */
package com.kpmg.demo.collector;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import com.kpmg.demo.collector.model.Price;
import com.kpmg.demo.collector.model.Stock;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Client for repo service
 * 
 * @author aantipov
 */
@Component
@Slf4j
public class RepoClient {
	@Value("${repo.service.url}") String serviceUrl;
	@Value("${repo.service.stock.by_symbol}") String searchStockQuery;
	@Value("${repo.service.stock.postquery}") String postStockQuery;
	@Value("${repo.service.price.postquery}") String postPriceQuery;
	WebClient client;
	
	/**
	 * Component initialization (called once all references are set)
	 */
	@PostConstruct
	protected void init() {
		if (StringUtils.isEmpty(serviceUrl)) {
			throw new IllegalArgumentException("Repo Service URL cannot be empty");
		}

		client = WebClient.create(serviceUrl);
	}
	
	/**
	 * Finds stock by symbol
	 * @param symbol Ticker symbol to search
	 * @return Stock object of null
	 */
	public Stock findStockSymbol(String symbol) {
		
		return client.get()
					 .uri(searchStockQuery, symbol).accept(MediaType.APPLICATION_JSON)
					 .retrieve()
					 .onStatus(status -> status == HttpStatus.NOT_FOUND, c -> Mono.empty())
					 .bodyToMono(Stock.class).block();
	}
	
	/**
	 * Persists stock entity
	 * @param entity Stock object to persist
	 */
	public void saveStockSymbol(Stock entity) {
		client.post()
			  .uri(postStockQuery).accept(MediaType.APPLICATION_JSON)
			  .bodyValue(entity).retrieve()
			  .onStatus(s -> true, c -> { log.trace("{}: stock {}", c.rawStatusCode(), entity.getSymbol()); return Mono.empty(); })
			  .toBodilessEntity().block();
	}
	
	/**
	 * Persists price entity
	 * @param entity Price object to persist
	 */
	public void saveStockPrice(Price entity) {
		client.post()
		  .uri(postPriceQuery).accept(MediaType.APPLICATION_JSON)
		  .bodyValue(entity).retrieve()
		  .onStatus(s -> true, c -> { log.trace("{}: price {}/{}", c.rawStatusCode(), entity.getSymbol(), entity.getPrice()); return Mono.empty(); })
		  .toBodilessEntity().block();
	}

}
