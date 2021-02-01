package br.com.votacao.unittest;

import br.com.votacao.service.impl.SessaoServiceImplTest;
import br.com.votacao.service.impl.VotoServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * <p>Esta suite roda todos os testes unidade rápidos.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    SessaoServiceImplTest.class,
    VotoServiceImplTest.class
})
public class FastUnitTests {

}
