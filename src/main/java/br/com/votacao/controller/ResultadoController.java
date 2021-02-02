package br.com.votacao.controller;

import br.com.votacao.service.ResultadoService;
import br.com.votacao.share.Resultado;
import br.com.votacao.share.dto.ResultadoDto;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class ResultadoController {

	private final ResultadoService resultadoService;
	private final ModelMapper modelMapper;

	public ResultadoController(ResultadoService resultadoService, ModelMapper modelMapper) {
		this.resultadoService = resultadoService;
		this.modelMapper = modelMapper;
	}

	@PostMapping(value = "/resultado", produces="application/json")
	public ResultadoDto save(@RequestBody ResultadoDto resultadoDto) {
		Resultado resultado = converterEmEntity(resultadoDto);
		Resultado resultadoFinal = this.resultadoService.resultado(resultado);
		return convertToDto(resultadoFinal);
	}

	private Resultado converterEmEntity(ResultadoDto resultadoDto) {
		return modelMapper.map(resultadoDto, Resultado.class);
	}

	private ResultadoDto convertToDto(Resultado resultado) {
		return modelMapper.map(resultado, ResultadoDto.class);
	}
}