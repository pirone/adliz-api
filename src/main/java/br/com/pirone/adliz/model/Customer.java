package br.com.pirone.adliz.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cutomer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id")
	private Person person;
	
    public Customer() {}
    
    public Customer(String id) {
    	this.id = Long.valueOf(id);
    }
    
    public Customer(int id) {
    	this.id = Long.valueOf(id);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}


}
