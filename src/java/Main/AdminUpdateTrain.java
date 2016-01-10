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
@WebServlet(urlPatterns = {"/AdminUpdateTrain"})
public class AdminUpdateTrain extends HttpServlet {

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
            out.println("<title>Servlet AdminUpdateTrain</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminUpdateTrain at " + request.getContextPath() + "</h1>");
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
        
        String stringTainID = request.getParameter("trainID");
        int trainID = Integer.parseInt(stringTainID);
        //int trainID = 2;
        String trainModel = request.getParameter("trainModel");
        String StringTrainCapacity = request.getParameter("trainCapacity");
        int trainCapacity = Integer.parseInt(StringTrainCapacity);
        

        try {
            Connection con = DBUtil.getConnection();
            Statement st = con.createStatement();
            System.out.println("connection established successfully...!!");
            
            String updateTableSQL = "UPDATE train"
				+ " SET model="+"'"+trainModel+"'"+", capacity="+trainCapacity+" "
				+ " WHERE id="+trainID+"";
            
            st.executeUpdate(updateTableSQL);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('A train recod has been successfuly UPDATED');");
            out.println("location='adminUpdateTrainJSP.jsp';");
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
