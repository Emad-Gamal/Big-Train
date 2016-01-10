<%-- 
    Document   : passengerHomepage
    Created on : Dec 26, 2015, 12:42:23 AM
    Author     : bilal
--%>

<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles.css">
        <title>Passenger Home page</title>
    </head>
    <body>
        
        <%PrintWriter out1 = response.getWriter();
            String username = (String)request.getSession().getAttribute("username");
            if(username==null){
                out1.println("<script type=\"text/javascript\">");
                out1.println("alert('You Are Not logged in!');");
                out1.println("location='index.html';");
                out1.println("</script>");
            }
            
            %> 
        
        <div id='cssmenu'>
            <ul>

                <li ><a href='passengerSearch.jsp'><span>Search Reservation</span></a></li>
                <li><a href='passengerCancelReservation.jsp'><span>Cancel Reservation</span></a></li>
                <li><a href='Logout'><span>logout</span></a></li>
            </ul>
        </div>
        <div id="image">
            <img height="600" width="1335" src="images/pass_train.jpg">
            <h2>Welcome To Big Train. LTD.<br />Wherever you go, go with all your heart.</h2>
            <a href="passengerSearch.jsp" class="myButton2">Search For A Trip NOW</a>
        </div>
    </body>
</html>
