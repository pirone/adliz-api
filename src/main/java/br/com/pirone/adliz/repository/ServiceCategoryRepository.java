package br.com.pirone.adliz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pirone.adliz.model.ServiceCategory;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {

}