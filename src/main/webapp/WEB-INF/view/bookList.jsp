<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Book List</title>
</head>
<body>
    <jsp:include page="_header.jsp"/>

    <h3>List of the books</h3>

    <table border="1" cellpadding="5" cellspacing="1" >
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${listBooks}" var="book">
            <tr>
                <td>${book.bookID}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>
                    <a href="updateBook?bookID=${book.bookID}">Update</a>
                </td>
                <td>
                    <a href="deleteBook?bookID=${book.bookID}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <jsp:include page="_footer.jsp"/>
</body>
</html>
