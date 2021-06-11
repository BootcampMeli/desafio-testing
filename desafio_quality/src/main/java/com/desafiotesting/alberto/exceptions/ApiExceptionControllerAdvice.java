package com.desafiotesting.alberto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleConflict(MethodArgumentNotValidException exception,
                                            HttpServletRequest httpServletRequest){
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        List<ErrorDetail> errorDetailList = fieldErrorList
                .stream()
                .map(fieldError -> new ErrorDetail(fieldError.getDefaultMessage(),
                        fieldError.getField(),
                        fieldError.getRejectedValue().toString()))
                .collect(Collectors.toList());
        return new ResponseEntity(errorDetailList, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<?> notReadable(HttpMessageNotReadableException notReadableException){
        String message = "Há algum erro com os campos de entrada, verifique e tente novamente\n\n" +
                notReadableException.getLocalizedMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<?> nullPointer(NullPointerException nullPointerException){
        return new ResponseEntity<String>("O bairro de entrada não existe " +
                "no repositorio de bairros", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
