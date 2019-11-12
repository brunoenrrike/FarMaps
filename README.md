# FarMaps
Aplicativo voltado para trazer a listagem de farmácias da cidade de Votuporanga.
Nele se encontraram as farmácias que me deram permissão para exibir seus status e suas informações.
<br>Dentre as informações se encontram: Horários de abertura e fechamento,  suas localizações, seus telefones e email.
<br>Através do clique no ícone do Maps é possível abrir o Google Maps com a localização da Farmácia, e o clique no ícone do Telefone abrirá o seu discador de telefone com o número já pré carregado. 
<p>Android Studio
<br>Desenvolvi ele usando o Android Studio com base em vídeo aulas e pesquisas na Internet.
<br>Ao abri o sistema é carregado inicialmente o arquivo que verifica a conexão de Internet, se não existir, será redirecionado para uma tela de aviso. Após realizado a conexão com a Internet o sistema redirecinára para a tela de listagem. Nesta listagem é feito uma consulta em uma página de Internet que trará as informações em um arquivo em JSON, este arquivo pega as informações de um banco de dados MySql que está hospedado em um servidor online, este arquivo JSON é decodificado pelo sistema e cada informação para seu determinado campo.
<p>JSON
<br>Em um site hospedado que tenho, criei uma pagína web que faz um select nas informações que tenho no banco de dados e transforma elas em arquivo em JSON.
  
<p>Banco de dados
<br>No banco de dados em MySql coloco as informações necessárias das famácias, que irão aparecer no aplicativo, tenho somente uma tabela com estes dados por enquanto.

<br>Link para download na versão Android.:https://play.google.com/store/apps/details?id=xyz.bhabra.farmaps_votuporanga&hl=pt_BR.
<br>
<br>Autor: Bruno Henrique Abra
