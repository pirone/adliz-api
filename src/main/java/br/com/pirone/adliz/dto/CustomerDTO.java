package br.com.pirone.adliz.dto;

import br.com.pirone.adliz.model.Customer;

public class CustomerDTO {
	
	public CustomerDTO(Customer customer) {
		this.id = customer.getId();
		this.Name = customer.getPerson().getName();
	}
	
	private Long id;
	
	private String Name;

	public Long getId() {
		return id;
	}

	public String getName() {
		return Name;
	};

}
