package br.com.votacao.service.validator.impl;

import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.domain.Voto;
import br.com.votacao.fixture.SessaoFixture;
import br.com.votacao.fixture.VotoFixture;
import br.com.votacao.repository.VotoRepository;
import br.com.votacao.service.validator.ValidadorVoto;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.votacao.share.Constants.O_ASSOCIADO_JA_REALIZOU_SEU_VOTO_NESTA_SESSAO;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorVotoImplTest {

    private ValidadorVoto validadorVoto;

    @Mock protected VotoRepository votoRepositoryMock;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Voto voto;

    @Before
    public void inicializarContexto() {
        validadorVoto = new ValidadorVotoImpl(votoRepositoryMock);

        voto = VotoFixture.umVoto();
        voto.setSessao(SessaoFixture.umaSessao());
    }

    @Test
    public void aoValidarDadoQueExistaVotoDoAssociadoCadastradoDeveriaLancarExcecaoComAhMensagemEsperada() {
        when(votoRepositoryMock.findByAssociadoIdenAndSessao_id(voto.getAssociadoIden(), voto.getSessao().getId())).thenReturn(voto);

        exception.expectMessage(O_ASSOCIADO_JA_REALIZOU_SEU_VOTO_NESTA_SESSAO);
        exception.expect(NegocioException.class);

        validadorVoto.validar(voto);
    }

    @Test
    public void AoValidarDadoQueNaoExistaVotoDoAssociadoNaoDeveriaLancarExcecao() {
        when(votoRepositoryMock.findByAssociadoIdenAndSessao_id(voto.getAssociadoIden(), voto.getSessao().getId())).thenReturn(null);

        validadorVoto.validar(voto);
    }
}
