<%-- 
    Document   : adminUpdateTripJSP.jsp
    Created on : Dec 24, 2015, 12:38:10 AM
    Author     : bilal
--%>

<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="Main.DBUtil"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Trip</title>
        <link rel="stylesheet" href="styles.css">
        <script language="javascript" type="text/javascript" src="datetimepicker.js"></script>

    </head>
    <body>
        <%
            PrintWriter out1 = response.getWriter();
            String username = (String)session.getAttribute("username");
            if(username==null){
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
                <li class="active"><a href='adminUpdateTripJSP.jsp'><span>Update Trip</span></a></li>
                <li><a href='adminDeleteTrain.jsp'><span>Delete Train</span></a></li>
                <li><a href='adminDeleteTrip.jsp'><span>Delete Trip</span></a></li>
                <li><a href='Logout'><span>Logout</span></a></li>
            </ul>
        </div>
        
        <%
            Connection conn = null;
            ResultSet rs = null;
            Statement stmt = null;
            String SQL = "SELECT * from trip";
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);

        %>
        
        <div>
            <form class="formInput2" action="TripDataRetriever" method="POST">
                <table cellspacing="5" border="0">
                    <tr> 
                        <td><h3>Trip ID:</h3></td>
                        <td>
                            <select name="tripID">
                            <%  
                                while (rs.next()) {
                                    out.println(" <option value=\"" + rs.getString("id") + "\">" + rs.getString("id") + "</option>");
                                }
                            %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" id="retrieveTripata" value="Show Trip Data" name="retrieveTripData"/></td>
                    </tr>
                </table>
            </form>
        </div>
        
        
        
    </body>
</html>

