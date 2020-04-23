package com.kpmg.demo.collector;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.kpmg.demo.collector.model.Stock;

@SpringBootTest
class CollectorApplicationTests {

	@Autowired RepoClient client;
	
	@Test
	void contextLoads() {
	}

	@Test
	void searchStock() {
		var r = client.findStockSymbol("BTwC");
		Assert.notNull(r, "");
	}

	@Test
	void saveStock() {
		var stock = new Stock();
		stock.setName("Russian ruble");
		stock.setSymbol("RUB");
		client.saveStockSymbol(stock);
	}
}
