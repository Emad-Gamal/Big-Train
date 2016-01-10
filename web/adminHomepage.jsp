<%-- 
    Document   : adminHomepage
    Created on : Dec 26, 2015, 12:15:52 AM
    Author     : bilal
--%>

<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin Home Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles.css">



    </head>
    <body>

        <%
            PrintWriter out1 = response.getWriter();
            String username = (String) session.getAttribute("username");
            if (username == null) {
                out1.println("<script type=\"text/javascript\">");
                out1.println("alert('You Are Not logged in!');");
                out1.println("location='index.html';");
                out1.println("</script>");
            }

        %>

        <div id='cssmenu'>
            <ul>
                <li><a href='adminAddTrainJSP.jsp'><span>Add Train</span></a></li>
                <li><a href='adminAddTrip'><span>Add Trip</span></a></li>
                <li><a href='adminUpdateTrainJSP.jsp'><span>Update Train</span></a></li>
                <li><a href='adminUpdateTripJSP.jsp'><span>Update Trip</span></a></li>
                <li><a href='adminDeleteTrain.jsp'><span>Delete Train</span></a></li>
                <li><a href='adminDeleteTrip.jsp'><span>Delete Trip</span></a></li>
                <li><a href='Logout'><span>Logout</span></a></li>
            </ul>
        </div>

        <div id="image2">
            <img height="600" width="1335" src="images/admin_train.jpg">
            <div id="demo"><h2>Welcome To Big Train. LTD.<br />Wherever you go, go with all your heart.</h2></div>
            <a onclick="loadDoc()" class="myButton1">See Also...</a>
            <a href="adminAddTrainJSP.jsp"  class="myButton">Add Train NOW</a>
        </div>

        <script>
            function loadDoc() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        document.getElementById("demo").innerHTML = xhttp.responseText;
                    }
                };
                xhttp.open("GET", "ajax_info.txt", true);
                xhttp.send();
            }
        </script>

    </body>
</html>

