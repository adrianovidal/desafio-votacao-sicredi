package br.com.votacao.service.impl;

import br.com.votacao.domain.Sessao;
import br.com.votacao.domain.Voto;
import br.com.votacao.repository.VotoRepository;
import br.com.votacao.service.VerificarCpfAssociadoService;
import br.com.votacao.service.VotoService;
import br.com.votacao.service.validator.ValidadorVoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoServiceImpl implements VotoService {

    private final VerificarCpfAssociadoService verificarCpfAssociadoService;
    private final ValidadorVoto validadorVoto;
    private final VotoRepository votoRepository;

    @Autowired
    public VotoServiceImpl(VerificarCpfAssociadoService verificarCpfAssociadoService,
                           VotoRepository votoRepository,
                           ValidadorVoto validadorVoto) {
        this.verificarCpfAssociadoService = verificarCpfAssociadoService;
        this.votoRepository = votoRepository;
        this.validadorVoto = validadorVoto;
    }

    @Override
    public Voto votar(Voto voto) {
        verificarAssociadoHapto(voto.getAssociadoCpf());
        validadorVoto.validar(voto);

        return this.votoRepository.save(voto);
    }

    @Override
    public List<Voto> consultarVotos(Sessao sessao) {
        return votoRepository.findAllBySessao(sessao);
    }

    private void verificarAssociadoHapto(String associadoCpf) {
        verificarCpfAssociadoService.verificar(associadoCpf);
    }
}
