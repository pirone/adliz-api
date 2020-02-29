package br.com.pirone.adliz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pirone.adliz.model.Work;

public interface WorkRepository extends JpaRepository<Work, Long>{

}
