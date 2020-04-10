package br.com.pirone.adliz.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "service")
public class Service {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(unique = true)
	private String name;
	
	private String description;
	
	@Column(nullable = false) 
	private BigDecimal price;
	
	@OneToOne
	@JoinColumn(name = "category_id")
	private ServiceCategory category;
	
	private Boolean status = true;
	
	public Service() {}
	
	public Service(String id) {
		this.id = Long.valueOf(id);
	}
	
	public Service(int id) {
		this.id = Long.valueOf(id);
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public void setPrice(String price) {
		String precoFormatado = price.replaceAll("\\.", "").replaceAll(",", ".");
		this.price = new BigDecimal(precoFormatado);
	}

	public ServiceCategory getCategory() {
		return category;
	}

	public void setCategory(ServiceCategory category) {
		this.category = category;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	

}
