package br.com.votacao.service.impl;

import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.domain.Sessao;
import br.com.votacao.domain.Voto;
import br.com.votacao.repository.VotoRepository;
import br.com.votacao.service.SessaoService;
import br.com.votacao.service.VerificarCpfAssociadoService;
import br.com.votacao.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.votacao.share.Constants.O_ASSOCIADO_JA_REALIZOU_SEU_VOTO_NESTA_SESSAO;
import static br.com.votacao.share.util.VerificadorUtil.naoEstaNulo;

@Service
public class VotoServiceImpl implements VotoService {

    private final VerificarCpfAssociadoService verificarCpfAssociadoService;
    private final VotoRepository votoRepository;
    private final SessaoService sessaoService;

    @Autowired
    public VotoServiceImpl(VerificarCpfAssociadoService verificarCpfAssociadoService,
                           VotoRepository votoRepository,
                           SessaoService sessaoService) {
        this.verificarCpfAssociadoService = verificarCpfAssociadoService;
        this.votoRepository = votoRepository;
        this.sessaoService = sessaoService;
    }

    @Override
    public Voto votar(Voto voto) {
        verificarAssociadoHapto(voto.getAssociadoCpf());

        this.sessaoService.validar(voto.getSessao());
        validarVoto(voto);

        return this.votoRepository.save(voto);
    }

    @Override
    public List<Voto> consultarVotos(Sessao sessao) {
        return votoRepository.findAllBySessao(sessao);
    }

    private void verificarAssociadoHapto(String associadoCpf) {
        this.verificarCpfAssociadoService.verificar(associadoCpf);
    }

    private void validarVoto(Voto voto) {
        voto.setId(null);
        Sessao sessao = voto.getSessao();
        Voto votoConsultado = this.votoRepository.findByAssociadoIdenAndSessao_id(voto.getAssociadoIden(), sessao.getId());
        if (naoEstaNulo(votoConsultado)) {
            throw new NegocioException(O_ASSOCIADO_JA_REALIZOU_SEU_VOTO_NESTA_SESSAO);
        }
    }
}
