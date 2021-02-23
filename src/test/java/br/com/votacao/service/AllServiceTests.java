package br.com.votacao.service;

import br.com.votacao.service.impl.ResultadoServiceImplTest;
import br.com.votacao.service.impl.SessaoServiceImplTest;
import br.com.votacao.service.impl.VerificarCpfAssociadoServiceImplTest;
import br.com.votacao.service.impl.VotoServiceImplTest;
import br.com.votacao.service.validator.impl.ValidadorSessaoImplTest;
import br.com.votacao.service.validator.impl.ValidadorVotoImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ResultadoServiceImplTest.class,
        SessaoServiceImplTest.class,
        VerificarCpfAssociadoServiceImplTest.class,
        VotoServiceImplTest.class,

        ValidadorSessaoImplTest.class,
        ValidadorVotoImplTest.class
})
public class AllServiceTests {
}
