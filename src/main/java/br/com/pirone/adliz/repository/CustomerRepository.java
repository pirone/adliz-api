package br.com.pirone.adliz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirone.adliz.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
