package com.gigmanager.bandservice.service;

import com.gigmanager.bandservice.domain.Band;
import com.gigmanager.bandservice.repository.BandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing Band.
 */
@Service
public class BandService {

    private final Logger log = LoggerFactory.getLogger(BandService.class);

    private final BandRepository bandRepository;

    public BandService(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    /**
     * Save a band.
     *
     * @param band the entity to save
     * @return the persisted entity
     */
    public Band save(Band band) {
        log.debug("Request to save Band : {}", band);
        return bandRepository.save(band);
    }

    /**
     * Get all the bands.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<Band> findAll(Pageable pageable) {
        log.debug("Request to get all Bands");
        return bandRepository.findAll(pageable);
    }

    /**
     * Get one band by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Band findOne(String id) {
        log.debug("Request to get Band : {}", id);
        return bandRepository.findOne(id);
    }

    /**
     * Delete the band by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Band : {}", id);
        bandRepository.delete(id);
    }

    public Page<Band> findUserBands(Pageable pageable, String currentUserLogin) {
        return bandRepository.findAllByUserName(pageable, currentUserLogin);
    }
}
