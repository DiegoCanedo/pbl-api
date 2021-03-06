package br.com.t2m.pblapi.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler{

	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		Map<String, String> fields = new HashMap<>();

		for (ObjectError erro : ex.getBindingResult().getAllErrors()) {
			String atributo = ((FieldError) erro).getField();
			String mensagem = erro.getDefaultMessage();
			fields.put(atributo, mensagem);
		}

		var body = new ErrorBody();
		body.setStatus(status.value());
		body.setMensage("Um ou mais campos estao invalidos. Faca o preenchimento correto e tente novamente");
		body.setDate(LocalDateTime.now());
		body.setFields(fields);

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public List<String> trataDataException(ConstraintViolationException exception){
		List <String> error = new ArrayList<>();
		Set<ConstraintViolation<?>> cv = exception.getConstraintViolations();
		for(ConstraintViolation<?> item : cv) {
			String r = "Campo " + item.getPropertyPath() + " " + item.getMessage();
			error.add(r);			
		}		
		 return error;
	}	

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
		return ResponseEntity.badRequest()
				.header("error_code", HttpStatus.BAD_REQUEST.toString())
				.header("error_value", String.valueOf(e.getValue())).body(e.getMessage());
	}

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<String> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e) {
		return ResponseEntity.badRequest()
				.header("error_code", HttpStatus.BAD_REQUEST.toString())
				.header("error_value", String.valueOf(e.getValue())).body(e.getMessage());
	}
	
	@ExceptionHandler(InvalidDateException.class)
	public ResponseEntity<String> handleResourceAlreadyExistsException(InvalidDateException e) {
		return ResponseEntity.badRequest()
				.header("error_code", HttpStatus.BAD_REQUEST.toString())
				.header("error_value", "").body(e.getMessage());
	}
	
	@ExceptionHandler(ResourceAlreadyBounded.class)
	public ResponseEntity<String> handleResourceAlreadyBoundedException(ResourceAlreadyBounded e) {
		return ResponseEntity.badRequest()
				.header("error_code", HttpStatus.BAD_REQUEST.toString())
				.header("error_value", "").body(e.getMessage());
	}
	
	@ExceptionHandler(TaskRestrictionException.class)
	public ResponseEntity<String> TaskRestrictionException(TaskRestrictionException e) {
		return ResponseEntity.badRequest()
				.header("error_code", HttpStatus.BAD_REQUEST.toString())
				.header("error_value ").body(e.getMessage());
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> DataIntegrityViolationException(DataIntegrityViolationException exception){
		return ResponseEntity.badRequest()
				.header("error_code", HttpStatus.BAD_REQUEST.toString())
				.header("error_value ").body(exception.getMessage());
	}
}
