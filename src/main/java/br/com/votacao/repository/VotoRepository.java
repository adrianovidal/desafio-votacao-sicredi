package br.com.votacao.repository;

import br.com.votacao.domain.Voto;
import org.springframework.data.repository.CrudRepository;

public interface VotoRepository extends CrudRepository<Voto, Long> {

    Voto findByAssociadoIdenAndSessao_Sequencial(Long associadoIden, Long sequencial);
}
