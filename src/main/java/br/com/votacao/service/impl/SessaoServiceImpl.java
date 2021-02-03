package br.com.votacao.service.impl;

import br.com.votacao.controller.errors.NegocioException;
import br.com.votacao.domain.Pauta;
import br.com.votacao.domain.Sessao;
import br.com.votacao.repository.PautaRepository;
import br.com.votacao.repository.SessaoRepository;
import br.com.votacao.service.SessaoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.votacao.share.Constants.PAUTA_NAO_LOCALIZADA_INFORMAR_VALOR_CORRETO_OU_CADADASTAR;
import static br.com.votacao.share.Constants.SESSAO_NAO_ENCONTRADA;
import static br.com.votacao.share.util.VerificadorUtil.estaNulo;
import static java.time.ZonedDateTime.now;

@Service
public class SessaoServiceImpl implements SessaoService {

    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;

    public SessaoServiceImpl(SessaoRepository sessaoRepository, PautaRepository pautaRepository) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
    }

    @Override
    public void validar(Sessao sessao) {
        Optional<Sessao> sessaoConsultada = this.sessaoRepository.findById(sessao.getId());
        validarSessaoAberta(sessaoConsultada.orElse(null));
    }

    private void validarSessaoAberta(Sessao sessao) {
        if (estaNulo(sessao) || now().isAfter(sessao.getDuracao())) {
            throw new NegocioException(SESSAO_NAO_ENCONTRADA);
        }
    }

    @Override
    public Sessao cadastrar(Sessao sessao) {
        Pauta pauta = sessao.getPauta();
        Optional<Pauta> paulta = this.pautaRepository.findById(pauta.getId());
        validarPauta(paulta.orElse(null));
        return this.sessaoRepository.save(sessao);
    }

    private void validarPauta(Pauta orElse) {
        if (estaNulo(orElse)) {
            throw new NegocioException(PAUTA_NAO_LOCALIZADA_INFORMAR_VALOR_CORRETO_OU_CADADASTAR);
        }
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
    public List<Sessao> consultarSessoesFinalizadasSemResultadoEnviaddo() {
        return sessaoRepository.consultarSessoesFinalizadasSemResultado(now());
    }
}
