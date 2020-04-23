/**
 * 
 */
package com.kpmg.demo.collector.generator;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of ValueGenerator for Aphadvantage Forex service 
 * @author aantipov
 */
@Lazy @Component
@Slf4j
public class AlphadvantageGenerator implements ValueGenerator {
	/**
	 * Forex rate response for ALPHADVANTAGE service
	 */
	@Data
	public static class ForexResponse {
		@JsonProperty("Realtime Currency Exchange Rate")
		ExchangeRate rate;
	}

	/**
	 * Exchange rate for ALPHANTAGE service
	 */
	@Data
	public static class ExchangeRate {
		@JsonProperty("1. From_Currency Code")
		String fromCurrencyCode;

		@JsonProperty("2. From_Currency Name")
		String fromCurrencyName;

		@JsonProperty("3. To_Currency Code")
		String toCurrencyCode;

		@JsonProperty("4. To_Currency Name")
		String toCurrencyName;

		@JsonProperty("5. Exchange Rate")
		float exchangeRate;

		@JsonProperty("6. Last Refreshed") 
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu-MM-dd HH:mm:ss") 
		LocalDateTime refreshed;

		@JsonProperty("7. Time Zone")
		String timeZone;

		@JsonProperty("Bid Price")
		String bid;

		@JsonProperty("Ask Price")
		String ask;
	}
		
		
	/**
	 * Class variables	
	 */
	@Value("${alphadvange.api.url}") String serviceUrl;
	@Value("${alphadvange.api.query}") String query;
	@Value("${alphadvange.api.key}") String apiKey;
	WebClient client;

	/**
	 * Post construction initialization 
	 */
	@PostConstruct
	public void init() {
		if (StringUtils.isEmpty(apiKey)) {
			throw new IllegalArgumentException("API key cannot be empty");
		}
		
		client = WebClient.create(serviceUrl);
	}

	/**
	 * Collects price for single stock price.
	 * @param symbol Stock symbol
	 */
	@Override
	public ValuePoint getCurrentValue(String symbol) {
		try {
			var response = client.get()
				  .uri(query, symbol, apiKey).accept(MediaType.APPLICATION_JSON)
				  .retrieve()
				  .bodyToMono(ForexResponse.class).block();
			var r = response.getRate();
			
			if (r != null) {
				return new ValuePoint(r.getFromCurrencyCode(), r.getFromCurrencyName(), 
						              r.getExchangeRate(), r.getRefreshed());
			}
		} catch (Exception x) {
			log.error(x.getMessage());
		}
		
		return null;
	}
	
}
