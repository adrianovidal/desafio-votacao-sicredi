package br.com.votacao.controller;

import br.com.votacao.service.ResultadoService;
import br.com.votacao.share.response.ResultadoResponse;
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
		ResultadoResponse resultadoResponse = converterEmEntity(resultadoDto);
		ResultadoResponse resultadoResponseFinal = this.resultadoService.resultado(resultadoResponse);
		return convertToDto(resultadoResponseFinal);
	}

	private ResultadoResponse converterEmEntity(ResultadoDto resultadoDto) {
		return modelMapper.map(resultadoDto, ResultadoResponse.class);
	}

	private ResultadoDto convertToDto(ResultadoResponse resultadoResponse) {
		return modelMapper.map(resultadoResponse, ResultadoDto.class);
	}
}