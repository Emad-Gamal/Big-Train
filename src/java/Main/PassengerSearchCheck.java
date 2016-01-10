package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 *
 * @author Emad
 */
@WebServlet(urlPatterns = {"/passengerSearchCheck"})
public class PassengerSearchCheck extends HttpServlet {

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
        
        PrintWriter out1 = response.getWriter();
            String username = (String)request.getSession().getAttribute("username");
            if(username==null){
                out1.println("<script type=\"text/javascript\">");
                out1.println("alert('You Are Not logged in!');");
                out1.println("location='index.html';");
                out1.println("</script>");
            }
        
        String departDate = request.getParameter("departDate");

        if (departDate != null && departDate != "") {
            departDate = DBUtil.Date(departDate);
        }
        String source = request.getParameter("source");
        String destinaion = request.getParameter("destinaion");
        String msg = request.getParameter("msg");
        HttpSession session = request.getSession(true);
        
        
        session.setAttribute("seats", request.getParameter("seats"));
        int seats = Integer.parseInt(request.getParameter("seats"));

        String sqlSource = "SELECT * FROM lut_city where id=\'" + source + "\'";
        String sqlDestination = "SELECT * FROM lut_city where id=\'" + destinaion + "\'";
        String sqlTrip = "";
        if (departDate != null && departDate != "") {
            sqlTrip = "SELECT * from trip WHERE sourceID=\'" + source
                    + "\' AND destinationID=\'" + destinaion + "\' AND date=\'" + departDate + "\' ";
        } else {
            sqlTrip = "SELECT * from trip WHERE sourceID=\'" + source
                    + "\' AND destinationID=\'" + destinaion + "\' ";

        }
        Connection conn = null;
        ResultSet rs = null;
        ResultSet cities = null;
        boolean empty = true;
        
        Statement stmt = null;
        Statement stmtCities=null;
        conn = DBUtil.getConnection();
        
        stmt = conn.createStatement();
        stmtCities=conn.createStatement();
        
        sqlSource = "SELECT * FROM lut_city where id=" + source;
        cities = stmtCities.executeQuery(sqlSource);
        if (cities.next()) {
            source = cities.getString("name");
        }
        sqlDestination = "SELECT * FROM lut_city where id=" + destinaion;
        cities = stmtCities.executeQuery(sqlDestination);
        if (cities.next()) {
            destinaion = cities.getString("name");
        }

        try (PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Select Trip</title>");
            out.print("<link rel=\"stylesheet\" href=\"styles.css\">\n"
                    + "        <script language=\"javascript\" type=\"text/javascript\" src=\"datetimepicker.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("        <div id='cssmenu'>\n"
                    + "            <ul>\n"
                    + "                \n"
                    + "                <li class=\"active\"><a href='passengerSearch.jsp'><span>Search Reservation</span></a></li>\n"
                    + "                <li><a href='passengerCancelReservation.jsp'><span>Cancel Reservation</span></a></li>\n"
                    + "                <li><a href='Logout'><span>Logout</span></a></li>"
                    + "            </ul>\n"
                    + "        </div>");
            if (request.getParameter("msg") != null) {
                out.println("<h3 style=\"color:red\">Please Select Trip</h3>");
            }
            out.println("<h2>" + departDate + "</h2>");
            out.println("<form class=\"formTable\" method=\"POST\" action=\"passengerPayment.jsp\" >\n"
                    + "            <table align=\"center\" border=\"5\" style=\"margin-top:100px\">\n"
                    + "                <thead>\n"
                    + "                    <tr>\n"
                    + "                        <th>Trip ID</th>\n"
                    + "                        <th>Source</th>\n"
                    + "                        <th>Destination</th>\n"
                    + "                        <th>Date</th>\n"
                    + "                        <th>Departure Time</th>\n"
                    + "                        <th>Arrival Time</th>\n"
                    + "                        <th>Price</th>\n"
                    + "                        <th>Seats</th>\n"
                    + "                        <th>Total</th>\n"
                    + "                        <th>Select</th>\n"
                    + "                    </tr>\n"
                    + "                </thead>\n");

            rs = stmt.executeQuery(sqlTrip);

            if (rs.isBeforeFirst()) {
                empty = false;
                out.println("<tbody>\n");
                while (rs.next()) {

                    out.println("                    <tr>\n"
                            + "                        <td>" + rs.getString("id") + "</td>\n"
                            + "                        <td>" + source + "</td>\n"
                            + "                        <td>" + destinaion + "</td>\n"
                            + "                        <td>" + rs.getString("date") + "</td>\n"
                            + "                        <td>" + rs.getString("departureTime") + "</td>\n"
                            + "                        <td>" + rs.getString("arrivalTime") + "</td>\n"
                            + "                        <td>" + rs.getString("pricePerPerson") + "</td>\n"
                            + "                        <td>" + seats + "</td>\n"
                            + "                        <td>" + Integer.parseInt(rs.getString("pricePerPerson")) * seats + "</td>\n"
                            + "                        <td><input type=\"radio\" name=\"selectTrip\" value=\"" + rs.getString("id") + "\"/></td>\n"
                            + "                    </tr>");

                }
                out.println(" </tbody>\n");

            }
            out.println("</table>\n");
            if (empty) {
                out.println("<h2 style=\"margin-left: 600px;\" >No Trips Found</h2>");

                out.println("<input style=\"margin-left: 630px;margin-top: 50px;\"onclick=\"javascript:history.go(-1)\" type=\"button\" value=\"Back\"/>");
            } else {
                out.println("<input style=\"margin-left: 630px;\" type=\"submit\" value=\"Pay\"/>");
                out.println("<input style=\"margin-left: 40px;margin-top: 50px;\"onclick=\"javascript:history.go(-1)\" type=\"button\" value=\"Back\"/>");
            }

            out.println("</form></body>");
            out.println("</html>");

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
            Logger.getLogger(PassengerSearchCheck.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PassengerSearchCheck.class.getName()).log(Level.SEVERE, null, ex);
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
