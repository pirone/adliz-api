package br.com.pirone.adliz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pirone.adliz.model.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {
	
	Page<Entry> findByOrderById(Pageable pagination);

}
