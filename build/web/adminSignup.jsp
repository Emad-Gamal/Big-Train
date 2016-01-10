<%-- 
    Document   : adminSignup
    Created on : Dec 22, 2015, 3:08:40 PM
    Author     : israa ismaill
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Admin Sinup</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles.css">

        <script>
            function checkForm(form)
            {
                if (form.username.value == "") {
                    alert("Error: Username cannot be blank!");
                    form.username.focus();
                    return false;
                }

                if (form.password.value == "") {
                    alert("Error: password cannot be blank!");
                    form.password.focus();
                    return false;
                }
                if (form.password.value.length<5) {
                    alert("Error: password cannot be less than 5 characters");
                    form.password.focus();
                    return false;
                }
                if (form.phone.value == "") {
                    alert("Error: phone number cannot be blank!");
                    form.phone.focus();
                    return false;
                }
                re = /^\d+$/;
                if (!re.test(form.phone.value)) {
                    alert("Error: phone should be digits only");
                    form.phone.focus();
                    return false;
                }
                if (form.phone.value.length < 11) {
                    alert("Error: Phone number must be eleven characters!");
                    form.phone.focus();
                    return false;
                }

                re = /[0-9]/;
                if (!re.test(form.password.value)) {
                    alert("Error: password must contain numbers");
                    form.password.focus();
                    return false;
                }
                

            }
        </script>

    </head>
    <body>
        <div  id="cssmenu">
            <ul>
                <li style="margin-left: 45%"><span>Admin SignUp</span></li>

            </ul>
        </div>
    <center>
        <div>
            <br><br>
            <%
                if (request.getParameter("existMsg") != null) {
                    out.print("<h2 style=\"color:red\">User Name Already Exist</h2>");
                }
            %>


            <form onsubmit= "return checkForm(this);" action="AdminSinup">
                Username:  <input type="text" name="username" value="" /><br><br>
                Password:  <input type="password" name="password" value="" /><br><br>
                Phone Number: <input type="text" name="phone" value="" /><br><br>
                <input type="submit" value="Sign up"/><br><br>
                <a href="adminLogin.jsp">if you are registered please login here</a>
            </form>
        </div>
    </center> 

</body>
</html>
