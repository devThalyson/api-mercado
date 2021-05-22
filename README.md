<h1>Api Rest - Mercado App 💻</h1>

<h2>📢 Descrição do Repositório 📢: </h2>
<p> Esse repositório abriga uma API REST construída com a linguagem de programação Java e o Framework Spring Boot. Essa API é o back-end das seguintes aplicações móveis: 
"link_app_mercado_cliente_aqui" e "link_app_mercado_gerente_aqui". O principal objetivo desta aplicação é sevir de ponte para inserções, deleções, atualizações e consultas 
de: Usuários, Categorias de produtos, Produtos, Compras e etc. Também funciona como ferramenta de envio de e-mails para usuários que por ventura tenham esquecido sua senha.
Abriga também um WebSocket utilizado com a finalidade de aplicar atualizações em tempo real nas aplicações front-end citadas acima.

A seguir, mais informações sobre essa aplicação serão exibidas, tais como: Requisitos para executar a aplicação em seu computador e instruções para executar a aplicação em seu
computador.</p>
  
<h3>Requisitos para executar a aplicação em seu computador: </h3>
  <p>⚫ Primeiramente, é necessário ter o JDK instalado em seu computador. Caso ainda não o tenha, siga o seguinte tutorial: https://www.devmedia.com.br/instalacao-e-configuracao-do-pacote-java-jdk/23749</p>
  <p>⚫ É necessário também ter o Maven instalado em seu computador. Caso ainda não o tenha, siga o seguinte tutorial: https://dicasdejava.com.br/como-instalar-o-maven-no-windows/</p>
  <p>⚫ Para abrir, executar ou alterar essa aplicação, é necessário ter a IDE Eclipse e o Spring Tool Suite instalados. Pra ficar mais fácil, vou deixar
  um tutorial que mostra como baixar e instalar o eclipse já com o Spring Tool Suite instalado. Segue o link: https://bgasparotto.com/pt/instalar-o-spring-tool-suite-no-eclipse</p>
  <p>⚫ Por fim, para podermos inserir nossos dados, fazer deleções, consultas ou atualizações, é necessário termos um banco de dados SQL instalado localmente. 
  Originalmente o projeto usa o MySQL Workbench, mas existem também outras alternativas. Segue o link de duas alternativas de banco de dados SQL que são compatíveis
  com a dita aplicação: MySQL WorkBench: https://www.mysql.com/products/workbench/ e PostgreSQL: https://www.postgresql.org/.</p>
  
  
<h3>Instruções para executar a aplicação em seu computador: </h3>
  <p>⚫ Abrir a interface de linha de comando da sua preferência</p>
  <p>⚫ digite: git clone https://github.com/devThalyson/api-mercado.git</p>
  <p>⚫ Abrir o Eclipse que contêm o Spring Tool Suite</p>
  <p>⚫ Importe o projeto clonado (link para o tutorial de como importar um projeto Spring: https://medium.com/@alex.girao/importar-um-projeto-maven-spring-boot-ea10078b2bde)</p>
  <p>⚫ Configure o arquivo application.properties (ApiMercado/src/main/resources) e insira os dados do seu banco de dados e email (para funcionalidades como 
  "recuperação de senha"). Link de tutorial para a configuração do MySQL WorkBench com o Spring: https://www.gasparbarancelli.com/post/banco-de-dados-mysql-com-spring-boot e link
  de tutorial para a configuração do Postgres com o Spring: https://cursos.alura.com.br/forum/topico-como-configurar-postgres-para-springboot-86299</p>
  <p>⚫ Por fim, no lado superior esquerdo de seu Eclipse deve aparecer o projeto com o nome "MercadoApi". Clique com o botão direito do mouse sobre ele e em "Run As"
  clique em Spring Boot App</p>
  
 <h3> Se os passos forem seguidos corretamente, nenhum erro ocorrerá e nossa aplicação vai estar pronta para servir como back-end do nossos Apps de Mercado.</h3>
