package br.com.pirone.adliz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pirone.adliz.model.Person;
import br.com.pirone.adliz.repository.PersonRepository;

@RestController
@RequestMapping("/api")
public class PersonController {
	
	@Autowired
	PersonRepository personRepository;
	
	@GetMapping("/person")
	public Person getPersonByCpf(@Valid @RequestBody Person person) {
		return personRepository.findByCpf(person.getCpf());
		
	}

}
