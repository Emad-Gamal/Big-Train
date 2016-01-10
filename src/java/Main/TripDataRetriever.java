package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Main.DBUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bilal
 */
@WebServlet(urlPatterns = {"/TripDataRetriever"})
public class TripDataRetriever extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out1 = response.getWriter();
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            out1.println("<script type=\"text/javascript\">");
            out1.println("alert('You Are Not logged in!');");
            out1.println("location='index.html';");
            out1.println("</script>");
        }

        String StringTripID = request.getParameter("tripID");
        int tripID = Integer.parseInt(StringTripID);

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Connection con = DBUtil.getConnection();
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
            Statement st2 = con.createStatement();
            Statement st3 = con.createStatement();
            Statement st4 = con.createStatement();
            System.out.println("connection established successfully...!!");

            ResultSet rs = st.executeQuery("SELECT id, sourceID, destinationID, trainID, pricePerPerson, date, departureTime, arrivalTime FROM trip WHERE trip.id=" + tripID + " ");

            int sourceID = 0;
            while (rs.next()) {
                sourceID = Integer.parseInt(rs.getString("sourceID"));
            }

            rs.beforeFirst();
            int destinationID = 0;
            while (rs.next()) {
                destinationID = Integer.parseInt(rs.getString("destinationID"));
            }

            ResultSet rs3 = st3.executeQuery("SELECT lut_city.name FROM lut_city where id=\'" + sourceID + "\'");
            ResultSet rs4 = st4.executeQuery("SELECT lut_city.name FROM lut_city where id=\'" + destinationID + "\'");

            ResultSet rs1 = st1.executeQuery("SELECT * FROM train");

            ResultSet rs2 = st2.executeQuery("SELECT * FROM lut_city");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Update Trip</title>");
            out.println("<link rel=\"stylesheet\" href=\"styles.css\">");
            out.println("<script language=\"javascript\" type=\"text/javascript\" src=\"datetimepicker.js\"></script>");

            out.println("<script>\n"
                    + "            function checkForm(form)\n"
                    + "            {\n"
                    + "                \n"
                    + "                if (form.pricePerPerson.value == \"\") {\n"
                    + "                    alert(\"Error: Please Enter Trip Price Per Person!\");\n"
                    + "                    form.pricePerPerson.focus();\n"
                    + "                    return false;\n"
                    + "                }\n"
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
                    + "                <li><a href='adminAddTrip'><span>Add Trip</span></a></li>\n"
                    + "                <li><a href='adminUpdateTrainJSP.jsp'><span>Update Train</span></a></li>\n"
                    + "                <li class=\"active\"><a href='adminUpdateTripJSP.jsp'><span>Update Trip</span></a></li>\n"
                    + "<li><a href='adminDeleteTrain.jsp'><span>Delete Train</span></a></li>\n"
                    + "                <li><a href='adminDeleteTrip.jsp'><span>Delete Trip</span></a></li>"
                    + "                <li><a href='Logout'><span>logout</span></a></li>\n"
                    + "            </ul>\n"
                    + "        </div>");

            out.println("<div>");
            out.println("<form class=\"formInput4\" onsubmit= \"return checkForm(this);\" action=\"AdminUpdateTrip\" method=\"POST\">");
            out.println("<table cellspacing=\"5\" border=\"0\">");

            out.println("<tr>");
            out.println("<td><h3>Trip ID:</h3></td>");
            out.println("<td>");

            rs.beforeFirst();
            while (rs.next()) {
                out.println("<h3 name=\"tripID\">" + rs.getString(1) + "</h3>");
            }

            out.println("</td>");

            out.println("<td></td>");
            out.println("<td></td>");

            out.println("</tr>");

            out.println("<tr>");
            out.println("<td><h3>Source:</h3></td>");
            out.println("<td>");

            //rs.beforeFirst();
            while (rs3.next()) {
                out.println("<h3>" + rs3.getString("name") + "</h3>");
            }

            out.println("</td>");

            out.println("<td><h3>New Source:</h3></td>");
            out.println("<td><select name=source>");
            while (rs2.next()) {
                out.println("<option value=\"" + rs2.getString("id") + "\">" + rs2.getString("name") + "</option>");
            }
            out.println("</select></td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td><h3>Destination ID:</h3></td>");
            out.println("<td>");

            //rs.beforeFirst();
            while (rs4.next()) {
                out.println("<h3>" + rs4.getString("name") + "</h3>");
            }

            out.println("</td>");

            out.println("<td><h3>New Destination:</h3></td>");
            out.println("<td><select name=destination>");
            rs2.beforeFirst();
            while (rs2.next()) {
                out.println("<option value=\"" + rs2.getString("id") + "\">" + rs2.getString("name") + "</option>");
            }
            out.println("</select></td>");

            out.println("</tr>");

            out.println("<tr>");
            out.println("<td><h3>Train ID:</h3></td>");
            out.println("<td>");

            rs.beforeFirst();
            while (rs.next()) {
                out.println("<h3>" + rs.getString(4) + "</h3>");
            }

            out.println("</td>");

            out.println("<td><h3>New Train ID:</h3></td>");
            out.println("<td>");
            out.println("<select name=\"trainID\">");
            rs1.beforeFirst();
            while (rs1.next()) {
                out.println("<option value=\"" + rs1.getString(1) + "\">" + rs1.getString(1) + "</option>");
            }
            out.println(" </select>");
            out.println("</td>");

            out.println("</tr>");

            out.println("<tr>");
            out.println("<td><h3>Price Per Person:</h3></td>");
            out.println("<td>");

            rs.beforeFirst();
            while (rs.next()) {
                out.println("<h3>" + rs.getString(5) + "</h3>");
            }

            out.println("</td>");

            out.println("<td><h3>New Price Per Person:</h3></td>");
            out.println("<td><input type=\"text\" title=\"pricePerPerson\" name=\"pricePerPerson\"></td>");

            out.println("</tr>");

            out.println("<tr>");
            out.println("<td><h3>Date:</h3></td>");
            out.println("<td>");

            rs.beforeFirst();
            while (rs.next()) {
                out.println("<h3>" + rs.getString(6) + "</h3>");
            }

            out.println("</td>");

            out.println("<td><h3>New Date:</h3></td>");
            out.println("<td><input id=\"depart\" name=\"departDate\" type=\"text\">");
            out.println("<a href=\"javascript:NewCal('depart','ddmmyyyy')\"><img src=\"images/cal.gif\" width=\"16\" height=\"16\" border=\"0\" alt=\"Pick a date\"></a></td>");

            out.println("</tr>");

            out.println("<tr>");
            out.println("<td><h3>Departure Time:</h3></td>");
            out.println("<td>");

            rs.beforeFirst();
            while (rs.next()) {
                out.println("<h3>" + rs.getString(7) + "</h3>");
            }

            out.println("</td>");

            out.println("<td><h3>New Leaving Time:</h3></td>");
            out.println("<td><input type=\"time\" name=\"departTime\"></td>");

            out.println("</tr>");

            out.println("<tr>");
            out.println("<td><h3>Arrival Time:</h3></td>");
            out.println("<td>");

            rs.beforeFirst();
            while (rs.next()) {
                out.println("<h3>" + rs.getString(8) + "</h3>");
            }

            out.println("</td>");

            out.println("<td><h3>New Arrival Time:</h3></td>");
            out.println("<td><input type=\"time\" name=\"arrivalTime\"></td>");

            out.println("</tr>");

            out.println("<tr>");
            out.println(" <td></td>");
            out.println("<td><input type=\"submit\" id=\"updateTrip\" value=\"Update Trip\" name=\"updateTripButton\"/></td>");
            out.println("</tr>");

            out.println("</table>");
            rs.beforeFirst();
            while (rs.next()) {
                out.println("<input type=\"hidden\" name=\"tripID\" value=\"" + rs.getString(1) + "\">");
            }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
