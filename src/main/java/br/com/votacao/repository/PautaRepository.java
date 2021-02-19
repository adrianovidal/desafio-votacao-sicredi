package br.com.votacao.repository;

import br.com.votacao.domain.Pauta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends CrudRepository<Pauta, Long> {
}
