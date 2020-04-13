package br.com.pirone.adliz.controller;

import java.text.ParseException;

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
import br.com.pirone.adliz.model.Employee;
import br.com.pirone.adliz.repository.EmployeeRepository;;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class EmployeeController extends GenericController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/employee/all/{page}")
	public ResponseEntity<PagedResultDTO> getAllEmployees(@PathVariable(value = "page")int page) {
		Page<Employee> result = employeeRepository.findByOrderById(setPagination(page, defaultPerPage));
		PagedResultDTO pagedResult = new PagedResultDTO(result, result.getTotalPages(), result.getTotalElements());
		return new ResponseEntity<PagedResultDTO>(pagedResult, HttpStatus.OK);
	}
	
	@PostMapping("/employee")
	public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee, Errors errors) {
		employeeRepository.save(employee);
		PagedResultDTO result = new PagedResultDTO(employee.getPerson().getName() +" incluído(a) com sucesso.");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	
	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) {
		return employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
	}
	
	@PutMapping("/employee/{id}")
	public String updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ParseException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
		
		employee.getPerson().setName(employeeDetails.getPerson().getName());
		employee.getPerson().setCpf(employeeDetails.getPerson().getCpf());
		employee.getPerson().setBirth_date(employeeDetails.getPerson().getBirth_date());
		employee.getPerson().setEmail(employeeDetails.getPerson().getEmail());
		employee.getPerson().setAdresses(employeeDetails.getPerson().getAdresses());
		employee.getPerson().setPhones(employeeDetails.getPerson().getPhones());
		employee.setComissionRate(employeeDetails.getComissionRate());
		
		employeeRepository.save(employee);
		
		return "Funcionário atualizado com sucesso.";
		
	}
	
	@DeleteMapping("/employee/{id}")
	public String deleteEmployeeById(@PathVariable(value = "id") Long employeeId) {
		try {
			employeeRepository.deleteById(employeeId);
			return "Funcionário excluído com sucesso.";
		} catch (Exception e) {
			System.out.println(e);
			return "Ocorreu um erro ao efetuar a exclusão. Tente novamente.";
			
		}
	}

}
