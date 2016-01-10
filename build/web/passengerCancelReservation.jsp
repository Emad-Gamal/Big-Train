<%-- 
    Document   : passengerCancelReservation
    Created on : Dec 23, 2015, 8:38:06 PM
    Author     : Emad
--%>

<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="Main.DBUtil" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cancel Reservation</title>
        <link rel="stylesheet" href="styles.css">
        <script language="javascript" type="text/javascript" src="datetimepicker.js"></script>
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
                <li class="active"><a href='passengerCancelReservation.jsp'><span>Cancel Reservation</span></a></li>
                <li><a href='Logout'><span>Logout</span></a></li>
            </ul>
        </div>
        <form action="confirmCancelReservation.jsp" method="POST">
            <table class="formTable" border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Source</th>
                        <th>Destination</th>
                        <th>Date</th>
                        <th>Seats</th>
                        <th>Total Cost</th>
                        <th>select</th>
                    </tr>
                </thead>
                <%
                    String passengerID = (String)session.getAttribute("ID");
                    String SQL = "select * FROM reservation WHERE passengerID= " + passengerID;
                    Connection conn = null;
                    ResultSet rs = null;
                    Statement stmt = null;
                    Statement stmt2 = null;
                    conn = DBUtil.getConnection();
                    stmt = conn.createStatement();
                    stmt2 = conn.createStatement();
                    rs = stmt.executeQuery(SQL);
                    boolean empty = true;
                    while (rs.next()) {

                        empty = false;
                        String id = rs.getString("id");
                        String seats = rs.getString("numberOfSeats");
                        String trip = rs.getString("tripID");
                        String total = rs.getString("totalCost");

                        SQL = "SELECT * FROM trip WHERE id= " + trip;
                        ResultSet rs2 = stmt2.executeQuery(SQL);
                        String source = "";
                        String destination = "";
                        String date = "";

                        if (rs2.next()) {
                            source = rs2.getString("sourceID");
                            destination = rs2.getString("destinationID");
                            date = rs2.getString("date");
                        }
                        SQL = "SELECT * FROM lut_city where id=" + source;
                        rs2 = stmt2.executeQuery(SQL);
                        if (rs2.next()) {
                            source = rs2.getString("name");
                        }
                        SQL = "SELECT * FROM lut_city where id=" + destination;
                        rs2 = stmt2.executeQuery(SQL);
                        if (rs2.next()) {
                            destination = rs2.getString("name");
                        }

                %>
                <tbody>
                    <tr>
                        <td><%=id%></td>
                        <td><%=source%></td>
                        <td><%=destination%></td>
                        <td><%=date%></td>
                        <td><%=seats%></td>
                        <td><%=total%></td>
                        <td><input type="radio" name="selected" value="<%=id%>" /></td>
                    </tr>
                </tbody>
                <%
                    }
                %>
            </table>

            <%if (empty) {%>
            <h2 style="margin-left: 600px;" >No Reservation Found</h2>
            <input style="margin-left: 630px;margin-top: 50px;" onclick="javascript:history.go(-1)" type="button" value="Back"/>
            <%} else {%>
            
            <input style="margin-left: 630px;margin-top: 50px;" type="submit" value="cancel"/>    
            <input style="margin-left: 40px;margin-top: 50px;" onclick="javascript:history.go(-1)" type="button" value="Back"/>
            <%}

                DBUtil.cleanUpResources(
                        null, rs, stmt);
                DBUtil.cleanUpResources(conn, rs, stmt2);
            %>
        </form>
    </body>
</html>
