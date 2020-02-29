package br.com.pirone.adliz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirone.adliz.model.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

}
