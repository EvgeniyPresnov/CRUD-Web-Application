<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
</head>
<body>
    <form action="registration" method="post">
        <table>
            <tr>
                <td>Name: </td><td>
                <input type="text" name="name"> </td>
            </tr>

            <tr>
                <td>Password: </td>
                <td> <input type="password" name="password"> </td>
            </tr>

            <tr>
                <td>Re-Type password: </td>
                <td> <input type="password" name="repeatPassword"> </td>
            </tr>

            <tr>
                <td> <input type="submit" name="submit" value="register"> </td>
                <td> </td>
            </tr>
        </table>
    </form>
</body>
</html>
