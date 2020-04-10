package br.com.pirone.adliz.model;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.pirone.adliz.util.DateUtils;

@Entity
@Table(name = "person")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String email;
	
	@NotBlank
	@Column(unique = true)
	private String cpf;
	
	@JsonFormat(pattern="dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date birth_date;
	
    @ElementCollection
    @CollectionTable(name = "person_phone_number", joinColumns = @JoinColumn(name = "person_id"))
    @Column(name = "phone_number")
	private Set<String> phones = new HashSet<>();
	
    @ElementCollection
    @CollectionTable(name = "person_adresses", joinColumns = @JoinColumn(name = "person_id"))
    @Column(name = "adress")
	private Set<String> adresses = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		String cpfFormatado = cpf.replaceAll("\\.", "").replace("-", "");
		this.cpf = cpfFormatado;
	}

//	public String getBirth_date() {
//		return DateUtils.getFormatedDateAsString(birth_date);
//	}
	
	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	
	public void setBirth_date(String birth_date) throws ParseException {
		this.birth_date = DateUtils.getFormatedStringAsDate(birth_date);
	}


	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	public Set<String> getAdresses() {
		return adresses;
	}

	public void setAdresses(Set<String> adresses) {
		this.adresses = adresses;
	}
	
	

}
