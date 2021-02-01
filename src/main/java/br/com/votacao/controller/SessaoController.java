package br.com.votacao.controller;

import br.com.votacao.domain.Sessao;
import br.com.votacao.service.SessaoService;
import br.com.votacao.share.dto.SessaoDto;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class SessaoController {

	private final SessaoService sessaoService;
	private final ModelMapper modelMapper;

	public SessaoController(SessaoService sessaoService,
							ModelMapper modelMapper) {
		this.sessaoService = sessaoService;
		this.modelMapper = modelMapper;
	}

	@PostMapping(value = "/sessao")
	public SessaoDto save(@RequestBody SessaoDto sessaoDto) {
		Sessao sessao = converterEmEntity(sessaoDto);
		Sessao sessaoCriada = sessaoService.cadastrar(sessao);
		return convertToDto(sessaoCriada);
	}

	private Sessao converterEmEntity(SessaoDto sessaoDto) {
		return modelMapper.map(sessaoDto, Sessao.class);
	}

	private SessaoDto convertToDto(Sessao sessao) {
		return modelMapper.map(sessao, SessaoDto.class);
	}
}