package org.maxwell.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.thymeleaf.exceptions.TemplateInputException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ WebExchangeBindException.class, TemplateInputException.class })
	public String handleBindingException(Exception exception, Model model) {

		log.error("Handling Binding Exception");
		log.error(exception.getMessage());

		model.addAttribute("exception", exception);

		return "400error";
	}
}
