package br.com.pirone.adliz.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "entry")
public class Entry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToMany
	@JoinTable(name = "entry_services", joinColumns=@JoinColumn(name="entry_id"),
			inverseJoinColumns=@JoinColumn(name="service_id"))
	private Set<Service> services;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "record_id")
	private FinancialRecord record;
	
	private BigDecimal comission;
	
	@OneToOne
	@JoinColumn(name = "payment_method_id")
	private PaymentMethod paymentMethod;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FinancialRecord getRecord() {
		return record;
	}

	public void setRecord(FinancialRecord record) {
		this.record = record;
	}

	public Set<Service> getServices() {
		return services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public BigDecimal getComission() {
		return comission;
	}

	public void setComission(BigDecimal comission) {
		this.comission = comission;
	}
	
	public void setComission(String comissionRate) {
		String formattedComissionRate = comissionRate.replaceAll("\\.", "").replaceAll(",", ".");
		this.comission = new BigDecimal(formattedComissionRate);
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	

}
