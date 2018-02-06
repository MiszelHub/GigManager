package com.gigmanager.gigservice.service;

import com.gigmanager.gigservice.domain.Gig;
import com.gigmanager.gigservice.repository.GigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Service Implementation for managing Gig.
 */
@Service
public class GigService {

    private final Logger log = LoggerFactory.getLogger(GigService.class);

    private final GigRepository gigRepository;

    public GigService(GigRepository gigRepository) {
        this.gigRepository = gigRepository;
    }

    /**
     * Save a gig.
     *
     * @param gig the entity to save
     * @return the persisted entity
     */
    public Gig save(Gig gig) {
        log.debug("Request to save Gig : {}", gig);
        return gigRepository.save(gig);
    }

    /**
     * Get all the gigs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<Gig> findAll(Pageable pageable) {
        log.debug("Request to get all Gigs");
        return gigRepository.findAll(pageable);
    }

    /**
     * Get one gig by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Gig findOne(String id) {
        log.debug("Request to get Gig : {}", id);
        return gigRepository.findOne(id);
    }

    /**
     * Delete the gig by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Gig : {}", id);
        gigRepository.delete(id);
    }

    public Page<Gig> findAllUserGigs(Pageable pageable, String currentUserLogin) {
        return gigRepository.findAllByUserName(pageable, currentUserLogin);
    }
}
