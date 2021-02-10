package br.com.votacao.builder;

import br.com.votacao.share.dto.SessaoDto;

import static br.com.votacao.share.ConstantsTests.LICITACAO;

public class SessaoDtoBuilder {

    private final SessaoDto sessaoDto;

    SessaoDtoBuilder() {
        sessaoDto = new SessaoDto();
    }

    public static SessaoDtoBuilder of () {
        return new SessaoDtoBuilder();
    }

    public static SessaoDtoBuilder umaSessaoDto() {
        return of().comId(1L).comDuracao("1").comPautaId(1L);
    }

    public SessaoDtoBuilder comId(Long id) {
        sessaoDto.setId(id);
        return this;
    }

    public SessaoDtoBuilder comDuracao(String duracao) {
        sessaoDto.setDuracao(duracao);
        return this;
    }

    public SessaoDtoBuilder comPautaId(Long pautaId) {
        sessaoDto.setPautaId(pautaId);
        return this;
    }

    public SessaoDto build() {
        return sessaoDto;
    }
}
