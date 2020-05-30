package br.com.pirone.adliz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pirone.adliz.dto.PagedResultDTO;
import br.com.pirone.adliz.exception.ResourceNotFoundException;
import br.com.pirone.adliz.model.PaymentMethod;
import br.com.pirone.adliz.repository.PaymentMethodRepository;;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PaymentMethodController extends GenericController {
	
	@Autowired
	PaymentMethodRepository paymentMethodRepository;
	
	@GetMapping("/paymentMethod/all/")
	public ResponseEntity<?> getAllCustomers() {
		List<PaymentMethod> list = paymentMethodRepository.findAll();
		PagedResultDTO pagedResult = new PagedResultDTO(paymentMethodRepository.findAll(), Long.valueOf(list.size()));
		return new ResponseEntity<Object>(pagedResult, HttpStatus.OK);
	}

	@GetMapping("/paymentMethod/{id}")
	public PaymentMethod getServiceById(@PathVariable(value = "id") Long paymentMethodId) {
		return paymentMethodRepository.findById(paymentMethodId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment Method", "id", paymentMethodId));
	}
	
}
