package br.com.votacao.share.dto;

import br.com.votacao.domain.Sessao;
import br.com.votacao.domain.Voto;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;

public class VotoDtoTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void deveriaConverterEntidadeVotoParaVotoDto() {
        Voto voto = new Voto();
        voto.setSessao(new Sessao() {{ setSequencial(1L); }});
        voto.setVoto("Não");
        voto.setAssociadoIden(2L);
        voto.setAssociadoCpf("07568173070");

        VotoDto votoDto = modelMapper.map(voto, VotoDto.class);
        assertEquals(voto.getVoto(), votoDto.getVoto());
        assertEquals(voto.getAssociadoCpf(), votoDto.getAssociadoCpf());
        assertEquals(voto.getAssociadoIden(), votoDto.getAssociadoIden());
        assertEquals(voto.getSessao().getSequencial(), votoDto.getSessaoSequencial());
    }

    @Test
    public void deveriaConverterSessaoDtoParaEntidadeSessao() {
        VotoDto votoDto = new VotoDto();
        votoDto.setVoto("Sim");
        votoDto.setAssociadoCpf("07568173070");
        votoDto.setAssociadoIden(2L);
        votoDto.setSessaoSequencial(2L);

        Voto voto = modelMapper.map(votoDto, Voto.class);
        assertEquals(votoDto.getAssociadoIden(), voto.getAssociadoIden());
        assertEquals(votoDto.getAssociadoCpf(), voto.getAssociadoCpf());
        assertEquals(votoDto.getSessaoSequencial(), voto.getSessao().getSequencial());
        assertEquals(votoDto.getVoto(), voto.getVoto());
    }
}