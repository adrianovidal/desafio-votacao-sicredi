#language: pt
@sessao_cadastro
Funcionalidade: Enviar requisição para cadastro de uma nova sessão.

    # A duração da assembleia deve ter um intervalor maior que a duração (min) convertida em segundos

    Cenario: 01 - Enviar duração e a identificação da pauta para iniciar uma nova assembleia
        Dado que foi informado a pauta "Licitação"
        E que foi informado a duracao da assenmbléia de "5" minutos
        Quando solicitar cadastrar uma nova assembleia
        Então Retornar a seguine sessao cadastrada
            | id | idPauta | duracao |
            | 1  | 1       | 4       |
