package br.com.pirone.adliz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import br.com.pirone.adliz.model.Service;
import br.com.pirone.adliz.repository.ServiceRepository;;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ServiceController extends GenericController {
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@GetMapping("/service/all/{page}")
	public ResponseEntity<PagedResultDTO> getAllServices(@PathVariable(value = "page")int page) {
		Page<Service> result = serviceRepository.findByOrderById(setPagination(page, defaultPerPage));
		PagedResultDTO pagedResult = new PagedResultDTO(result, result.getTotalPages(), result.getTotalElements());
		return new ResponseEntity<PagedResultDTO>(pagedResult, HttpStatus.OK);
	}
	
	@PostMapping("/service")
	public String createService(@Valid @RequestBody Service service, Errors errors) {
		serviceRepository.save(service);
		return service.getName() +" incluído(a) com sucesso.";
	}

	
	@GetMapping("/service/{id}")
	public Service getServiceById(@PathVariable(value = "id") Long serviceId) {
		return serviceRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("Service", "id", serviceId));
	}
	
	@PutMapping("/service/{id}")
	public String updateService(@PathVariable(value = "id") Long serviceId,
			@Valid @RequestBody Service serviceDetails) {
		Service service = serviceRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("Service", "id", serviceId));
		
		service.setName(serviceDetails.getName());
		service.setDescription(serviceDetails.getDescription());
		service.setCategory(serviceDetails.getCategory());
		service.setPrice(serviceDetails.getPrice());
		
		serviceRepository.save(service);
		
		return "Serviço atualizado com sucesso.";
		
	}
	
	@DeleteMapping("/service/{id}")
	public String deleteServiceById(@PathVariable(value = "id") Long serviceId) {
		try {
			serviceRepository.deleteById(serviceId);
			return "Serviço excluído com sucesso.";
		} catch (Exception e) {
			System.out.println(e);
			return "Ocorreu um erro ao efetuar a exclusão. Tente novamente.";
			
		}
	}

}
