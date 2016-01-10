<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles.css">
        <title>Passenger Login</title>
        <script>
                function checkForm(form)
                {
                    if (form.username.value == "") {
                        alert("Error: UserName cannot be blank!");
                        form.username.focus();
                        return false;
                    }
                    if (form.password.value == "") {
                        alert("Error: Password cannot be blank!");
                        form.username.focus();
                        return false;
                    }

                }
        </script>

    </head>
    <body>
        <div  id="cssmenu">
            <ul>
                <li style="margin-left: 45%"><span>passenger Login</span></li>
                
            </ul>
        </div>
        <%
            if(request.getParameter("msg")!=null){
                out.print("<h2 style=\"color:red\">Wrong Username or Password</h2>");
            }
            if(request.getParameter("msgReg")!=null){
                if(request.getParameter("msg")!=null){
                    out.print("<br>");
                }
                out.print("<h2>Registerd Successfull Please Login</h2>");
            }
        %>
        <form onsubmit="return checkForm(this)" class="formInput" method="POST" action="passengerLoginCheck">
            <br><br>
            Username <input type="text" name="username" /><br><br>
            Password <input type="password" name="password" /><br><br>
            <input type="submit" value="Login" /><br><br>
            <a href="passengerSignup.jsp">if you are not registered please sign up here</a>

        </form>
   
    </body>
</html>
