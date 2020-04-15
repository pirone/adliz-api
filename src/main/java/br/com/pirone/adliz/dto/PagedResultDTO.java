package br.com.pirone.adliz.dto;

import java.util.List;

import org.springframework.data.domain.Page;

public class PagedResultDTO {
	
	private List<?> content;
	private Integer totalPages;
	private Long totalItems;
	private String message;

	public PagedResultDTO(List<?> content) {
		this.content = content;
	}
	
	public PagedResultDTO(List<?> content, Long totalItems) {
		this.content = content;
		this.totalItems = totalItems;
	}
	
	public PagedResultDTO(Page<?> content, Integer totalPages) {
		this.content = content.getContent();
		this.totalPages = totalPages;
	}
	
	public PagedResultDTO(List<?> content, Integer totalPages, Long totalItems) {
		this.content = content;
		this.totalPages = totalPages;
		this.totalItems = totalItems;
	}
	
	public PagedResultDTO(Page<?> content, Integer totalPages, Long totalItems) {
		this.content = content.getContent();
		this.totalPages = totalPages;
		this.totalItems = totalItems;
	}
	public PagedResultDTO(String message) {
		this.message = message;
	}
	
	public List<?> getContent() {
		return content;
	}
	public void setContent(List<?> content) {
		this.content = content;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
