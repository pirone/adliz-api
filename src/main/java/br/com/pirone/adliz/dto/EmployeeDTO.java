package br.com.pirone.adliz.dto;

import br.com.pirone.adliz.model.Employee;

public class EmployeeDTO {
	
	public EmployeeDTO(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getPerson().getName();
		this.comissionRate = employee.getComissionRate();
	}
	
	private Long id;
	
	private String name;
	
	private String comissionRate;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getComissionRate() {
		return comissionRate;
	}

}
