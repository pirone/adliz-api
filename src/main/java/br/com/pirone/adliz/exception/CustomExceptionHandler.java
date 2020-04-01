package br.com.pirone.adliz.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.pirone.adliz.dto.ApiErrorDTO;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	ResourceBundle msg = ResourceBundle.getBundle("messages");
	
	private HttpHeaders JsonHeader() {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		return header;
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	  MethodArgumentNotValidException ex, 
	  HttpHeaders headers, 
	  HttpStatus status, 
	  WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	        errors.add(error.getField() + ": " + error.getDefaultMessage());
	    }
	    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
	    }
	     
	    ApiErrorDTO apiError = 
	      new ApiErrorDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
	    return handleExceptionInternal(
	      ex, apiError, headers, apiError.getStatus(), request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
	  MissingServletRequestParameterException ex, HttpHeaders headers, 
	  HttpStatus status, WebRequest request) {
	    String error = ex.getParameterName() + " parameter is missing";
	     
	    ApiErrorDTO apiError = 
	      new ApiErrorDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
	    return new ResponseEntity<Object>(
	      apiError, JsonHeader(), apiError.getStatus());
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
	  MethodArgumentTypeMismatchException ex, WebRequest request) {
	    String error = 
	      ex.getName() + " should be of type " + ex.getRequiredType().getName();
	 
	    ApiErrorDTO apiError = 
	      new ApiErrorDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
	    return new ResponseEntity<Object>(
	      apiError, JsonHeader(), apiError.getStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
	  HttpRequestMethodNotSupportedException ex, 
	  HttpHeaders headers, 
	  HttpStatus status, 
	  WebRequest request) {
	    StringBuilder builder = new StringBuilder();
	    builder.append(ex.getMethod());
	    builder.append(
	      " method is not supported for this request. Supported methods are ");
	    ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
	 
	    ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.METHOD_NOT_ALLOWED, 
	      ex.getLocalizedMessage(), builder.toString());
	    return new ResponseEntity<Object>(
	      apiError, JsonHeader(), apiError.getStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
	  HttpMediaTypeNotSupportedException ex, 
	  HttpHeaders headers, 
	  HttpStatus status, 
	  WebRequest request) {
	    StringBuilder builder = new StringBuilder();
	    builder.append(ex.getContentType());
	    builder.append(" media type is not supported. Supported media types are ");
	    ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));
	 
	    ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.UNSUPPORTED_MEDIA_TYPE, 
	      ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
	    return new ResponseEntity<Object>(
	      apiError, JsonHeader(), apiError.getStatus());
	}
	
	@ExceptionHandler({	ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		ApiErrorDTO apiError = new ApiErrorDTO(
			HttpStatus.CONFLICT, "Não foi possível realizar a operação. Por favor verifique os dados informados.", ex.getLocalizedMessage());
  	    return new ResponseEntity<Object>(
	  	      apiError, JsonHeader(), apiError.getStatus());
    }
	
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
	    ApiErrorDTO apiError = new ApiErrorDTO(
	    	HttpStatus.CONFLICT, msg.getString("constraint-error"), ex.getLocalizedMessage());
	    return new ResponseEntity<Object>(
	      apiError, JsonHeader(), apiError.getStatus());
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
	    ApiErrorDTO apiError = new ApiErrorDTO(
	      HttpStatus.BAD_REQUEST, "Ocorreu um erro inesperado. Contate um administrador.", ex.getLocalizedMessage());
	    return new ResponseEntity<Object>(
	      apiError, new HttpHeaders(), apiError.getStatus());
	}
    
}