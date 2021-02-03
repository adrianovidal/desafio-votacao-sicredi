package br.com.votacao.cucumber.stepdefs;

import br.com.votacao.VotacaoApplication;
import br.com.votacao.VotacaoApplicationTests;
import br.com.votacao.share.dto.PautaDto;
import br.com.votacao.share.dto.SessaoDto;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static br.com.votacao.share.ConstantsTests.MAPPER;
import static br.com.votacao.share.ConstantsTests.TYPE_FACTORY;

@WebAppConfiguration
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {VotacaoApplication.class, VotacaoApplicationTests.class})
public abstract class StepDefs {

    public PautaDto pautaDto;
    public SessaoDto sessaoDto;

    public ResultActions actions;

    protected <T> T obterObjetoRetornado(Class<T> clazz) {
        try {
            return asJsonToClass(obterResposta().getContentAsString(), clazz);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static <T> T asJsonToClass(final String json, Class<T> classe) {
        try {
            return MAPPER.readValue(json, TYPE_FACTORY.constructType(classe));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private MockHttpServletResponse obterResposta() {
        return actions.andReturn().getResponse();
    }

    public void limparBanco() {
        String[] tabelas = {"VOTO", "SESSAO", "PAUTA"};
        String[] colunas = {"id", "sequencial", "id"};
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:votacao_db_teste", "sa", "sa");

            int index = 0;
            for (String tabela : tabelas) {
                conn.createStatement().execute(String.format("DELETE FROM %s", tabelas[index]));
                conn.createStatement().execute(String.format("ALTER TABLE %s ALTER COLUMN id RESTART WITH 1", tabelas[index]));
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
