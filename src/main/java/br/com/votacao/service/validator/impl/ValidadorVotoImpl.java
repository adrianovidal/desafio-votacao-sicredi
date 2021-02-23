package br.com.votacao.service.validator.impl;

import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.domain.Sessao;
import br.com.votacao.domain.Voto;
import br.com.votacao.repository.VotoRepository;
import br.com.votacao.service.validator.ValidadorVoto;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.votacao.share.Constants.O_ASSOCIADO_JA_REALIZOU_SEU_VOTO_NESTA_SESSAO;
import static java.util.Optional.ofNullable;

@Component
public class ValidadorVotoImpl implements ValidadorVoto {

    private final VotoRepository votoRepository;

    public ValidadorVotoImpl(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    @Override
    public void validar(Voto voto) {
        voto.setId(null);
        Optional<Voto> votoConsultado = consultarVoto(voto);
        votoConsultado.ifPresent(v -> {
            throw new NegocioException(O_ASSOCIADO_JA_REALIZOU_SEU_VOTO_NESTA_SESSAO);
        });
    }

    private Optional<Voto> consultarVoto(Voto voto) {
        Sessao sessao = voto.getSessao();
        return ofNullable(this.votoRepository.findByAssociadoIdenAndSessao_id(voto.getAssociadoIden(), sessao.getId()));
    }
}
