/**
 * 
 */
package com.kpmg.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Entity repository (mongo)
 * 
 * @author aantipov
 */
@RepositoryRestResource(collectionResourceRel = "entity", path = "entity")
public interface EntityRepository  extends MongoRepository<StockEntity, String>  {

	/**
	 * Finds unique share info by symbol
	 * @param s Symbol to search
	 * @return Entity object
	 */
	StockEntity findBySymbol(@Param("symbol") String s);
}
