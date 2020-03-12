package br.com.pirone.adliz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pirone.adliz.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	Person findByCpf(String cpf);

}
