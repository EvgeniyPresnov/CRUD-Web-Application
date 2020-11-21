<%@ page import="java.sql.SQLOutput" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
    <jsp:include page="_header.jsp"/>
    <form action="home">

    <br><br> Welcome to the bookshop! By default, the list of books is already available to you, <br>
        but you can also edit, delete existing books stored in the database, or create your own book. <br><br>
    </form>
    <jsp:include page="_footer.jsp"/>
</body>
</html>
