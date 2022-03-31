package mezutaula;

import helper.db.MySQLdb;
import helper.info.MessageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainServlet extends HttpServlet {

    private MySQLdb mySQLdb;

    public MainServlet(){
        super();

        System.out.println("------> Entering init() MainServlet");
        mySQLdb = new MySQLdb();
        System.out.println("------> Exiting init() MainServlet");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("------> Entering doGet() MainServlet");


        HttpSession session = request.getSession(false); // saioa sortuta ez badago, saioa ez sortu
        if(session != null) {
            System.out.println("\tUser is logged in");

            // DB-an mezu berritik gorde behar den egiaztatu
            String message = request.getParameter("message");
            if(message != null) {
                // erabiltzaileak mezu bat bidali nahi du
                String username = (String) session.getAttribute("username");
                mySQLdb.setMessageInfo(message, username);
            }

            //mezuen zerrenda atera eta eskaeran atributu bezela erantsi
            // gero JSP-ak atributu hori eskaeratik atera dezan

            ArrayList<MessageInfo> messageList = mySQLdb.getAllMessages();
            request.setAttribute("messageList", messageList);
            request.setAttribute("inactive_interval", 20);
            System.out.println("\tRedirecting the user to viewMessages.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/viewMessages.jsp");
            rd.forward(request, response);

        }
        System.out.println("------> Exiting doGet() MainServlet");




//        response.setHeader("Cache-Control", "no-cache");
////        PrintWriter http_out = response.getWriter();
////        http_out.println("MainServlet-era heldu zara");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
