package com.gigmanager.gigservice.repository;

import com.gigmanager.gigservice.domain.Band;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Band entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BandRepository extends MongoRepository<Band, String> {

}
