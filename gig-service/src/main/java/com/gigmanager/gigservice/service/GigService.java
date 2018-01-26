package com.gigmanager.gigservice.service;

import com.gigmanager.gigservice.client.BandClient;
import com.gigmanager.gigservice.domain.Band;
import com.gigmanager.gigservice.domain.Gig;
import com.gigmanager.gigservice.repository.GigRepository;
import com.gigmanager.gigservice.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Service Implementation for managing Gig.
 */
@Service
public class GigService {

    private final Logger log = LoggerFactory.getLogger(GigService.class);

    private final GigRepository gigRepository;

    private final BandClient bandClient;

    @Autowired
    public GigService(GigRepository gigRepository, BandClient bandClient) {
        this.gigRepository = gigRepository;
        this.bandClient = bandClient;
    }

    /**
     * Save a gig.
     *
     * @param gig the entity to save
     * @return the persisted entity
     */
    public Gig save(Gig gig) {
        log.debug("Request to save Gig : {}", gig);
        Band band = bandClient.findBandByUserId(SecurityUtils.getCurrentUserLogin().get());
        gig.setBandsAttendingGig(band);
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

    public List<Gig> findGigsOfTheBand(String bandId) {
        return gigRepository.findAll()
            .stream()
            .filter(x -> x.getBandsAttendingGig().stream()
                .anyMatch(y -> y.getId().equals(bandId)))
            .collect(Collectors.toList());
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
}
