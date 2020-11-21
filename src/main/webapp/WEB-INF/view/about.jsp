<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About Page</title>
</head>
<body>
    <jsp:include page="_header.jsp"/>

    <br> User Name: ${loginedUser} <br>

    <h1>Warning: </h1>
    This program is protected by copyright law and international treaties. <br>
    Unauthorized reproduction or distribution of this program, or any portion of it, <br>
    may result in severe civil and criminal penalties.

    <jsp:include page="_footer.jsp"/>
</body>
</html>
