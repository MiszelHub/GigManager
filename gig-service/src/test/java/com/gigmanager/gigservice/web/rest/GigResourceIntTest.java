package com.gigmanager.gigservice.web.rest;

import com.gigmanager.gigservice.GigserviceApp;

import com.gigmanager.gigservice.config.SecurityBeanOverrideConfiguration;

import com.gigmanager.gigservice.domain.Gig;
import com.gigmanager.gigservice.repository.GigRepository;
import com.gigmanager.gigservice.service.GigService;
import com.gigmanager.gigservice.web.rest.errors.ExceptionTranslator;

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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.gigmanager.gigservice.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GigResource REST controller.
 *
 * @see GigResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GigserviceApp.class, SecurityBeanOverrideConfiguration.class})
public class GigResourceIntTest {

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_CANCELLED = false;
    private static final Boolean UPDATED_IS_CANCELLED = true;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_TICKET_PRICE = BigDecimal.valueOf(1);
    private static final BigDecimal UPDATED_TICKET_PRICE = BigDecimal.valueOf(2);

    private static final String DEFAULT_STAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STAGE_NAME = "BBBBBBBBBB";

    @Autowired
    private GigRepository gigRepository;

    @Autowired
    private GigService gigService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restGigMockMvc;

    private Gig gig;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GigResource gigResource = new GigResource(gigService);
        this.restGigMockMvc = MockMvcBuilders.standaloneSetup(gigResource)
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
    public static Gig createEntity() {
        Gig gig = new Gig()
            .startDate(DEFAULT_START_DATE)
            .name(DEFAULT_NAME)
            .isCancelled(DEFAULT_IS_CANCELLED)
            .description(DEFAULT_DESCRIPTION)
            .ticketPrice(DEFAULT_TICKET_PRICE)
            .stageName(DEFAULT_STAGE_NAME);
        return gig;
    }

    @Before
    public void initTest() {
        gigRepository.deleteAll();
        gig = createEntity();
    }

    @Test
    public void createGig() throws Exception {
        int databaseSizeBeforeCreate = gigRepository.findAll().size();

        // Create the Gig
        restGigMockMvc.perform(post("/api/gigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gig)))
            .andExpect(status().isCreated());

        // Validate the Gig in the database
        List<Gig> gigList = gigRepository.findAll();
        assertThat(gigList).hasSize(databaseSizeBeforeCreate + 1);
        Gig testGig = gigList.get(gigList.size() - 1);
        assertThat(testGig.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testGig.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGig.isIsCancelled()).isEqualTo(DEFAULT_IS_CANCELLED);
        assertThat(testGig.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGig.getTicketPrice()).isEqualTo(DEFAULT_TICKET_PRICE);
        assertThat(testGig.getStageName()).isEqualTo(DEFAULT_STAGE_NAME);
    }

    @Test
    public void createGigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gigRepository.findAll().size();

        // Create the Gig with an existing ID
        gig.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restGigMockMvc.perform(post("/api/gigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gig)))
            .andExpect(status().isBadRequest());

        // Validate the Gig in the database
        List<Gig> gigList = gigRepository.findAll();
        assertThat(gigList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = gigRepository.findAll().size();
        // set the field null
        gig.setStartDate(null);

        // Create the Gig, which fails.

        restGigMockMvc.perform(post("/api/gigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gig)))
            .andExpect(status().isBadRequest());

        List<Gig> gigList = gigRepository.findAll();
        assertThat(gigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTicketPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = gigRepository.findAll().size();
        // set the field null
        gig.setTicketPrice(null);

        // Create the Gig, which fails.

        restGigMockMvc.perform(post("/api/gigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gig)))
            .andExpect(status().isBadRequest());

        List<Gig> gigList = gigRepository.findAll();
        assertThat(gigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStageNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = gigRepository.findAll().size();
        // set the field null
        gig.setStageName(null);

        // Create the Gig, which fails.

        restGigMockMvc.perform(post("/api/gigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gig)))
            .andExpect(status().isBadRequest());

        List<Gig> gigList = gigRepository.findAll();
        assertThat(gigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllGigs() throws Exception {
        // Initialize the database
        gigRepository.save(gig);

        // Get all the gigList
        restGigMockMvc.perform(get("/api/gigs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gig.getId())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].isCancelled").value(hasItem(DEFAULT_IS_CANCELLED.booleanValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].ticketPrice").value(hasItem(DEFAULT_TICKET_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].stageName").value(hasItem(DEFAULT_STAGE_NAME.toString())));
    }

    @Test
    public void getGig() throws Exception {
        // Initialize the database
        gigRepository.save(gig);

        // Get the gig
        restGigMockMvc.perform(get("/api/gigs/{id}", gig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gig.getId()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.isCancelled").value(DEFAULT_IS_CANCELLED.booleanValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.ticketPrice").value(DEFAULT_TICKET_PRICE.doubleValue()))
            .andExpect(jsonPath("$.stageName").value(DEFAULT_STAGE_NAME.toString()));
    }

    @Test
    public void getNonExistingGig() throws Exception {
        // Get the gig
        restGigMockMvc.perform(get("/api/gigs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateGig() throws Exception {
        // Initialize the database
        gigService.save(gig);

        int databaseSizeBeforeUpdate = gigRepository.findAll().size();

        // Update the gig
        Gig updatedGig = gigRepository.findOne(gig.getId());
        updatedGig
            .startDate(UPDATED_START_DATE)
            .name(UPDATED_NAME)
            .isCancelled(UPDATED_IS_CANCELLED)
            .description(UPDATED_DESCRIPTION)
            .ticketPrice(UPDATED_TICKET_PRICE)
            .stageName(UPDATED_STAGE_NAME);

        restGigMockMvc.perform(put("/api/gigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGig)))
            .andExpect(status().isOk());

        // Validate the Gig in the database
        List<Gig> gigList = gigRepository.findAll();
        assertThat(gigList).hasSize(databaseSizeBeforeUpdate);
        Gig testGig = gigList.get(gigList.size() - 1);
        assertThat(testGig.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testGig.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGig.isIsCancelled()).isEqualTo(UPDATED_IS_CANCELLED);
        assertThat(testGig.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGig.getTicketPrice()).isEqualTo(UPDATED_TICKET_PRICE);
        assertThat(testGig.getStageName()).isEqualTo(UPDATED_STAGE_NAME);
    }

    @Test
    public void updateNonExistingGig() throws Exception {
        int databaseSizeBeforeUpdate = gigRepository.findAll().size();

        // Create the Gig

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGigMockMvc.perform(put("/api/gigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gig)))
            .andExpect(status().isCreated());

        // Validate the Gig in the database
        List<Gig> gigList = gigRepository.findAll();
        assertThat(gigList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteGig() throws Exception {
        // Initialize the database
        gigService.save(gig);

        int databaseSizeBeforeDelete = gigRepository.findAll().size();

        // Get the gig
        restGigMockMvc.perform(delete("/api/gigs/{id}", gig.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Gig> gigList = gigRepository.findAll();
        assertThat(gigList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Gig.class);
        Gig gig1 = new Gig();
        gig1.setId("id1");
        Gig gig2 = new Gig();
        gig2.setId(gig1.getId());
        assertThat(gig1).isEqualTo(gig2);
        gig2.setId("id2");
        assertThat(gig1).isNotEqualTo(gig2);
        gig1.setId(null);
        assertThat(gig1).isNotEqualTo(gig2);
    }
}
