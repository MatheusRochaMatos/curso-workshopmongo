package com.nelioalves.workshopmongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nelioalves.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	/* query methods 
	https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/ */

	List<Post> findByTitleContainingIgnoreCase(String text);
	
	
	/* @query
	 * https://www.mongodb.com/pt-br/docs/manual/reference/operator/query/regex/
	 */
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> findByTitle (String text);
}
