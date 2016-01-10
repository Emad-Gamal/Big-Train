<%-- 
    Document   : passengerPayment
    Created on : Dec 23, 2015, 10:26:03 AM
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
        <title>Pay Trip</title>
        <link rel="stylesheet" href="styles.css">
        <script language="javascript" type="text/javascript" src="datetimepicker.js"></script>
        <script>
                function checkForm(form)
                {
                    if (form.paymentCardNumber.value == "") {
                        alert("Error: paymentCardNumber cannot be blank!");
                        form.username.focus();
                        return false;
                    }

                }
        </script>
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

                <li class="active"><a href='passengerSearch.jsp'><span>Search Reservation</span></a></li>
                <li><a href='passengerCancelReservation.jsp'><span>Cancel Reservation</span></a></li>
                <li><a href='Logout'><span>Logout</span></a></li>
            </ul>
        </div>
        <%
            String selectTrip=request.getParameter("selectTrip");
            if (selectTrip == null) {

                response.sendRedirect(request.getHeader("Referer") + "&msg=true");
            }
             
            
            session.setAttribute("selectTrip", selectTrip);
            String sqlTrip = "SELECT * from trip WHERE id= " + selectTrip;
            Connection conn = null;
            ResultSet rs = null;
            Statement stmt = null;
            
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlTrip);
            int y=-1;
            if (rs.next()) {
                y = Integer.parseInt((String) session.getAttribute("seats"))
                        * Integer.parseInt(rs.getString("pricePerPerson"));
            }
            session.setAttribute("totalPrice", Integer.toString(y));
            
                    
            DBUtil.cleanUpResources(conn, rs, stmt);
       

        %>


        <form onsubmit="return checkForm(this)" class="formInput" action="passengerPaymentSave.jsp" method="POST">
            <label for="paymentCardNumber">Payment Card Number</label>
            <input type="text" name="paymentCardNumber" />
            <br>
            <label>Total Price  <%=y%></label>
            <br>
            <input type="submit" value="Confirm">
            <input style="margin-left: 40px" onclick="javascript:history.go(-1)" type="button" value="Back"/>
        </form>
    </body>
</html>
