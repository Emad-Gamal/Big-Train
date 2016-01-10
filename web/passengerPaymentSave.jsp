<%-- 
    Document   : passengerPaymentSave
    Created on : Dec 23, 2015, 11:54:30 AM
    Author     : Emad
--%>

<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="Main.DBUtil" %>
<%@page import="Main.MailClient" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment</title>
        <link rel="stylesheet" href="styles.css">
        <script language="javascript" type="text/javascript" src="datetimepicker.js"></script>
    </head>
    <body onload="ajaxFunction();">
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
            ResultSet rs1 = null;
            Statement stmt2 = null;

//            String SQL = "INSERT  INTO (tripID,passengerID,paymentCardNumber,numberOfSeats,totalCost)  reservation "
//                    + " VALUES(?,?,?,?,?)";
            String passengerID = (String) session.getAttribute("ID");

            int id = Integer.parseInt(passengerID);

            String total = (String) session.getAttribute("totalPrice");
            String selectTrip = (String) session.getAttribute("selectTrip");
            String paymentCardNumber = request.getParameter("paymentCardNumber");
            String seatsNumber = (String) session.getAttribute("seats");

            String SQL = "INSERT  INTO reservation (tripID,passengerID,paymentCardNumber,numberOfSeats,totalCost)   "
                    + " VALUES(" + selectTrip + "," + passengerID + ",\'" + paymentCardNumber + "\'," + seatsNumber + "," + total + ")";

            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(SQL);
            stmt2 = conn.createStatement();
            rs1 = stmt2.executeQuery("SELECT passenger.email FROM passenger where passenger.id=" + id + " ");
            String email = " ";
            rs1.beforeFirst();
            while (rs1.next()) {
                email = rs1.getString("email");
            }

            new MailClient().mail(email);

            DBUtil.cleanUpResources(conn, rs, stmt);


        %>
    <dev>
        <center>
            <br><br>
            <h2>Trip Reserved</h2>
            <h2>A Confirmation Email has been sent to your Mail</h2>
            <h2>Thank you.</h2>
            <br>
            <br>
            <div id="time" ></div>
        </center>
    </dev>




    <script language="javascript" type="text/javascript">
	function ajaxFunction(){
		var xmlHttp;
		try{
			xmlHttp=new XMLHttpRequest();
		}
		catch (e){			
		}

		xmlHttp.onreadystatechange=function(){
			if(xmlHttp.readyState==4){
				document.getElementById('time').innerHTML = "This Reservation has been made on:</b>  <font size='5'>" + xmlHttp.responseText + "</font>";
			}
		}

		var url = "time.jsp";
		xmlHttp.open("GET",url,true);
		xmlHttp.send(null);
	}
</script>

</body>
</html>
