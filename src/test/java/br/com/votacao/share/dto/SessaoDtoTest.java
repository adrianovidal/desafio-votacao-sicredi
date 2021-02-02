package br.com.votacao.share.dto;

import br.com.votacao.domain.Pauta;
import br.com.votacao.domain.Sessao;
import br.com.votacao.share.converter.DateStringConverter;
import br.com.votacao.share.converter.StringDateConverter;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.Date;

import static java.lang.Long.parseLong;
import static java.time.ZonedDateTime.now;
import static java.util.Date.from;
import static org.junit.Assert.assertEquals;

public class SessaoDtoTest {

    private final ModelMapper modelMapper =  modelMapper();

    @Test
    public void deveriaConverterEntidadeSessaoParaSessaoDto() {
        Sessao sessao = new Sessao();
        sessao.setSequencial(1L);
        sessao.setDuracao(now());
        sessao.setPauta(new Pauta() {{ setId(2L); setNome("Licitação"); }});

        SessaoDto sessaoDto = modelMapper.map(sessao, SessaoDto.class);
        assertEquals(sessao.getSequencial(), sessaoDto.getSequencial());
        assertEquals(sessao.getDuracao().toString(), sessaoDto.getDuracao());
        assertEquals(sessao.getPauta().getId(), sessaoDto.getPautaId());
    }

    @Test
    public void deveriaConverterSessaoDtoParaEntidadeSessao() {
        SessaoDto sessaoDto = new SessaoDto();
        sessaoDto.setSequencial(1L);
        sessaoDto.setDuracao("2");
        sessaoDto.setPautaId(2L);

        Sessao sessao = modelMapper.map(sessaoDto, Sessao.class);
        assertEquals(sessaoDto.getSequencial(), sessao.getSequencial());
        assertEquals(obterDuracao(sessaoDto), from(sessao.getDuracao().toInstant()).toString());
        assertEquals(sessaoDto.getPautaId(), sessao.getPauta().getId());
    }

    private String obterDuracao(SessaoDto sessaoDto) {
        Date date = from(now().plusMinutes(parseLong(sessaoDto.getDuracao())).toInstant());
        return date.toString();
    }

    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new StringDateConverter());
        modelMapper.addConverter(new DateStringConverter());
        return modelMapper;
    }
}