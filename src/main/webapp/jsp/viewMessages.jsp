<%@ page import="java.util.*,helper.info.*" %>
Created by IntelliJ IDEA.
User: gzuaz
Date: 18/03/2022
Time: 9:16
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<% ArrayList<MessageInfo> messageList = (ArrayList) request.getAttribute("messageList");
    int inactive_interval = (int) request.getAttribute("inactive_interval");

    String username = (String) session.getAttribute("username");
    ServletContext context = request.getServletContext();
    HashMap<String, String> loggedinUsers = (HashMap<String, String>) context.getAttribute("loggedin_users");
%>



<html>
    <head>
        <title>View Messages</title>
        <link href="/MezuTaula/css/styleSheet.css" rel="stylesheet">
    </head>

    <body>
        <header>
            <h1>A webapp to share short messages</h1>
            <h3>View Messages</h3>
        </header>
        <section>
            <font color="white">You are logged in as: </font>
            <%= username %>
        </section>

        <section>
            <script>
                var timeleft = <%= inactive_interval %>;
                var downloadTimer = setInterval(function() {
                    if(timeleft == 1){
                        clearInterval(downloadTimer);
                    }
                    document.getElementById("progressBar").value = <%= inactive_interval %> - timeleft;
                    timeleft -= 1;
                    }, 1000);
            </script>
            <font color="white">Session timeout: </font>
            <progress value="0" max="<%= inactive_interval %>" id="progressBar"></progress>
        </section>

        <section>
            <font color="white">Active users: </font>
            <% for(Map.Entry<String, String> entry : loggedinUsers.entrySet()) { %>
            <%= entry.getKey() %>
            <% } %>
        </section>
        <section>
            <form method="post" action="/MezuTaula/servlet/MainServlet">
                <table>
                    <tr>
                        <td>Message:</td>
                        <td><textarea id="message" name="message" cols="25" rows="3"></textarea></td>
                    </tr>
                </table>
            </form>
        </section>

        <section>
            <table>
                <tr>
                    <th>Username</th>
                    <th>Message</th>
                </tr>
                <% for (int i = 0; i < messageList.size(); i++) {
                    MessageInfo messageInfo = messageList.get(i);%>
                <tr>
                    <td> <%= messageInfo.getUsername() %></td>
                    <td> <%= messageInfo.getMessage() %></td>
                </tr>
                <% } %>

            </table>
        </section>

        <footer> Server Date: <%=new Date().toString()%>
            <script type="javascript">
                var fecha = new Date();
                document.write(" -- Client Date: ");
                document.write(fecha);
            </script>
        </footer>

    </body>
</html>

