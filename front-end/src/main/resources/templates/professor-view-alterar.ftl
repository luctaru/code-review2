<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Gerencia Professor</title>
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
            <h1>Gerenciamento de Professor</h1>
            <p>Essa página é responsável por fazer o geranciamento de professores. </p>
        </div>
        <div class="row">
            <div class="col">
                <form action="/professor/alterar" method="post">
                    <div class="form-group">
                        <label for="nome">Nome:</label>
                        <input value="${(professorAtual.nome)!}" name="nome" type="text" class="form-control" id="nome">
                    </div>

                    <input type="hidden" name="ra" value="${(professorAtual.ra)!}"></input>

                    <button type="submit" class="btn btn-warning">Alterar</button>
                </form>

            </div>
        </div>
        <div class="row">
            <div class="col">
                <table class="table table-striped table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Nome</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list professores as professor>
                            <tr>
                                <td>${professor.nome}</td>
                                <td>
                                    <a href="/professor/prepara-alterar?ra=${professor.ra}">Alterar</a>
                                    <a href="/professor/excluir?ra=${professor.ra}">Excluir</a>
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