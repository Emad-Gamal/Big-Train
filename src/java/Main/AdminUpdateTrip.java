package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emad
 */
@WebServlet(urlPatterns = {"/AdminUpdateTrip"})
public class AdminUpdateTrip extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminUpdateTrip</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminUpdateTrip at " + request.getContextPath() + "</h1>");
            out.println("</body>");
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
        //processRequest(request, response);
        
        PrintWriter out1 = response.getWriter();
            String username = (String)request.getSession().getAttribute("username");
            if(username==null){
                out1.println("<script type=\"text/javascript\">");
                out1.println("alert('You Are Not logged in!');");
                out1.println("location='index.html';");
                out1.println("</script>");
            }
            
        PrintWriter out = response.getWriter();
        String StringTripID = request.getParameter("tripID");
        int tripID = Integer.parseInt(StringTripID);
        
        String StringPricePerPerson = request.getParameter("pricePerPerson");
        int pricePerPerson = Integer.parseInt(StringPricePerPerson);
                
        String StringTainID = request.getParameter("trainID");
        int trainID = Integer.parseInt(StringTainID);
        
        String stringSource = request.getParameter("source");
        int source = Integer.parseInt(stringSource);
        String stringDestination = request.getParameter("destination");
        int destination = Integer.parseInt(stringDestination);
        
        String departDate = request.getParameter("departDate");
        
        
        String[] parts = departDate.split("-");
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];
        departDate=year+"-"+month+"-"+day;
        
  
        String departTime = request.getParameter("departTime");
        String arrivalTime = request.getParameter("arrivalTime");
        
        try {
            Connection con = DBUtil.getConnection();
            Statement st = con.createStatement();
            System.out.println("connection established successfully...!!");
            
            String updateTableSQL = "UPDATE trip"
				+ " SET sourceID="+source+", destinationID="+destination+", trainID="+trainID+", pricePerPerson="+pricePerPerson+", date="+"'"+departDate+"'"+", departureTime="+"'"+departTime+"'"+", arrivalTime="+"'"+arrivalTime+"'"+"  "
				+ " WHERE id="+tripID+"";
            
            st.executeUpdate(updateTableSQL);
            
            
            out.println("<script type=\"text/javascript\">");
            out.println("alert('A Trip reacord has been successfuly UPDATED');");
            out.println("location='adminUpdateTripJSP.jsp';");
            out.println("</script>");
            DBUtil.cleanUpResources(con, st);
        } catch (Exception e) {
            e.printStackTrace();
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
