package br.com.votacao.controller.errors;

import java.util.ArrayList;
import java.util.List;

public class NegocioException extends RuntimeException {

    private static final String MENSAGEM_OCORREU_UM_ERRO_NAO_ESPECIFICADO = "Ocorreu um erro não especificado";

    private static final long serialVersionUID = 1L;

    private final List<String> erroList = new ArrayList<>();

    public NegocioException(String mensagemErro) {
        super(mensagemErro);
        adicionarMensagemErro(mensagemErro);
    }

    /**
     * Método que retorna a mensagem de erro passando como parametro o indice.
     *
     * @param indice
     *            - indice da mensagem
     * @return retorna a mensagem
     */
    public String getMensagemErro(int indice) {
        try {
            if (erroList.isEmpty()) {
                return MENSAGEM_OCORREU_UM_ERRO_NAO_ESPECIFICADO;
            }
            return erroList.get(indice);
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }

    /**
     * Método que adiciona uma mensagem na lista de erros.
     */
    public void adicionarMensagemErro(String mensagem) {
        if (null != mensagem) {
            erroList.add(tratarMensagemDeErro(mensagem));
        }
    }

    /**
     * Método que redifine o método da classe pai. Motivação: acumulará os erros
     * em uma lista interna.
     */
    @Override
    public String getMessage() {
        return getMensagem();
    }

    /**
     * Método que retorna as mensagens de erro inseridas no BOException.
     *
     * @return Mensagem com os erros inseridos.
     */
    private String getMensagem() {
        StringBuilder mensagens = new StringBuilder("");
        if (erroList.isEmpty()) {
            return MENSAGEM_OCORREU_UM_ERRO_NAO_ESPECIFICADO;
        } else {
            mensagens.append(getMensagemErro(0));
            return mensagens.toString();
        }
    }

    protected String tratarMensagemDeErro(String mensagem){
        return mensagem;
    }
}
