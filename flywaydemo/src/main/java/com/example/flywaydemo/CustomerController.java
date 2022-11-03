package com.example.flywaydemo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping(path = "/customers/", method = RequestMethod.GET)
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
		Optional<Customer> customerData = customerRepository.findById(id);

		if (customerData.isPresent()) {
			return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(path = "/customers/{customerId}/contacts/", method = RequestMethod.GET)
	public List<Contact> getCustomerContacts(@PathVariable("customerId") Long customerId) {
		Optional<Customer> customerData = customerRepository.findById(customerId);
		Customer cust = null;

		if (customerData.isPresent()) {
			cust = customerData.get();
			return cust.getContacts();
		} else {
			return null;
		}

	}

}
