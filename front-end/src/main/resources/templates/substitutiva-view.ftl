<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Gerencia Aula Substitutiva</title>
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
            <h1>Gerenciamento de Aula Substitutiva</h1>
            <p>Essa página é responsável por fazer o geranciamento Aula Substitutiva. </p>
        </div>
        <div class="row">
        </div>
        <div class="row">
            <div class="col">
                <table class="table table-striped table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Aula</th>
                            <th>Data</th>
                            <th>Professor Original</th>
                            <th>Professor Substituto</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list substitutivas as substitutiva>
                            <tr>
                                <td>${substitutiva.aula.conteudo}</td>
                                <td>${substitutiva.aula.data?date}</td>
                                <td>${substitutiva.profOriginal.nome}</td>
                                <td>${substitutiva.profSubstituto.nome}</td>
                            </tr>        
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>

</html>
