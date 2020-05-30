package br.com.pirone.adliz.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.pirone.adliz.model.Entry;
import br.com.pirone.adliz.util.DateUtils;

public class EntryDTO {
	
	private Long id;
	
	private String funcionario;
	private String cliente;
	private BigDecimal total;
	private Date dataRegistro;
	
	public EntryDTO(Entry entry) {
		this.id = entry.getId();
		this.funcionario = entry.getEmployee().getPerson().getName();
		this.cliente = entry.getCustomer().getPerson().getName();
		this.total = entry.getRecord().getValue();
		this.dataRegistro = entry.getRecord().getDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getDataRegistro() {
		return DateUtils.getFormatedDateAsString(dataRegistro);
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

}
