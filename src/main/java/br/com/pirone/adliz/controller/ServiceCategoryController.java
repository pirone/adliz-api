package br.com.pirone.adliz.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pirone.adliz.exception.ResourceNotFoundException;
import br.com.pirone.adliz.model.ServiceCategory;
import br.com.pirone.adliz.repository.ServiceCategoryRepository;;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ServiceCategoryController {
	
	@Autowired
	ServiceCategoryRepository serviceCategoryRepository;
	
	@GetMapping("/serviceCategory")
	public List<ServiceCategory> getAllServicesCategories() {
		return serviceCategoryRepository.findAll();
	}
	
	@PostMapping("/serviceCategory")
	public String createServiceCategory(@Valid @RequestBody ServiceCategory serviceCategory, Errors errors) {
		serviceCategoryRepository.save(serviceCategory);
		return serviceCategory.getName() +" gravada com sucesso.";
	}

	
	@GetMapping("/serviceCategory/{id}")
	public ServiceCategory getServiceCategoryById(@PathVariable(value = "id") Long serviceCategoryId) {
		return serviceCategoryRepository.findById(serviceCategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Service category", "id", serviceCategoryId));
	}
	
	@PutMapping("/serviceCategory/{id}")
	public ServiceCategory updateServiceCategory(@PathVariable(value = "id") Long serviceCategoryId,
			@Valid @RequestBody ServiceCategory serviceCategoryDetails) {
		ServiceCategory serviceCategory = serviceCategoryRepository.findById(serviceCategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("ServiceCategory", "id", serviceCategoryId));
		
		serviceCategory.setName(serviceCategoryDetails.getName());
		serviceCategory.setDescription(serviceCategoryDetails.getDescription());
		
		return serviceCategoryRepository.save(serviceCategory);
		
	}

}
