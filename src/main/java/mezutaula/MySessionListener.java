package mezutaula;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class MySessionListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public MySessionListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */

        System.out.println("\tSession destroyed");
        HttpSession session = se.getSession();
        String sessionID = session.getId();
        System.out.println("\tGetting user sessionID: "+ sessionID);

        ServletContext context = session.getServletContext();
        HashMap<String, String> loggedinUsers = (HashMap<String, String>) context.getAttribute("loggedin_users");
        System.out.println("\tLoggedin users: "+ loggedinUsers.toString());


        for (Map.Entry<String, String> entry : loggedinUsers.entrySet()){
            if (entry.getValue().equals(sessionID)){
                loggedinUsers.remove(entry.getKey());
                System.out.println("\tRemoving " + entry.getKey() + " from loggedin users");
                context.setAttribute("loggedin_users", loggedinUsers);
                System.out.println("\tLoggedin users: "+ loggedinUsers.toString());
                break;
            }
        }

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
