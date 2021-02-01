package br.com.votacao.controller;

import br.com.votacao.domain.Voto;
import br.com.votacao.service.VotoService;
import br.com.votacao.share.dto.VotoDto;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class VotoController {

	private final VotoService votoService;
	private final ModelMapper modelMapper;

	public VotoController(VotoService votoService, ModelMapper modelMapper) {
		this.votoService = votoService;
		this.modelMapper = modelMapper;
	}

	@PostMapping(value = "/votar")
	public void save(@RequestBody VotoDto votoDto) {
		Voto voto = convertToEntity(votoDto);
		votoService.votar(voto);
	}

	private Voto convertToEntity(VotoDto votoDto) {
		return modelMapper.map(votoDto, Voto.class);
	}
}