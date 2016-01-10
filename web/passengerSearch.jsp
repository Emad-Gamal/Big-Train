
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="Main.DBUtil" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Passenger Search</title>
        <link rel="stylesheet" href="styles.css">
        <script language="javascript" type="text/javascript" src="datetimepicker.js"></script>

    </head>
    <body>
        <%PrintWriter out1 = response.getWriter();
            String username = (String) request.getSession().getAttribute("username");
            if (username == null) {
                out1.println("<script type=\"text/javascript\">");
                out1.println("alert('You Are Not logged in!');");
                out1.println("location='index.html';");
                out1.println("</script>");
            }

        %>        
        <div id='cssmenu'>
            <ul>

                <li class="active"><a href='passengerSearch.jsp'><span>Search Reservation</span></a></li>
                <li><a href='passengerCancelReservation.jsp'><span>Cancel Reservation</span></a></li>
                <li><a href='Logout'><span>Logout</span></a></li>
            </ul>
        </div>
        <%            Connection conn = null;
            ResultSet rs = null;
            Statement stmt = null;
            String SQL = "SELECT * from lut_city";
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);

        %>


        <form  action="passengerSearchCheck" method="GET" class="formInput">


            <div class="inputInline">
                <table cellspacing="5" border="0">
                    <tr>

                        <td><label for="source">Source:</label></td>
                        <td><select name="source">
                                <%                    while (rs.next()) {
                                        out.println(" <option value=\"" + rs.getString("id") + "\">" + rs.getString("name") + "</option>");
                                    }
                                %>
                            </select></td>
                    </tr>
                    <tr>
                        <td><label for="destinaion">Destination:</label></td>
                        <td><select name="destinaion">
                                <%
                                    rs.beforeFirst();
                                    while (rs.next()) {
                                        out.println(" <option value=\"" + rs.getString("id") + "\">" + rs.getString("name") + "</option>");
                                    }
                                %>
                            </select></td>
                    </tr>
                    <tr>
                        <td><label for="departDate" >Date:</label></td>
                        <td><input id="depart" name="departDate" type="text">
                            <a href="javascript:NewCal('depart','ddmmyyyy')">
                                <img src="cal.gif" width="16" height="16" border="0" alt="Pick a date">
                            </a></td>
                    </tr>
                    <tr>
                        <td><label for="seats">No. Seats:</label></td>
                        <td><select name="seats">
                                <%
                                    for (int i = 1; i < 11; i++) {
                                        out.println(" <option value=\"" + i + "\">" + i + "</option>");
                                    }
                                %>
                            </select></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Search"/></td>
                    </tr>
                </table>
            </div>
        </form>
        <%
            DBUtil.cleanUpResources(conn, rs, stmt);
        %>
    </body>
</html>
