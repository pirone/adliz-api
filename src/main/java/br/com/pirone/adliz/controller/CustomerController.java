package br.com.pirone.adliz.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pirone.adliz.dto.CustomerDTO;
import br.com.pirone.adliz.dto.PagedResultDTO;
import br.com.pirone.adliz.exception.ResourceNotFoundException;
import br.com.pirone.adliz.model.Customer;
import br.com.pirone.adliz.repository.CustomerRepository;;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class CustomerController extends GenericController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/customer/all/{page}")
	public ResponseEntity<PagedResultDTO> getAllCustomers(@PathVariable(value = "page")int page) {
		Page<Customer> result = customerRepository.findByOrderById(setPagination(page, defaultPerPage));
		PagedResultDTO pagedResult = new PagedResultDTO(result, result.getTotalPages(), result.getTotalElements());
		return new ResponseEntity<PagedResultDTO>(pagedResult, HttpStatus.OK);
	}
	
	@GetMapping("/customer/all/")
	public ResponseEntity<?> getAllCustomers() {
		List<CustomerDTO> resultado = new ArrayList<>();
		List<Customer> list = customerRepository.findAll();
		list.stream().forEach(customer -> resultado.add(new CustomerDTO(customer)));
		PagedResultDTO pagedResult = new PagedResultDTO(resultado, Long.valueOf(list.size()));
		return new ResponseEntity<Object>(pagedResult, HttpStatus.OK);
	}
	
	@PostMapping("/customer")
	public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer, Errors errors) {
		customerRepository.save(customer);
		PagedResultDTO result = new PagedResultDTO(customer.getPerson().getName() +" incluído(a) com sucesso.");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable(value = "id") Long customerId) {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
	}
	
	@PutMapping("/customer/{id}")
	public String updateCustomer(@PathVariable(value = "id") Long customerId,
			@Valid @RequestBody Customer customerDetails) throws ParseException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
		
		customer.getPerson().setName(customerDetails.getPerson().getName());
		customer.getPerson().setCpf(customerDetails.getPerson().getCpf());
		customer.getPerson().setBirth_date(customerDetails.getPerson().getBirth_date());
		customer.getPerson().setEmail(customerDetails.getPerson().getEmail());
		customer.getPerson().setAdresses(customerDetails.getPerson().getAdresses());
		customer.getPerson().setPhones(customerDetails.getPerson().getPhones());
		
		customerRepository.save(customer);
		
		return "Cliente atualizado com sucesso.";
		
	}
	
	@DeleteMapping("/customer/{id}")
	public String deleteCustomerById(@PathVariable(value = "id") Long customerId) {
		try {
			customerRepository.deleteById(customerId);
			return "Cliente excluído com sucesso.";
		} catch (Exception e) {
			System.out.println(e);
			return "Ocorreu um erro ao efetuar a exclusão. Tente novamente.";
			
		}
	}

}
