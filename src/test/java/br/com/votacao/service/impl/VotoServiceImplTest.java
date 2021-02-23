package br.com.votacao.service.impl;

import br.com.votacao.domain.Sessao;
import br.com.votacao.domain.Voto;
import br.com.votacao.fixture.SessaoFixture;
import br.com.votacao.fixture.VotoFixture;
import br.com.votacao.repository.VotoRepository;
import br.com.votacao.service.VerificarCpfAssociadoService;
import br.com.votacao.service.VotoService;
import br.com.votacao.service.validator.ValidadorVoto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VotoServiceImplTest {

    @Mock protected VerificarCpfAssociadoService verificarCpfAssociadoServiceMock;
    @Mock protected VotoRepository votoRepositoryMock;
    @Mock protected ValidadorVoto validadorVotoMock;

    protected VotoService votoService;

    protected Sessao sessao;
    protected Voto voto;
    protected List<Voto> votos;

    @Before
    public void inicializarContexto() {
        votoService = new VotoServiceImpl(verificarCpfAssociadoServiceMock, votoRepositoryMock, validadorVotoMock);

        sessao = SessaoFixture.umaSessao();

        voto = VotoFixture.umVoto();
        voto.setSessao(sessao);

        votos = new ArrayList<>();
    }

    @Test
    public void AoCadastrarDeveriaValidarIhCadastrarVoto() {
        when(votoRepositoryMock.save(voto)).thenReturn(voto);

        votar();

        verify(validadorVotoMock).validar(voto);
        verify(verificarCpfAssociadoServiceMock).verificar(voto.getAssociadoCpf());
    }

    @Test
    public void AoCadastrarDeveriaRetornarVotoCadastrado() {
        when(votoRepositoryMock.save(voto)).thenReturn(voto);

        Voto votoCadastrado = votar();
        assertSame(voto, votoCadastrado);
    }

    private Voto votar() {
        return votoService.votar(voto);
    }

    @Test
    public void AoConsultarVotosDeveriaRetornarVotosDaSessao() {
        when(votoRepositoryMock.findAllBySessao(sessao)).thenReturn(votos);

        List<Voto> votosConsultados = votoService.consultarVotos(sessao);
        assertSame(votos, votosConsultados);
    }
}
