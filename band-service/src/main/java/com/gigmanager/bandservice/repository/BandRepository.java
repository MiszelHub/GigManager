package com.gigmanager.bandservice.repository;

import com.gigmanager.bandservice.domain.Band;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Band entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BandRepository extends MongoRepository<Band, String> {

}
