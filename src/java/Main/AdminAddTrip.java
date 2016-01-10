package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bilal
 */
@WebServlet(urlPatterns = {"/adminAddTrip"})
public class AdminAddTrip extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            PrintWriter out1 = response.getWriter();
            String username = (String)request.getSession().getAttribute("username");
            if(username==null){
                out1.println("<script type=\"text/javascript\">");
                out1.println("alert('You Are Not logged in!');");
                out1.println("location='index.html';");
                out1.println("</script>");
            }
            
            Connection con = DBUtil.getConnection();
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
            System.out.println("connection established successfully...!!");

            ResultSet rs = st.executeQuery("SELECT train.id FROM train");
            ResultSet rs1 = st1.executeQuery("SELECT * FROM lut_city");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Admin Add Trip</title>");
            out.println("<link rel=\"stylesheet\" href=\"styles.css\">");
            out.println("<script language=\"javascript\" type=\"text/javascript\" src=\"datetimepicker.js\"></script>");

            out.println("<script>\n"
                    + "            function checkForm(form)\n"
                    + "            {\n"
                    + "                \n"
                    + "                if (form.departDate.value == \"\") {\n"
                    + "                    alert(\"Error: Please Enter Trip Date!\");\n"
                    + "                    form.departDate.focus();\n"
                    + "                    return false;\n"
                    + "                }\n"
                    + "                \n"
                    + "                if (form.departTime.value == \"\") {\n"
                    + "                    alert(\"Error: Please Enter Trip Departure Time!\");\n"
                    + "                    form.departTime.focus();\n"
                    + "                    return false;\n"
                    + "                }\n"
                    + "                if (form.arrivalTime.value == \"\") {\n"
                    + "                    alert(\"Error: Please Enter Trip Arrival Time Date!\");\n"
                    + "                    form.arrivalTime.focus();\n"
                    + "                    return false;\n"
                    + "                }\n"
                    + "                if (form.pricePerPerson.value == \"\") {\n"
                    + "                    alert(\"Error: Please Enter Trip Price Per Person!\");\n"
                    + "                    form.pricePerPerson.focus();\n"
                    + "                    return false;\n"
                    + "                }\n"
                    + "                if (form.source.value == form.destination.value) {\n"
                    + "                    alert(\"Error: Source And Destination Cannot be the same!\");\n"
                    + "                    form.destination.focus();\n"
                    + "                    return false;\n"
                    + "                }\n"
                    + "                \n"
                    + "                \n"
                    + "                \n"
                    + "            }\n"
                    + "        </script>");

            out.println("</head>");
            out.println("<body>");

            out.println("<div id='cssmenu'>\n"
                    + "            <ul>\n"
                    + "                <li><a href='adminAddTrainJSP.jsp'><span>Add Train</span></a></li>\n"
                    + "                <li class=\"active\"><a href='adminAddTrip'><span>Add Trip</span></a></li>\n"
                    + "                <li><a href='adminUpdateTrainJSP.jsp'><span>Update Train</span></a></li>\n"
                    + "                <li><a href='adminUpdateTripJSP.jsp'><span>Update Trip</span></a></li>\n"
                    +"<li><a href='adminDeleteTrain.jsp'><span>Delete Train</span></a></li>\n" +
"                <li><a href='adminDeleteTrip.jsp'><span>Delete Trip</span></a></li>"
                    + "                <li><a href='Logout'><span>logout</span></a></li>\n"
                    + "            </ul>\n"
                    + "            </div>");

            out.println("<div>");
            out.println("<form class=\"formInput3\" onsubmit= \"return checkForm(this);\" action=\"InsertTripToDB\" method=\"POST\">");
            out.println("<table cellspacing=\"5\" border=\"0\">");

            out.println("<tr>");
            out.println("<td><h3>Train ID:</h3></td>");
            out.println("<td>");
            out.println("<select name=\"trainID\">");
            while (rs.next()) {
                out.println("<option value=\"" + rs.getString(1) + "\">" + rs.getString(1) + "</option>");
            }
            out.println(" </select>");
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><h3>Source:</h3></td>");
            out.println("<td><select name=source>");
            while (rs1.next()) {
                out.println("<option value=\"" + rs1.getString("id") + "\">" + rs1.getString("name") + "</option>");
            }
            out.println("</select></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><h3>Destination:</h3></td>");
            out.println("<td><select name=destination>");
            rs1 = st1.executeQuery("SELECT * FROM lut_city");
            while (rs1.next()) {
                out.println("<option value=\"" + rs1.getString("id") + "\">" + rs1.getString("name") + "</option>");
            }
            out.println("</select></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><h3>Date:</h3></td>");
            out.println("<td><input id=\"depart\" name=\"departDate\" type=\"text\">");
            out.println("<a href=\"javascript:NewCal('depart','ddmmyyyy')\"><img src=\"images/cal.gif\" width=\"16\" height=\"16\" border=\"0\" alt=\"Pick a date\"></a></td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td><h3>Leaving Time:</h3></td>");
            out.println("<td><input type=\"time\" name=\"departTime\"></td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td><h3>Arrival Time:</h3></td>");
            out.println("<td><input type=\"time\" name=\"arrivalTime\"></td>");
            out.println("</tr>");

            out.println("<tr> ");
            out.println("<td><h3>Trip Price Per Person:</h3></td>");
            out.println("<td><input type=\"text\" title=\"pricePerPerson\" name=\"pricePerPerson\"></td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println(" <td></td>");
            out.println("<td><input type=\"submit\" id=\"addTrip\" value=\"Add Trip\" name=\"addTripButton\"/></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminAddTrip.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminAddTrip.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
