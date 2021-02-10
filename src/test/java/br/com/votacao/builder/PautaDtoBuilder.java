package br.com.votacao.builder;

import br.com.votacao.share.dto.PautaDto;

import static br.com.votacao.share.ConstantsTests.LICITACAO;

public class PautaDtoBuilder {

    private final PautaDto pautaDto;

    PautaDtoBuilder () {
        pautaDto = new PautaDto();
    }

    public static PautaDtoBuilder of () {
        return new PautaDtoBuilder();
    }

    public static PautaDtoBuilder umaPautaDto() {
        return of().comId(1L).comNome(LICITACAO);
    }

    public PautaDtoBuilder comId(Long id) {
        pautaDto.setId(id);
        return this;
    }

    public PautaDtoBuilder comNome(String nome) {
        pautaDto.setNome(nome);
        return this;
    }

    public PautaDto build() {
        return pautaDto;
    }
}
