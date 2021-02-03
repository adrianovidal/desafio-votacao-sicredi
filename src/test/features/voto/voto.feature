#language: pt
@sessao_cadastro
Funcionalidade: Enviar o voto para registro na sessão.

    Cenario: 01 - Enviar o voto do associado em uma Assembleia
        Dado que exista na base a pauta "Licitação"
        E que exista na base a asembleia "1";
        E que foi informado os seguintes votos
            | idAssociado | voto |
            | 1           | Sim  |
        Quando solicitar cadastrar um novo voto
        Então Retornar o seguinte mensasgem "Voto cadastrado!"

    Cenario: 02 - Enviar o voto do associado em uma Assembleia com CPF inválido
        Dado que exista na base a pauta "Licitação"
        E que exista na base a asembleia "1";
        E que foi informado os seguintes votos
            | idAssociado | cpfAssociado | voto |
            | 1           | 1111         | Sim  |
        Quando solicitar cadastrar um novo voto
        Então Retornar o seguinte mensasgem "CPF inválido!"
