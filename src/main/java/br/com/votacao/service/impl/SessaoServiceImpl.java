package br.com.votacao.service.impl;

import br.com.votacao.domain.Sessao;
import br.com.votacao.repository.SessaoRepository;
import br.com.votacao.service.SessaoService;
import br.com.votacao.service.validator.ValidadorSessao;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.time.ZonedDateTime.now;

@Service
public class SessaoServiceImpl implements SessaoService {

    private final SessaoRepository sessaoRepository;
    private final ValidadorSessao validadorSessao;

    public SessaoServiceImpl(SessaoRepository sessaoRepository, ValidadorSessao validadorSessao1) {
        this.sessaoRepository = sessaoRepository;
        this.validadorSessao = validadorSessao1;
    }

    @Override
    public Sessao cadastrar(Sessao sessao) {
        validadorSessao.validar(sessao);
        return this.sessaoRepository.save(sessao);
    }

    @Override
    public Sessao consultar(Long idSessaso, Long idPauta) {
        return sessaoRepository.findByIdAndPauta_Id(idSessaso, idPauta);
    }

    @Override
    public void atualizar(Sessao sessao) {
        sessaoRepository.save(sessao);
    }

    @Override
    public List<Sessao> consultarSessoesFinalizadasSemResultadoEnviado() {
        return sessaoRepository.consultarSessoesFinalizadasSemResultado(now());
    }
}
