<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Gerencia Pais</title>
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
            <p>Essa página é responsável por fazer o geranciamento de substituir a aula. </p>
        </div>
        <div class="row">
            <div class="col">
                <form action="/substitutiva/criar" method="post">
                    <div class="form-group">
                        <label for="aula">Conteudo: ${aulaAtual.conteudo}</label>
                        <input type="hidden" name="aula" value="${(aulaAtual)!}"></input>
                    </div>
                    <div class="form-group">
                        <label for="profOriginal">Professor Original:</label>
                        <select name="profOriginal" class="form-control" id="profOriginal">
                            <#list professores as professor>
                             <option value="${professor}">${professor.nome}</option>
                            </#list>
                        <select> 
                    </div>
                    <div class="form-group">
                        <label for="profSubstituto">Professor Substituto:</label>
                        <select name="profSubstituto" class="form-control" id="profSubstituto">
                            <#list professores as professor>
                             <option value="${professor}">${professor.nome}</option>
                            </#list>
                        <select> 
                    </div>

                    <button type="submit" class="btn btn-primary">Criar</button>
                </form>

            </div>
        </div>
    </div>
</body>

</html>

