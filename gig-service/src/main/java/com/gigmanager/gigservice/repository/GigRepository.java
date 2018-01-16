package com.gigmanager.gigservice.repository;

import com.gigmanager.gigservice.domain.Gig;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Gig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GigRepository extends MongoRepository<Gig, String> {

}
