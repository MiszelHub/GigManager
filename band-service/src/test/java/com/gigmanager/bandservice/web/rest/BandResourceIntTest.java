package com.gigmanager.bandservice.web.rest;

import com.gigmanager.bandservice.BandserviceApp;

import com.gigmanager.bandservice.config.SecurityBeanOverrideConfiguration;

import com.gigmanager.bandservice.domain.Band;
import com.gigmanager.bandservice.repository.BandRepository;
import com.gigmanager.bandservice.service.BandService;
import com.gigmanager.bandservice.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.gigmanager.bandservice.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.gigmanager.bandservice.domain.enumeration.MusicGenre;
/**
 * Test class for the BandResource REST controller.
 *
 * @see BandResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BandserviceApp.class, SecurityBeanOverrideConfiguration.class})
public class BandResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGIN = "AAAAAAAAAA";
    private static final String UPDATED_ORIGIN = "BBBBBBBBBB";

    private static final MusicGenre DEFAULT_GENRE = MusicGenre.Rock;
    private static final MusicGenre UPDATED_GENRE = MusicGenre.HardRock;

    private static final LocalDate DEFAULT_DATE_OF_FORMATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_FORMATION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_BIO = "AAAAAAAAAA";
    private static final String UPDATED_BIO = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_ID = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_ID = "BBBBBBBBBB";

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private BandService bandService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restBandMockMvc;

    private Band band;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BandResource bandResource = new BandResource(bandService);
        this.restBandMockMvc = MockMvcBuilders.standaloneSetup(bandResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Band createEntity() {
        Band band = new Band()
            .name(DEFAULT_NAME)
            .origin(DEFAULT_ORIGIN)
            .genre(DEFAULT_GENRE)
            .dateOfFormation(DEFAULT_DATE_OF_FORMATION)
            .bio(DEFAULT_BIO)
            .accountId(DEFAULT_ACCOUNT_ID);
        return band;
    }

    @Before
    public void initTest() {
        bandRepository.deleteAll();
        band = createEntity();
    }

    @Test
    public void createBand() throws Exception {
        int databaseSizeBeforeCreate = bandRepository.findAll().size();

        // Create the Band
        restBandMockMvc.perform(post("/api/bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(band)))
            .andExpect(status().isCreated());

        // Validate the Band in the database
        List<Band> bandList = bandRepository.findAll();
        assertThat(bandList).hasSize(databaseSizeBeforeCreate + 1);
        Band testBand = bandList.get(bandList.size() - 1);
        assertThat(testBand.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBand.getOrigin()).isEqualTo(DEFAULT_ORIGIN);
        assertThat(testBand.getGenre()).isEqualTo(DEFAULT_GENRE);
        assertThat(testBand.getDateOfFormation()).isEqualTo(DEFAULT_DATE_OF_FORMATION);
        assertThat(testBand.getBio()).isEqualTo(DEFAULT_BIO);
        assertThat(testBand.getAccountId()).isEqualTo(DEFAULT_ACCOUNT_ID);
    }

    @Test
    public void createBandWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bandRepository.findAll().size();

        // Create the Band with an existing ID
        band.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restBandMockMvc.perform(post("/api/bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(band)))
            .andExpect(status().isBadRequest());

        // Validate the Band in the database
        List<Band> bandList = bandRepository.findAll();
        assertThat(bandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = bandRepository.findAll().size();
        // set the field null
        band.setName(null);

        // Create the Band, which fails.

        restBandMockMvc.perform(post("/api/bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(band)))
            .andExpect(status().isBadRequest());

        List<Band> bandList = bandRepository.findAll();
        assertThat(bandList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkGenreIsRequired() throws Exception {
        int databaseSizeBeforeTest = bandRepository.findAll().size();
        // set the field null
        band.setGenre(null);

        // Create the Band, which fails.

        restBandMockMvc.perform(post("/api/bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(band)))
            .andExpect(status().isBadRequest());

        List<Band> bandList = bandRepository.findAll();
        assertThat(bandList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDateOfFormationIsRequired() throws Exception {
        int databaseSizeBeforeTest = bandRepository.findAll().size();
        // set the field null
        band.setDateOfFormation(null);

        // Create the Band, which fails.

        restBandMockMvc.perform(post("/api/bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(band)))
            .andExpect(status().isBadRequest());

        List<Band> bandList = bandRepository.findAll();
        assertThat(bandList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAccountIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = bandRepository.findAll().size();
        // set the field null
        band.setAccountId(null);

        // Create the Band, which fails.

        restBandMockMvc.perform(post("/api/bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(band)))
            .andExpect(status().isBadRequest());

        List<Band> bandList = bandRepository.findAll();
        assertThat(bandList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllBands() throws Exception {
        // Initialize the database
        bandRepository.save(band);

        // Get all the bandList
        restBandMockMvc.perform(get("/api/bands?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(band.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].origin").value(hasItem(DEFAULT_ORIGIN.toString())))
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE.toString())))
            .andExpect(jsonPath("$.[*].dateOfFormation").value(hasItem(DEFAULT_DATE_OF_FORMATION.toString())))
            .andExpect(jsonPath("$.[*].bio").value(hasItem(DEFAULT_BIO.toString())))
            .andExpect(jsonPath("$.[*].accountId").value(hasItem(DEFAULT_ACCOUNT_ID.toString())));
    }

    @Test
    public void getBand() throws Exception {
        // Initialize the database
        bandRepository.save(band);

        // Get the band
        restBandMockMvc.perform(get("/api/bands/{id}", band.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(band.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.origin").value(DEFAULT_ORIGIN.toString()))
            .andExpect(jsonPath("$.genre").value(DEFAULT_GENRE.toString()))
            .andExpect(jsonPath("$.dateOfFormation").value(DEFAULT_DATE_OF_FORMATION.toString()))
            .andExpect(jsonPath("$.bio").value(DEFAULT_BIO.toString()))
            .andExpect(jsonPath("$.accountId").value(DEFAULT_ACCOUNT_ID.toString()));
    }

    @Test
    public void getNonExistingBand() throws Exception {
        // Get the band
        restBandMockMvc.perform(get("/api/bands/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBand() throws Exception {
        // Initialize the database
        bandService.save(band);

        int databaseSizeBeforeUpdate = bandRepository.findAll().size();

        // Update the band
        Band updatedBand = bandRepository.findOne(band.getId());
        updatedBand
            .name(UPDATED_NAME)
            .origin(UPDATED_ORIGIN)
            .genre(UPDATED_GENRE)
            .dateOfFormation(UPDATED_DATE_OF_FORMATION)
            .bio(UPDATED_BIO)
            .accountId(UPDATED_ACCOUNT_ID);

        restBandMockMvc.perform(put("/api/bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBand)))
            .andExpect(status().isOk());

        // Validate the Band in the database
        List<Band> bandList = bandRepository.findAll();
        assertThat(bandList).hasSize(databaseSizeBeforeUpdate);
        Band testBand = bandList.get(bandList.size() - 1);
        assertThat(testBand.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBand.getOrigin()).isEqualTo(UPDATED_ORIGIN);
        assertThat(testBand.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testBand.getDateOfFormation()).isEqualTo(UPDATED_DATE_OF_FORMATION);
        assertThat(testBand.getBio()).isEqualTo(UPDATED_BIO);
        assertThat(testBand.getAccountId()).isEqualTo(UPDATED_ACCOUNT_ID);
    }

    @Test
    public void updateNonExistingBand() throws Exception {
        int databaseSizeBeforeUpdate = bandRepository.findAll().size();

        // Create the Band

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBandMockMvc.perform(put("/api/bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(band)))
            .andExpect(status().isCreated());

        // Validate the Band in the database
        List<Band> bandList = bandRepository.findAll();
        assertThat(bandList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteBand() throws Exception {
        // Initialize the database
        bandService.save(band);

        int databaseSizeBeforeDelete = bandRepository.findAll().size();

        // Get the band
        restBandMockMvc.perform(delete("/api/bands/{id}", band.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Band> bandList = bandRepository.findAll();
        assertThat(bandList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Band.class);
        Band band1 = new Band();
        band1.setId("id1");
        Band band2 = new Band();
        band2.setId(band1.getId());
        assertThat(band1).isEqualTo(band2);
        band2.setId("id2");
        assertThat(band1).isNotEqualTo(band2);
        band1.setId(null);
        assertThat(band1).isNotEqualTo(band2);
    }
}
