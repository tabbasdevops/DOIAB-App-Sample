/**
 * 
 */
package com.kpmg.demo.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Price repository (mongodb)
 *
 * @author aantipov
 */
@RepositoryRestResource(collectionResourceRel = "price", path = "price")
public interface PriceRepository extends MongoRepository<PriceEntity, String> {

	/**
	 * Finds price records by symbol and cutoff time
	 * @param symbol Share symbol
	 * @param date Cutoff time
	 * @return Collection of shares
	 */
	@Query("{'symbol': ?0, 'created': {'$gt': ?1}}")
	List<PriceEntity> findBySymbol(String symbol, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime cutoff);
}
