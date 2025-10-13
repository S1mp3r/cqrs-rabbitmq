package br.rafael.users.api.handlers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.rafael.users.api.exceptions.NotFoundException;
import br.rafael.users.api.models.ExceptionBody;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionBody> handleNotFoundException(NotFoundException ex, HttpServletRequest req) {
        var errorDTO = ExceptionBody.builder()
            .timestamp(System.currentTimeMillis())
            .status(HttpStatus.NOT_FOUND.value())
            .messages(List.of(ex.getMessage()))
            .path(req.getRequestURI())
            .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionBody> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest req) {
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(err -> err.getDefaultMessage())
            .toList();

        var errorDTO = ExceptionBody.builder()
            .timestamp(System.currentTimeMillis())
            .status(HttpStatus.BAD_REQUEST.value())
            .messages(errors)
            .path(req.getRequestURI())
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionBody> objectGenericExceptionHandler(RuntimeException excep, HttpServletRequest req) {
        var errorDTO = ExceptionBody
            .builder()
            .timestamp(System.currentTimeMillis())
            .status(HttpStatus.NOT_FOUND.value())
            .messages(List.of("An unexpected error occourred: " + excep.getMessage()))
            .path(req.getRequestURI())
            .build()
        ;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

}
