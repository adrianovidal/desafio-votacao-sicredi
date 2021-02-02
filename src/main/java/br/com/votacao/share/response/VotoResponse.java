package br.com.votacao.share.response;

import io.swagger.annotations.ApiModelProperty;

public class VotoResponse {

    @ApiModelProperty(readOnly = true)
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
