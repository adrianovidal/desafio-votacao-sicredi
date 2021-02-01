package br.com.votacao.repository;

import br.com.votacao.domain.Pauta;
import org.springframework.data.repository.CrudRepository;

public interface PautaRepository extends CrudRepository<Pauta, Long> {
}
