package com.gigmanager.gigservice.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gigmanager.gigservice.domain.Gig;
import com.gigmanager.gigservice.security.AuthoritiesConstants;
import com.gigmanager.gigservice.security.SecurityUtils;
import com.gigmanager.gigservice.service.GigService;
import com.gigmanager.gigservice.web.rest.errors.BadRequestAlertException;
import com.gigmanager.gigservice.web.rest.util.HeaderUtil;
import com.gigmanager.gigservice.web.rest.util.PaginationUtil;
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
 * REST controller for managing Gig.
 */
@RestController
@RequestMapping("/api")
public class GigResource {

    private static final String ENTITY_NAME = "gig";
    private final Logger log = LoggerFactory.getLogger(GigResource.class);
    private final GigService gigService;

    public GigResource(GigService gigService) {
        this.gigService = gigService;
    }

    /**
     * POST  /gigs : Create a new gig.
     *
     * @param gig the gig to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gig, or with status 400 (Bad Request) if the gig has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/gigs")
    @Timed
    public ResponseEntity<Gig> createGig(@Valid @RequestBody Gig gig) throws URISyntaxException {
        log.debug("REST request to save Gig : {}", gig);
        if (gig.getId() != null) {
            throw new BadRequestAlertException("A new gig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Optional<String> currentUserLogin = SecurityUtils.getCurrentUserLogin();

        currentUserLogin.ifPresent(gig::setUserName);

        Gig result = gigService.save(gig);
        return ResponseEntity.created(new URI("/api/gigs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /gigs : Updates an existing gig.
     *
     * @param gig the gig to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gig,
     * or with status 400 (Bad Request) if the gig is not valid,
     * or with status 500 (Internal Server Error) if the gig couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/gigs")
    @Timed
    public ResponseEntity<Gig> updateGig(@Valid @RequestBody Gig gig) throws URISyntaxException {
        log.debug("REST request to update Gig : {}", gig);
        if (gig.getId() == null) {
            return createGig(gig);
        }
        Gig result = gigService.save(gig);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gig.getId().toString()))
            .body(result);
    }

    /**
     * GET  /gigs : get all the gigs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of gigs in body
     */
    @GetMapping("/gigs")
    @Timed
    public ResponseEntity<List<Gig>> getAllGigs(Pageable pageable) {
        log.debug("REST request to get a page of Gigs");

        Page<Gig> page = null;
        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
            page = gigService.findAll(pageable);

        } else if(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER)){
            page = gigService.findAllUserGigs(pageable,SecurityUtils.getCurrentUserLogin().orElseGet(null));
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/gigs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /gigs/:id : get the "id" gig.
     *
     * @param id the id of the gig to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gig, or with status 404 (Not Found)
     */
    @GetMapping("/gigs/{id}")
    @Timed
    public ResponseEntity<Gig> getGig(@PathVariable String id) {
        log.debug("REST request to get Gig : {}", id);
        Gig gig = gigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(gig));
    }

    /**
     * DELETE  /gigs/:id : delete the "id" gig.
     *
     * @param id the id of the gig to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/gigs/{id}")
    @Timed
    public ResponseEntity<Void> deleteGig(@PathVariable String id) {
        log.debug("REST request to delete Gig : {}", id);
        gigService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
