package br.com.votacao.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PautaControllerTest.class,
        ResultadoControllerTest.class,
        SessaoControllerTest.class,
        VotoControllerTest.class
})
public class AllControllersTests {
}
