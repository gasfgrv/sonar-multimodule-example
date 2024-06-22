### Como rodar a aplicação

- Subir containers do docker:

```shell
docker comppose up -d
```

- Esperar servidor do sonar para gerar token de acesso

  - Depois terminar todo o processo para subir o servido, acesse http://localhost:9000, logo aparecerá essa tela, as credênciais padrão são admin/admin
  
  ![Tela de login do sonar](docs/01_login.png)

  - Após o login será necesspario fornecer uma nova senha para administrador 
  
  ![Nova senha de amin](docs/02_atualizar-senha.png)
 
  - Nesse caso, crie um projeto manualmente, em _Manually_ 

  ![criar projetro no sonar](docs/03_criar-projeto.png)
  
  - Informe os dados do projeto
  
  ![Dados do projeto](docs/04_dados-do-projeto.png)

  - Suba o código do projeto em _Locally_

  ![Subir código fonte](docs/05_obter-código.png)

  - Gere o token de acesso

  ![Gerando token de acesso](docs/06_token-acesso.png)

- Enviar código para o sonar:

```shell
 mvn clean install -Dsonar.login=<token_sonar>
```

Ao final, o sonarQube gera um dashboard com as informações da qualiadade do seu código

![07_projeto-sonar.png](docs/07_projeto-sonar.png)