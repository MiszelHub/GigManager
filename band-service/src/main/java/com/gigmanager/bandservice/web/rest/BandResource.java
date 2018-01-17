package com.gigmanager.bandservice.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gigmanager.bandservice.domain.Band;
import com.gigmanager.bandservice.service.BandService;
import com.gigmanager.bandservice.web.rest.errors.BadRequestAlertException;
import com.gigmanager.bandservice.web.rest.util.HeaderUtil;
import com.gigmanager.bandservice.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Band.
 */
@RestController
@RequestMapping("/api")
public class BandResource {

    private final Logger log = LoggerFactory.getLogger(BandResource.class);

    private static final String ENTITY_NAME = "band";

    private final BandService bandService;

    public BandResource(BandService bandService) {
        this.bandService = bandService;
    }

    /**
     * POST  /bands : Create a new band.
     *
     * @param band the band to create
     * @return the ResponseEntity with status 201 (Created) and with body the new band, or with status 400 (Bad Request) if the band has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bands")
    @Timed
    public ResponseEntity<Band> createBand(@Valid @RequestBody Band band) throws URISyntaxException {
        log.debug("REST request to save Band : {}", band);
        if (band.getId() != null) {
            throw new BadRequestAlertException("A new band cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Band result = bandService.save(band);
        return ResponseEntity.created(new URI("/api/bands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bands : Updates an existing band.
     *
     * @param band the band to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated band,
     * or with status 400 (Bad Request) if the band is not valid,
     * or with status 500 (Internal Server Error) if the band couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bands")
    @Timed
    public ResponseEntity<Band> updateBand(@Valid @RequestBody Band band) throws URISyntaxException {
        log.debug("REST request to update Band : {}", band);
        if (band.getId() == null) {
            return createBand(band);
        }
        Band result = bandService.save(band);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, band.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bands : get all the bands.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bands in body
     */
    @GetMapping("/bands")
    @Timed
    public ResponseEntity<List<Band>> getAllBands(Pageable pageable) {
        log.debug("REST request to get a page of Bands");
        Page<Band> page = bandService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/bands");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /bands/:id : get the "id" band.
     *
     * @param id the id of the band to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the band, or with status 404 (Not Found)
     */
    @GetMapping("/bands/{id}")
    @Timed
    public ResponseEntity<Band> getBand(@PathVariable String id) {
        log.debug("REST request to get Band : {}", id);
        Band band = bandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(band));
    }

    /**
     * DELETE  /bands/:id : delete the "id" band.
     *
     * @param id the id of the band to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bands/{id}")
    @Timed
    public ResponseEntity<Void> deleteBand(@PathVariable String id) {
        log.debug("REST request to delete Band : {}", id);
        bandService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
