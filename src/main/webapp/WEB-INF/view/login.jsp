<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <form action="login" method="post">
        <table>
            <tr>
                <td>User Name: </td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>Password: </td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td><input type="submit" name="submit" value="Login"></td>
                <td><a href="registration"> Registration</a></td>
            </tr>
        </table>
    </form>
</body>
</html>
