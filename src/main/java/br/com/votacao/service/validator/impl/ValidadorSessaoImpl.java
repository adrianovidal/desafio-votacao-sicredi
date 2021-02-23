package br.com.votacao.service.validator.impl;

import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.domain.Sessao;
import br.com.votacao.repository.PautaRepository;
import br.com.votacao.repository.SessaoRepository;
import br.com.votacao.service.validator.ValidadorSessao;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.votacao.share.Constants.*;

@Component
public class ValidadorSessaoImpl implements ValidadorSessao {

    private final PautaRepository pautaRepository;
    private final SessaoRepository sessaoRepository;

    public ValidadorSessaoImpl(PautaRepository pautaRepository, SessaoRepository sessaoRepository) {
        this.pautaRepository = pautaRepository;
        this.sessaoRepository = sessaoRepository;
    }

    @Override
    public void validar(Sessao sessao) {
        pautaRepository.findById(sessao.obterIdPauta())
                .orElseThrow(() -> new NegocioException(PAUTA_NAO_LOCALIZADA_INFORMAR_VALOR_CORRETO_OU_CADADASTAR));
    }

    @Override
    public void validar(Long idSessao) {
        Optional<Sessao> sessaoOptional = sessaoRepository.findById(idSessao);

        if (!sessaoOptional.isPresent()) {
            throw new NegocioException(SESSAO_NAO_ENCONTRADA);
        }

        Sessao sessao = sessaoOptional.get();
        if (sessao.estaEncerrada()) {
            throw new NegocioException(A_SESSAO_INFORMADA_ESTA_FECHADA);
        }
    }
}
