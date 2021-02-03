package br.com.votacao.cucumber.stepdefs.voto.verificador;

import org.junit.Assert;

public class VotoVerificador {

    public static void verificarPautaCadastrada(String mensagemRetornada, String mensagemEsperada) {
        Assert.assertEquals(mensagemEsperada, mensagemRetornada);
    }
}
