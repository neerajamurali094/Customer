package com.diviso.graeshoppe.service.impl;

import com.diviso.graeshoppe.service.CustomerService;
import com.diviso.graeshoppe.domain.Contact;
import com.diviso.graeshoppe.domain.Customer;
import com.diviso.graeshoppe.repository.ContactRepository;
import com.diviso.graeshoppe.repository.CustomerRepository;
import com.diviso.graeshoppe.repository.search.CustomerSearchRepository;
import com.diviso.graeshoppe.service.dto.ContactDTO;
import com.diviso.graeshoppe.service.dto.CustomerDTO;
import com.diviso.graeshoppe.service.mapper.ContactMapper;
import com.diviso.graeshoppe.service.mapper.CustomerMapper;
import com.twilio.Twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Customer.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final CustomerSearchRepository customerSearchRepository;
    
    private final ContactRepository contactRepository;

    private final ContactMapper contactMapper;
    
    private JavaMailSender sender;
    
    private final static String ACCOUNT_SID = "ACe660cc3e88299624df35f2a6d066c7cc";
	private final static String AUTH_ID = "32f1e90519be08b568947c78211ff195";
	private final static String TWILIO_NUMBER="+18166232986";
	   static {
	      Twilio.init(ACCOUNT_SID, AUTH_ID);
	   }
	 
	public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper,
			CustomerSearchRepository customerSearchRepository, ContactRepository contactRepository,
			ContactMapper contactMapper, JavaMailSender sender) {
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
		this.customerSearchRepository = customerSearchRepository;
		this.contactRepository = contactRepository;
		this.contactMapper = contactMapper;
		this.sender = sender;
	}

	/**
     * Save a customer.
     *
     * @param customerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        log.debug("Request to save Customer : {}", customerDTO);
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.save(customer);
        CustomerDTO result = customerMapper.toDto(customer);
        
        //logic to send sms
        if(result.getContactId()!=null){
        
        Optional<Contact> contact=contactRepository.findById(result.getContactId());
        
        if(contact.get().getMobileNumber()!=null){
        	String mobileNumber=contact.get().getMobileNumber();
        	String message=sendSms(mobileNumber);
        	log.info("***********message{}"+message);
        }
        }
       
        customerSearchRepository.save(customer);
        return result;
    }

    /**
     * Get all the customers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Customers");
        return customerRepository.findAll(pageable)
            .map(customerMapper::toDto);
    }


    /**
     * Get one customer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> findOne(Long id) {
        log.debug("Request to get Customer : {}", id);
        return customerRepository.findById(id)
            .map(customerMapper::toDto);
    }

    /**
     * Delete the customer by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Customer : {}", id);
        customerRepository.deleteById(id);
        customerSearchRepository.deleteById(id);
    }

    /**
     * Search for the customer corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Customers for query {}", query);
        return customerSearchRepository.search(queryStringQuery(query), pageable)
            .map(customerMapper::toDto);
    }
    
	/**
	 * Send sms notification to registered customer 
	 *
	 * @param mobileNumber
	 *            the mobileNumber to send sms
	 * @return the sms sending status
	 */
	@Override
	public String sendSms(String mobileNumber) {
		
		try {
			 Message.creator(new PhoneNumber(mobileNumber), new PhoneNumber("+18166232986"),
					 "Welcome! Thank you for registering with us").create();

		} catch (Exception e) {
			e.printStackTrace();
			return "Error while sending sms";
		}
		
		return "SMS Sent Success!";
	}
	
	/**
	 * Send email notification to registered customer 
	 *
	 * @param email
	 *            the mobileNumber to send email
	 * @return the email sending status
	 */
	@Override
	public String sendEmail(String email) {
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		try {
			helper.setTo(email);
			helper.setText("Thank you for registering with us!!!");
			helper.setSubject("Mail From Graeshoppe");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		
		sender.send(message);
		return "Mail Sent Success!";

	}
}
