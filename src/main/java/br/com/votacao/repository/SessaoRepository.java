package br.com.votacao.repository;

import br.com.votacao.domain.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    Sessao findByIdAndPauta_Id(Long sequencial, Long pautaId);

    @Query("SELECT s FROM SESSAO s WHERE s.duracao < :duracao AND s.enviadoKafka = false")
    List<Sessao> consultarSessoesFinalizadasSemResultado(@Param("duracao")ZonedDateTime duracao);
}
