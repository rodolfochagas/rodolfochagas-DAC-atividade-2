<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Agenda - Página de Confirmação de Cadastro</title>
</head>
<body>
<h1>A seguinte entrada foi cadastrada:</h1>
<p>Número: <%= request.getParameter("numero") %></p>
<p>Ano: <%= request.getParameter("ano") %></p>
<p>Data de Início: <%= request.getParameter("dataInicial") %></p>
<p>Data de Fim: <%= request.getParameter("dataFinal") %></p>
<p>Cidade Sede: <%= request.getParameter("cidadeSede") %></p>
<p>País: <%= request.getParameter("pais") %></p></br>
<p><a href=CadastraEdicao.html>Cadastrar outro</a></p>
</body>
</html>