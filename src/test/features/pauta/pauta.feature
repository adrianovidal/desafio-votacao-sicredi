#language: pt
@pauta_cadastro
Funcionalidade: Enviar requisição para cadastro de uma nova pauta.

    Cenario: 01 - Enviar título da pauta para cadastro
        Dado que foi informado o título "Licitação"
        Quando solicitar cadastrar nova pauta
        Então Retornar a seguine pauta cadastrada
            | id | nome      |
            | 1  | Licitação |
