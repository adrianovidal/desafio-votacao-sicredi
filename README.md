# Desafio Técnico

## Objetivo: 

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação.
A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação.
Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API
REST:

* Cadastrar uma nova pauta;

* Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo
determinado na chamada de abertura ou 1 minuto por default);

* Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é
identificado por um id único e pode votar apenas uma vez por pauta);

* Contabilizar os votos e dar o resultado da votação na pauta

Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces
pode ser considerada como autorizada. A escolha da linguagem, frameworks e bibliotecas é livre (desde que
não infrinja direitos de uso).

É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.

## Linguagens/Tecnologias:
- Java 1.8
- JPA
- JUnit 4 (testes unitários automatizados)
- Spring-Boot
- Maven
- Docker
- ModelMapper
- Kafka (Mensageria Pub/Sub)
- Swagger
- Banco de Dados H2

## Requisitos:

**Java** - Linguagem principal.

**Docker** - Para execução do Kafka, o arquivo docker-compose.yml apresenta as configurações básicas de execução.
- `docke-compose -f docker-compose.yml up -d`

## Links

- [Execução da API](http://localhost:9000)
- [Console do Banco H2](http://localhost:9000/h2-console)
- [Swagger](http://localhost:9000/swagger-ui.html)
- [Repopsitório](https://github.com/adrianovidal/desafio-votacao-api.git)

## Modelos

- **Pauta** - Representa a tema a ser votado.
- **Sessão** - Representa as sessões de votação de uma pauta. Não é necessário duplicar uma pauta para abrir outra sessão de votação.
- **Voto** - Representa os votos dos associados para a sessão.

## Aplicação

- **Cadastro de Pauta:** Criação de uma nova pauta informando seu título.
- **Cadastro de Sessão:** Ao ser aberta a sessão para votação, deverá ser informado seu tempo de duração, do contrário, ela terá duração padrão de 1 (um) minuto. A sessão é fechada para votação automaticamente após completar o tempo de duração.
- **Cadastro de voto:**  Os votos são realizados em uma sessão com as opções "Sim/Não". O associado deve informar sua identificação para evitar duplicidade do seu voto na mesma sessão. O CPF é opcional, caso seja informado, será validado.
- **Validação de CPF:** Todo voto acompanhado do CPF do associado será validado por um serviço externo. Se for positivo, o associado tem seu voto registrado. Em caso negativo, é informado que o associado não poderá realizar a votação.
- **Resultado da votação:** É possível consultar a votação com resultados parciais e finais. Ao fechamento da votação, é realizado automaticamente o envio do resultado por mensageria.