package br.com.pirone.adliz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirone.adliz.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
	
	Page<Service> findByOrderById(Pageable pagination);

}
