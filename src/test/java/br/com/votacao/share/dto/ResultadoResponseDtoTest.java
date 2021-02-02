package br.com.votacao.share.dto;

import br.com.votacao.domain.Sessao;
import br.com.votacao.share.response.ResultadoResponse;
import br.com.votacao.share.converter.DateStringConverter;
import br.com.votacao.share.converter.StringDateConverter;
import br.com.votacao.share.enuns.ResultadoEnum;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.Date;

import static java.lang.Long.parseLong;
import static java.time.ZonedDateTime.now;
import static org.junit.Assert.assertEquals;

public class ResultadoResponseDtoTest {

    private final ModelMapper modelMapper =  modelMapper();

    @Test
    public void deveriaConverterEntidadeSessaoParaSessaoDto() {
        ResultadoResponse resultadoResponse = new ResultadoResponse();
        resultadoResponse.setIdPauta(1L);
        resultadoResponse.setIdSessao(2L);
        resultadoResponse.setTotalVotos(10);
        resultadoResponse.setVotosNao(3);
        resultadoResponse.setVotosSim(7);
        resultadoResponse.setResultado(ResultadoEnum.FINAL);

        ResultadoDto resultadoDto = modelMapper.map(resultadoResponse, ResultadoDto.class);
        assertEquals(resultadoResponse.getIdPauta(), resultadoDto.getIdPauta());
        assertEquals(resultadoResponse.getIdSessao(), resultadoDto.getIdSessao());
        assertEquals(resultadoResponse.getVotosNao(), resultadoDto.getVotosNao());
        assertEquals(resultadoResponse.getVotosSim(), resultadoDto.getVotosSim());
        assertEquals(resultadoResponse.getTotalVotos(), resultadoDto.getTotalVotos());
        assertEquals(resultadoResponse.getResultado(), resultadoDto.getResultado());
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