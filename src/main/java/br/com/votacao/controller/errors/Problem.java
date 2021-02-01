package br.com.votacao.controller.errors;

import org.zalando.problem.Status;

public class Problem {

    private Status status;
    private String message;

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}