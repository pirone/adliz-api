package br.com.pirone.adliz.controller;

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

import br.com.pirone.adliz.dto.PagedResultDTO;
import br.com.pirone.adliz.exception.ResourceNotFoundException;
import br.com.pirone.adliz.model.ServiceCategory;
import br.com.pirone.adliz.repository.ServiceCategoryRepository;;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ServiceCategoryController extends GenericController {
	
	@Autowired
	ServiceCategoryRepository serviceCategoryRepository;
	
	@GetMapping("/serviceCategory/all/{page}")
	public ResponseEntity<PagedResultDTO> getAllServicesCategories(@PathVariable(value = "page")int page) {
		Page<ServiceCategory> result = serviceCategoryRepository.findByOrderById(setPagination(page, defaultPerPage));
		PagedResultDTO pagedResult = new PagedResultDTO(result, result.getTotalPages(), result.getTotalElements());
		return new ResponseEntity<PagedResultDTO>(pagedResult, HttpStatus.OK);
	}
	
	@PostMapping("/serviceCategory")
	public String createServiceCategory(@Valid @RequestBody ServiceCategory serviceCategory, Errors errors) {
		serviceCategoryRepository.save(serviceCategory);
		return serviceCategory.getName() +" incluído(a) com sucesso.";
	}

	
	@GetMapping("/serviceCategory/{id}")
	public ServiceCategory getServiceCategoryById(@PathVariable(value = "id") Long serviceCategoryId) {
		return serviceCategoryRepository.findById(serviceCategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Service category", "id", serviceCategoryId));
	}
	
	@PutMapping("/serviceCategory/{id}")
	public String updateServiceCategory(@PathVariable(value = "id") Long serviceCategoryId,
			@Valid @RequestBody ServiceCategory serviceCategoryDetails) {
		ServiceCategory serviceCategory = serviceCategoryRepository.findById(serviceCategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("ServiceCategory", "id", serviceCategoryId));
		
		serviceCategory.setName(serviceCategoryDetails.getName());
		serviceCategory.setDescription(serviceCategoryDetails.getDescription());
		
		serviceCategoryRepository.save(serviceCategory);
		
		return "Categoria de serviço atualizada com sucesso";
		
	}
	
	@DeleteMapping("/serviceCategory/{id}")
	public String deleteServiceCategoryById(@PathVariable(value = "id") Long serviceCategoryId) {
		try {
			serviceCategoryRepository.deleteById(serviceCategoryId);
			return "Categoria de serviço excluída com sucesso";
		} catch (Exception e) {
			System.out.println(e);
			return "Ocorreu um erro ao efetuar a exclusão. Tente novamente.";
			
		}
	}

}
