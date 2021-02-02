package br.com.votacao.share.dto;

import br.com.votacao.domain.Pauta;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;

public class PautaDtoTest {

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void deveriaConverterEntidadeVotoParaVotoDto() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setNome("Não");

        PautaDto pautaDto = modelMapper.map(pauta, PautaDto.class);
        assertEquals(pauta.getId(), pautaDto.getId());
        assertEquals(pauta.getNome(), pautaDto.getNome());
    }

    @Test
    public void deveriaConverterSessaoDtoParaEntidadeSessao() {
        PautaDto pautaDto = new PautaDto();
        pautaDto.setId(1L);
        pautaDto.setNome("Não");

        Pauta pauta = modelMapper.map(pautaDto, Pauta.class);
        assertEquals(pautaDto.getId(), pauta.getId());
        assertEquals(pautaDto.getNome(), pauta.getNome());
    }
}