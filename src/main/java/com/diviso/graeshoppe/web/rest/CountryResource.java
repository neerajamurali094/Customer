package com.diviso.graeshoppe.web.rest;
import com.diviso.graeshoppe.service.CountryService;
import com.diviso.graeshoppe.web.rest.errors.BadRequestAlertException;
import com.diviso.graeshoppe.web.rest.util.HeaderUtil;
import com.diviso.graeshoppe.web.rest.util.PaginationUtil;
import com.diviso.graeshoppe.service.dto.CountryDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Country.
 */
@RestController
@RequestMapping("/api")
public class CountryResource {

    private final Logger log = LoggerFactory.getLogger(CountryResource.class);

    private static final String ENTITY_NAME = "customerCountry";

    private final CountryService countryService;

    public CountryResource(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * POST  /countries : Create a new country.
     *
     * @param countryDTO the countryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new countryDTO, or with status 400 (Bad Request) if the country has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/countries")
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO) throws URISyntaxException {
        log.debug("REST request to save Country : {}", countryDTO);
        if (countryDTO.getId() != null) {
            throw new BadRequestAlertException("A new country cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CountryDTO result = countryService.save(countryDTO);
        return ResponseEntity.created(new URI("/api/countries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /countries : Updates an existing country.
     *
     * @param countryDTO the countryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated countryDTO,
     * or with status 400 (Bad Request) if the countryDTO is not valid,
     * or with status 500 (Internal Server Error) if the countryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/countries")
    public ResponseEntity<CountryDTO> updateCountry(@RequestBody CountryDTO countryDTO) throws URISyntaxException {
        log.debug("REST request to update Country : {}", countryDTO);
        if (countryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CountryDTO result = countryService.save(countryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, countryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /countries : get all the countries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of countries in body
     */
    @GetMapping("/countries")
    public ResponseEntity<List<CountryDTO>> getAllCountries(Pageable pageable) {
        log.debug("REST request to get a page of Countries");
        Page<CountryDTO> page = countryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/countries");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /countries/:id : get the "id" country.
     *
     * @param id the id of the countryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the countryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/countries/{id}")
    public ResponseEntity<CountryDTO> getCountry(@PathVariable Long id) {
        log.debug("REST request to get Country : {}", id);
        Optional<CountryDTO> countryDTO = countryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(countryDTO);
    }

    /**
     * DELETE  /countries/:id : delete the "id" country.
     *
     * @param id the id of the countryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/countries/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        log.debug("REST request to delete Country : {}", id);
        countryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/countries?query=:query : search for the country corresponding
     * to the query.
     *
     * @param query the query of the country search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/countries")
    public ResponseEntity<List<CountryDTO>> searchCountries(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Countries for query {}", query);
        Page<CountryDTO> page = countryService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/countries");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
