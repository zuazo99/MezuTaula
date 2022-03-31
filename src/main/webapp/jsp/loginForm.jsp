<%--
  Created by IntelliJ IDEA.
  User: gzuaz
  Date: 18/03/2022
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Form</title>
    <link href="/MezuTaula/css/styleSheet.css" rel="stylesheet">
</head>
<body>
    <header>
        <h1>A webapp to share short messages</h1>
        <h3>Log in form</h3>
    </header>

    <section>
        <form method="post" action="/MezuTaula/servlet/LoginServlet">
            <table>
                <tr>
                    <td>Email</td>
                    <td><input name="email"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password"></td>
                </tr>
            </table>
            <button>Send</button>
        </form>
    </section>

    <footer>
        Web Systems - EUITI Bilbao
    </footer>
</body>
</html>
