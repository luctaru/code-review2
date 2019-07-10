<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Gerencia Aula</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>
    <div class="container">
        <div class="jumbotron">
            <h1>Gerenciamento de Aula</h1>
            <p>Essa página é responsável por fazer o geranciamento de aulas. </p>
        </div>
        <div class="row">
           <!--<div class="col">
                <form action="/aula/criar" method="post">
                    <div class="form-group">
                        <label for="conteudo">Conteudo:</label>
                        <input value="${(aulaAtual.conteudo)!}" name="conteudo" type="text" class="form-control" id="conteudo">
                    </div>
                    <div class="form-group">
                        <label for="data">Data:</label>
                        <input value="${(aulaAtual.data)!}"  name="data" type="date" class="form-control" id="data">
                    </div>
                    <div class="form-group">
                        <label for="observacao">Observação:</label>
                        <input value="${(aulaAtual.observacao)!}"  name="observacao" type="text" class="form-control" id="observacao">
                    </div>
                    <div class="form-group">
                        <p>Substituição:</p>
                        <label for="sim">Sim</label>
                        <input value="${(aulaAtual.substituicao)!}"  name="radio" type="radio" class="form-control" id="sim">
                        <label for="nao">Não</label>
                        <input value="${(aulaAtual.substituicao)!}"  name="radio" type="radio" class="form-control" id="nao">
                    </div>

                    <button type="submit" class="btn btn-primary">Criar</button>
                </form>

            </div>-->
        </div>
        <div class="row">
            <div class="col">
                <table class="table table-striped table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Conteudo</th>
                            <th>Data</th>
                            <th>Observação</th>
                            <th>Substituição</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list aulas as aula>
                            <tr>
                                <td>${aula.conteudo}</td>
                                <td>${aula.data?date}</td>
                                <td>${aula.observacao}</td>
                                <td>${aula.substituicao?string('yes', 'no')}</td>
                                <td>
                                    <a href="/aula/prepara-substituir?id=${aula.id}">Substituir </a>
                                </td>
                            </tr>        
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>

</html>
