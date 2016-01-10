<%-- 
    Document   : adminAddTrainJSP
    Created on : Dec 22, 2015, 9:28:16 PM
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
        <title>Add Train</title>
        <link rel="stylesheet" href="styles.css">
        <script language="javascript" type="text/javascript" src="datetimepicker.js"></script>
        
        <script>
            function checkForm(form)
            {
                
                if (form.trainCapacity.value == "") {
                    alert("Error: Please Enter Train Capacity!");
                    form.trainCapacity.focus();
                    return false;
                }
                
            }
        </script>


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
                <li class="active"><a href='adminAddTrainJSP.jsp'><span>Add Train</span></a></li>
                <li><a href='adminAddTrip'><span>Add Trip</span></a></li>
                <li><a href='adminUpdateTrainJSP.jsp'><span>Update Train</span></a></li>
                <li><a href='adminUpdateTripJSP.jsp'><span>Update Trip</span></a></li>
                <li><a href='adminDeleteTrain.jsp'><span>Delete Train</span></a></li>
                <li><a href='adminDeleteTrip.jsp'><span>Delete Trip</span></a></li>
                <li><a href='Logout'><span>logout</span></a></li>
            </ul>
        </div>
        
        <%
            Connection conn = null;
            ResultSet rs = null;
            Statement stmt = null;
            String SQL = "SELECT train.model from train GROUP BY model";
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);

        %>
        
        <div>
            <form class="formInput2" onsubmit= "return checkForm(this);" action="adminAddTrain" method="POST"> 
                <table cellspacing="5" border="0">
                    <tr>
                        <td><h3>Train Model:</h3></td>
                        <td><select name="trainModel">
                <%  
                    while (rs.next()) {
                        out.println(" <option value=\"" + rs.getString("model") + "\">" + rs.getString("model") + "</option>");
                    }
                %>
                            </select></td>
                    </tr>
                    <tr>
                        <td><h3>Train Capacity:</h3></td>
                        <td><input type="text" title="Train Capacity" name="trainCapacity"></td>
                    </tr>
                <br>
                <br>
                <tr>
                <td></td>
                <td><input type="submit" value="Add Train" name="addTrainButton"/></td>
                </tr>
                </table>
                            
            </form>
        </div>
    </body>
</html>
