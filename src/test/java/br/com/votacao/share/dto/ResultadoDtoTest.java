package br.com.votacao.share.dto;

import br.com.votacao.domain.Sessao;
import br.com.votacao.share.converter.DateStringConverter;
import br.com.votacao.share.converter.StringDateConverter;
import br.com.votacao.share.enuns.ResultadoEnum;
import br.com.votacao.share.response.Resultado;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.Date;

import static java.lang.Long.parseLong;
import static java.time.ZonedDateTime.now;
import static org.junit.Assert.assertEquals;

public class ResultadoDtoTest {

    private final ModelMapper modelMapper =  modelMapper();

    @Test
    public void deveriaConverterEntidadeSessaoParaSessaoDto() {
        Resultado resultado = new Resultado();
        resultado.setIdPauta(1L);
        resultado.setIdSessao(2L);
        resultado.setTotalVotos(10);
        resultado.setVotosNao(3);
        resultado.setVotosSim(7);
        resultado.setResultado(ResultadoEnum.FINAL);

        ResultadoDto resultadoDto = modelMapper.map(resultado, ResultadoDto.class);
        assertEquals(resultado.getIdPauta(), resultadoDto.getIdPauta());
        assertEquals(resultado.getIdSessao(), resultadoDto.getIdSessao());
        assertEquals(resultado.getVotosNao(), resultadoDto.getVotosNao());
        assertEquals(resultado.getVotosSim(), resultadoDto.getVotosSim());
        assertEquals(resultado.getTotalVotos(), resultadoDto.getTotalVotos());
        assertEquals(resultado.getResultado(), resultadoDto.getResultado());
    }

    @Test
    public void deveriaConverterSessaoDtoParaEntidadeSessao() {
        SessaoDto sessaoDto = new SessaoDto();
        sessaoDto.setSequencial(1L);
        sessaoDto.setDuracao("2");
        sessaoDto.setPautaId(2L);

        Sessao sessao = modelMapper.map(sessaoDto, Sessao.class);
        assertEquals(sessaoDto.getSequencial(), sessao.getSequencial());
        assertEquals(obterDuracao(sessaoDto), Date.from(sessao.getDuracao().toInstant()).toString());
        assertEquals(sessaoDto.getPautaId(), sessao.getPauta().getId());
    }

    private String obterDuracao(SessaoDto sessaoDto) {
        return Date.from(now().plusMinutes(parseLong(sessaoDto.getDuracao())).toInstant()).toString();
    }

    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new StringDateConverter());
        modelMapper.addConverter(new DateStringConverter());
        return modelMapper;
    }
}