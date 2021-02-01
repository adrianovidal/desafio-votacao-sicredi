package br.com.votacao.controller.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@ControllerAdvice
public class ExceptionTranslator implements ProblemHandling {

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Problem> handleConcurrencyFailure(NegocioException ex, NativeWebRequest request) {
        Problem problem = new Problem();
        problem.setStatus(Status.CONFLICT);
        problem.setMessage(ex.getMessage());
        return ResponseEntity.ok(problem);
    }
}
