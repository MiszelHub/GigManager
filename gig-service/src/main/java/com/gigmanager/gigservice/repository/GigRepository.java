package com.gigmanager.gigservice.repository;

import com.gigmanager.gigservice.domain.Gig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.servlet.tags.Param;

/**
 * Spring Data MongoDB repository for the Gig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GigRepository extends MongoRepository<Gig, String> {

    Page<Gig> findAllByUserName(Pageable pageable, String userName);
}
