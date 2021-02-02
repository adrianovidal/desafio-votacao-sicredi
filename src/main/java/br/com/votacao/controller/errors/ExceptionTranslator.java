package br.com.votacao.controller.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.zalando.problem.Status;

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Problem> handleNegocioException(NegocioException ex, NativeWebRequest request) {
        Problem problem = new Problem();
        problem.setStatus(Status.CONFLICT);
        problem.setMessage(ex.getMessage());
        return ResponseEntity.ok(problem);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Problem problem = new Problem();
        problem.setStatus(Status.BAD_REQUEST);
        problem.setMessage(ex.getMessage());
        return ResponseEntity.ok(problem);
    }
}
