package br.com.votacao.unittest;

import br.com.votacao.service.impl.ResultadoServiceImplTest;
import br.com.votacao.service.impl.SessaoServiceImplTest;
import br.com.votacao.service.impl.VerificarCpfAssociadoServiceImplTest;
import br.com.votacao.service.impl.VotoServiceImplTest;
import br.com.votacao.share.converter.DateStringConverterTest;
import br.com.votacao.share.converter.StringDateConverterTest;
import br.com.votacao.share.dto.PautaDtoTest;
import br.com.votacao.share.dto.ResultadoDtoTest;
import br.com.votacao.share.dto.SessaoDtoTest;
import br.com.votacao.share.dto.VotoDtoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * <p>Esta suite roda todos os testes unidade rápidos.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SessaoServiceImplTest.class,
        VotoServiceImplTest.class,
        ResultadoServiceImplTest.class,
        VerificarCpfAssociadoServiceImplTest.class,

        PautaDtoTest.class,
        ResultadoDtoTest.class,
        SessaoDtoTest.class,
        VotoDtoTest.class,

        DateStringConverterTest.class,
        StringDateConverterTest.class
})
public class FastUnitTests {

}
