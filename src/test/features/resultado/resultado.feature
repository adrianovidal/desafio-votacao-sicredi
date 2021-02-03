#language: pt
@sessao_cadastro
Funcionalidade: Enviar requisição obter o resultado de uma Assembleia.

    # A duração da assembleia deve ter um intervalor maior que a duração (min) convertida em segundos

    Cenario: 01 - Enviar requisição para obter o resultado de uma assembleia
        Dado que exista na base a pauta "Licitação"
        E que exista na base a asembleia "1";
        E que esista na base os seguintes votos
            | idAssociado | voto |
            | 1           | Sim  |
            | 2           | Não  |
            | 3           | Não  |
            | 4           | Sim  |
            | 5           | Sim  |
        Quando solicitar o resultado de uma Assembleia
        Então Retornar a seguine dados da votação
            | votosSim | votosNao | totalVotos | resultado |
            | 3        | 2        | 5          | PARCIAL   |
