package br.com.votacao.repository;

import br.com.votacao.domain.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {

}
