package br.com.votacao.service.impl;

import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.domain.Sessao;
import br.com.votacao.repository.SessaoRepository;
import br.com.votacao.service.SessaoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.votacao.share.Constants.SESSAO_NAO_ENCONTRADA_OU_ENCERRADA;
import static br.com.votacao.share.util.VerificadorUtil.estaNulo;
import static java.time.ZonedDateTime.now;

@Service
public class SessaoServiceImpl implements SessaoService {

    private final SessaoRepository sessaoRepository;

    public SessaoServiceImpl(SessaoRepository sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    @Override
    public void validar(Sessao sessao) {
        Optional<Sessao> sessaoConsultada = this.sessaoRepository.findById(sessao.getSequencial());
        validarSessaoAberta(sessaoConsultada.orElse(null));
    }

    private void validarSessaoAberta(Sessao sessao) {
        if (estaNulo(sessao) || now().isAfter(sessao.getDuracao())) {
            throw new NegocioException(SESSAO_NAO_ENCONTRADA_OU_ENCERRADA);
        }
    }

    @Override
    public Sessao cadastrar(Sessao sessao) {
        return this.sessaoRepository.save(sessao);
    }
}
