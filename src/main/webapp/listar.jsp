<%--
  Created by IntelliJ IDEA.
  User: adrien
  Date: 31/05/2026
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="com.webdev.syslogin.model.Usuario" %>

<%List<Usuario> lista = (List<Usuario>) request.getAttribute("users");%>

<html>
<head>
    <title>Lista de Usuários</title>
</head>
<body>
<h1>Usuários Cadastrados</h1>

<a href="index.html">Voltar para a home (Cadastro)</a> <br><br>

<ul>
    <% if(lista != null && !lista.isEmpty()){

            for (Usuario u : lista) { %>

    <li>
        <strong>Nome:</strong> <%= u.getNome() %> |
        <strong>Email:</strong> <%= u.getEmail() %>
    </li> <% }

    } else { %>
    <li> Ainda sem nenhum usuário... :( </li>
    <% }  %>

</ul>
</body>
</html>