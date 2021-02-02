package br.com.votacao.controller;

import br.com.votacao.domain.Pauta;
import br.com.votacao.repository.PautaRepository;
import br.com.votacao.share.dto.PautaDto;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class PautaController {

	private final PautaRepository pautaRepository;
	private final ModelMapper modelMapper;

	public PautaController(PautaRepository pautaRepository, ModelMapper modelMapper) {
		this.pautaRepository = pautaRepository;
		this.modelMapper = modelMapper;
	}

	@PostMapping(value = "/pauta", produces="application/json")
	public PautaDto save(@RequestBody PautaDto pautaDto) {
		Pauta pautaEntity = converterEmEntity(pautaDto);
		Pauta pauta = pautaRepository.save(pautaEntity);
		return convertToDto(pauta);
	}

	private Pauta converterEmEntity(PautaDto pautaDto) {
		return modelMapper.map(pautaDto, Pauta.class);
	}

	private PautaDto convertToDto(Pauta pauta) {
		return modelMapper.map(pauta, PautaDto.class);
	}

}