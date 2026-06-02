<%--
  Created by IntelliJ IDEA.
  User: adrien
  Date: 31/05/2026
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%String e = (String) request.getAttribute("message");%>

<html>
<head>
    <title>Erro de cadastro</title>
</head>
<body>
    <h2 style="color: red;">Erro: Problemas com o Cadastro</h2>

    <% if (e != null) { %>
        <p>Detalhes do Erro: <strong><%= e %></strong></p>
    <% } %>
</body>
</html>