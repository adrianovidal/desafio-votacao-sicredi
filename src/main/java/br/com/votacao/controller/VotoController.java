package br.com.votacao.controller;

import br.com.votacao.domain.Voto;
import br.com.votacao.service.VotoService;
import br.com.votacao.share.dto.VotoDto;
import br.com.votacao.share.response.VotoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.votacao.share.builders.VotoResponseBuild.votoCadastrado;

@RestController
@RequestMapping(path = "/api")
public class VotoController {

	private final VotoService votoService;
	private final ModelMapper modelMapper;

	public VotoController(VotoService votoService, ModelMapper modelMapper) {
		this.votoService = votoService;
		this.modelMapper = modelMapper;
	}

	@PostMapping(value = "/voto", produces="application/json")
	public VotoDto save(@RequestBody VotoDto votoDto) {
		Voto voto = convertToEntity(votoDto);
		Voto votarCadastrado = votoService.votar(voto);
		return convertToDto(votarCadastrado);
	}

	private Voto convertToEntity(VotoDto votoDto) {
		return modelMapper.map(votoDto, Voto.class);
	}

	private VotoDto convertToDto(Voto voto) {
		return modelMapper.map(voto, VotoDto.class);
	}
}
