package br.com.votacao.builder;

import br.com.votacao.share.dto.VotoDto;

public class VotoDtoBuilder {

    private final VotoDto votoDto;

    VotoDtoBuilder() {
        votoDto = new VotoDto();
    }

    public static VotoDtoBuilder of () {
        return new VotoDtoBuilder();
    }

    public static VotoDtoBuilder umaVotoDto() {
        return of().comAssociadoCpf("57787901047").comAssociadoIden(1L).comVoto("Sim");
    }

    public VotoDtoBuilder comAssociadoCpf(String associadoCpf) {
        votoDto.setAssociadoCpf(associadoCpf);
        return this;
    }

    public VotoDtoBuilder comAssociadoIden(Long associadoIden) {
        votoDto.setAssociadoIden(associadoIden);
        return this;
    }

    public VotoDtoBuilder comVoto(String voto) {
        votoDto.setVoto(voto);
        return this;
    }

    public VotoDto build() {
        return votoDto;
    }
}
