package br.com.votacao.controller;

import br.com.votacao.domain.Pauta;
import br.com.votacao.repository.PautaRepository;
import br.com.votacao.share.builders.PautaBuild;
import br.com.votacao.share.dto.PautaDto;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@PostMapping(value = "/pauta")
	public PautaDto save(@RequestParam("nome") String nome) {
		Pauta pauta = pautaRepository.save(criarPauta(nome));
		return convertToDto(pauta);
	}

	private Pauta criarPauta(String nome) {
		return PautaBuild.of().comNome(nome).build();
	}

	private PautaDto convertToDto(Pauta pauta) {
		return modelMapper.map(pauta, PautaDto.class);
	}

}