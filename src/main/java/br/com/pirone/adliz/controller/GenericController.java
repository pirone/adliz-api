package br.com.pirone.adliz.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class GenericController {
	
	Pageable pageable = PageRequest.of(0, 10);
	
	protected Integer defaultPerPage = 12;
	
	public Pageable setPagination(int page, int resultPerPage) {
		return PageRequest.of(page, resultPerPage);
	}
	
	

}
