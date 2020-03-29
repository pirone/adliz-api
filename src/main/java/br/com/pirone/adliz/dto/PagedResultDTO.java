package br.com.pirone.adliz.dto;

import java.util.List;

import org.springframework.data.domain.Page;

public class PagedResultDTO {
	
	private List<?> content;
	private Integer totalPages;
	private Long totalItems;
	
	public PagedResultDTO(List<?> content) {
		this.content = content;
	}
	
	public PagedResultDTO(List<?> content, Integer totalPages) {
		this.content = content;
		this.totalPages = totalPages;
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

	

}
