package br.com.votacao.controller;

import br.com.votacao.domain.Pauta;
import br.com.votacao.repository.PautaRepository;
import br.com.votacao.share.builders.PautaBuild;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class PautaController {

	private final PautaRepository pautaRepository;

	public PautaController(PautaRepository pautaRepository) {
		this.pautaRepository = pautaRepository;
	}

	@PostMapping(value = "/pauta")
	public Pauta save(@RequestParam("nome") String nome) {
		Pauta pauta = PautaBuild.of().comNome(nome).build();
		return pautaRepository.save(pauta);
	}

}