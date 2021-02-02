package br.com.votacao.unittest;

import br.com.votacao.service.impl.ResultadoResponseServiceImplTest;
import br.com.votacao.service.impl.SessaoServiceImplTest;
import br.com.votacao.service.impl.VerificarCpfAssociadoServiceImplTest;
import br.com.votacao.service.impl.VotoServiceImplTest;
import br.com.votacao.share.converter.DateStringConverterTest;
import br.com.votacao.share.converter.StringDateConverterTest;
import br.com.votacao.share.dto.PautaDtoTest;
import br.com.votacao.share.dto.ResultadoResponseDtoTest;
import br.com.votacao.share.dto.SessaoDtoTest;
import br.com.votacao.share.dto.VotoDtoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * <p>Esta suite roda todos os testes unidade rápidos.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ResultadoResponseServiceImplTest.class,
        SessaoServiceImplTest.class,
        VerificarCpfAssociadoServiceImplTest.class,
        VotoServiceImplTest.class,

        DateStringConverterTest.class,
        StringDateConverterTest.class,

        PautaDtoTest.class,
        ResultadoResponseDtoTest.class,
        SessaoDtoTest.class,
        VotoDtoTest.class
})
public class FastUnitTests {

}
