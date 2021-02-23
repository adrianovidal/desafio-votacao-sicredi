package br.com.votacao.repository;

import br.com.votacao.domain.Sessao;
import br.com.votacao.domain.Voto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository extends CrudRepository<Voto, Long> {

    Voto findByAssociadoIdenAndSessao_id(Long associadoIden, Long sequencial);

    List<Voto> findAllBySessao(Sessao sessao);
}
