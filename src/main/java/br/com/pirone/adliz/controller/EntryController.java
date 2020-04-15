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
import br.com.pirone.adliz.model.Entry;
import br.com.pirone.adliz.repository.EntryRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class EntryController extends GenericController {
	
	@Autowired
	EntryRepository entryRepository;
	
	@GetMapping("/entry/all/{page}")
	public ResponseEntity<PagedResultDTO> getAllEntrys(@PathVariable(value = "page")int page) {
		Page<Entry> result = entryRepository.findByOrderById(setPagination(page, defaultPerPage));
		PagedResultDTO pagedResult = new PagedResultDTO(result, result.getTotalPages(), result.getTotalElements());
		return new ResponseEntity<PagedResultDTO>(pagedResult, HttpStatus.OK);
	}
	
	@PostMapping("/entry")
	public ResponseEntity<?> createEntry(@Valid @RequestBody Entry entry, Errors errors) {
		entryRepository.save(entry);
		PagedResultDTO result = new PagedResultDTO("Registro incluído(a) com sucesso.");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	
	@GetMapping("/entry/{id}")
	public Entry getEntryById(@PathVariable(value = "id") Long entryId) {
		return entryRepository.findById(entryId)
				.orElseThrow(() -> new ResourceNotFoundException("Entry", "id", entryId));
	}
	
	@PutMapping("/entry/{id}")
	public String updateEntry(@PathVariable(value = "id") Long entryId,
			@Valid @RequestBody Entry entryDetails) {
		Entry entry = entryRepository.findById(entryId)
				.orElseThrow(() -> new ResourceNotFoundException("Entry", "id", entryId));
		
		entry.setComission(entryDetails.getComission());
		entry.setCustomer(entryDetails.getCustomer());
		entry.setEmployee(entryDetails.getEmployee());
		entry.setServices(entryDetails.getServices());
		entry.setPaymentMethod(entryDetails.getPaymentMethod());
		entry.setRecord(entryDetails.getRecord());
		
		entryRepository.save(entry);
		
		return "Registro atualizado com sucesso.";
		
	}
	
	@DeleteMapping("/entry/{id}")
	public String deleteEntryById(@PathVariable(value = "id") Long entryId) {
		try {
			entryRepository.deleteById(entryId);
			return "Registro excluído com sucesso.";
		} catch (Exception e) {
			System.out.println(e);
			return "Ocorreu um erro ao efetuar a exclusão. Tente novamente.";
			
		}
	}

}
