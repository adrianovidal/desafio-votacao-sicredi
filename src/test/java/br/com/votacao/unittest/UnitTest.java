package br.com.votacao.unittest;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class UnitTest {

    @Rule
    public ExpectedException contextoExcecao = ExpectedException.none();

    @Rule
    public JUnitRuleMockery contexto = new JUnitRuleMockery();

}
