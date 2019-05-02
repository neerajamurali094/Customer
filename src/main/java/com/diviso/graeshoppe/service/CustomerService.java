package com.diviso.graeshoppe.service;

import com.diviso.graeshoppe.service.dto.CustomerDTO;

import net.sf.jasperreports.engine.JRException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Customer.
 */
public interface CustomerService {

    /**
     * Save a customer.
     *
     * @param customerDTO the entity to save
     * @return the persisted entity
     */
    CustomerDTO save(CustomerDTO customerDTO);

    /**
     * Get all the customers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" customer.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CustomerDTO> findOne(Long id);

    /**
     * Delete the "id" customer.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the customer corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerDTO> search(String query, Pageable pageable);
    
    /**
     * send sms to the customer
     *
     * @param mobileNumber the mobileNumber of the customer
     */
    String sendSms(String mobileNumber);
    
    /**
     * send email to the customer
     *
     * @param email the email of the customer
     */
    public String sendEmail(String email);
/*
     * Get customerssReport.
     *			     
     * @return the byte[]
	 * @throws JRException 
     */    
    byte[] getPdfAllCustomers() throws JRException;

}
