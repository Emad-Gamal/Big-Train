<%-- 
    Document   : confirmCancelReservation
    Created on : Dec 24, 2015, 11:47:17 AM
    Author     : Emad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="Main.DBUtil" %>
<%@page import="Main.MailClient" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cancel Reservation</title>
        <link rel="stylesheet" href="styles.css">
        <script language="javascript" type="text/javascript" src="datetimepicker.js"></script>
    </head>
    <body>
        <div id='cssmenu'>
            <ul>
                <li ><a href='passengerSearch.jsp'><span>Search Reservation</span></a></li>
                <li class="active"><a href='passengerCancelReservation.jsp'><span>Cancel Reservation</span></a></li>
                <li><a href='Logout'><span>Logout</span></a></li>
            </ul>
        </div>
        <%
            String reservationID = request.getParameter("selected");
            String userID = (String) session.getAttribute("ID");
            String SQL = "DELETE FROM reservation WHERE id= " + reservationID + " AND passengerID= " + userID;

            Connection conn = null;
            Statement stmt = null;

            ResultSet rs1 = null;
            Statement stmt2 = null;

            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            int x = stmt.executeUpdate(SQL);

            stmt2 = conn.createStatement();
            rs1 = stmt2.executeQuery("SELECT passenger.email FROM passenger where passenger.id=" + userID + " ");
            String email = " ";
            rs1.beforeFirst();
            while (rs1.next()) {
                email = rs1.getString("email");
            }

            new MailClient().cancellatiomMail(email);

            DBUtil.cleanUpResources(conn, null, stmt);
        %>
    <dev>
        <center>
            <br><br>
            <h2 >Reservation Canceled</h2>
            <h2 >A Cancellation email has been sent to your Mail. </h2>
        </center>
    </dev>
</body>
</html>
