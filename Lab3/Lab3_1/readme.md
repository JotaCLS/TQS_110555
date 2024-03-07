## File for answering questions  

### a) Identify a couple of examples that use AssertJ expressive methods chaining.

#### Ans: No teste A, por exemplo, é usado o assertThat (linhas 37, 43 ...). No teste B verifica-se o mesmo cenario. No teste D tambem é usado 1 vez. No E é usado multiplas vezes tambem.

### b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).

#### Ans: No teste B faz-se um mock do repositorio e define-se todos os retornos expectaveis do objeto em seguida criado.

### c) What is the difference between standard @Mock and @MockBean?

#### Ans: O @Mock standard é usado em qualquer tipo de projeto, o @MockBean é usado especificamente para o funcionar no SpringBoot.

### d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

#### Ans: É um ficheiro criado para configurar testes de integracao numa aplicacao SpringBoot. É usado por exemplo quando queremos configurar especificamente um teste ou uma base de dados.

### e) the sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

#### Ans: Nos testes C e D é criado um mock de um Web Enviorement, no teste E é realemente usado o Web Enviormente criado pela aplicacao SpringBoot.