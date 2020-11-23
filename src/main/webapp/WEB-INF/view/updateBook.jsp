<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Book</title>
</head>
<body>
    <jsp:include page="_header.jsp"/>

    <h3>Update Book</h3>

    <form action="updateBook" method="post">
        <input type="hidden" name="bookID" value="${book.bookID}"/>
        <table border="0">
            <tr>
                <td>ID</td>
                <td style="color:black;">${book.bookID}</td>
            </tr>
            <tr>
                <td>Title: </td>
                <td><input type="text" name="title" value="${book.title}"/></td>
            </tr>
            <tr>
                <td>Author: </td>
                <td><input type="text" name="author" value="${book.author}"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Submit"/>
                    <a href="bookList">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
    <jsp:include page="_footer.jsp"/>
</body>
</html>
