package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author israa ismaill
 */
@WebServlet(urlPatterns = {"/PassengerSignup"})
public class PassengerSignup extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        Connection conn = DBUtil.getConnection();
        Statement Stmt = conn.createStatement();
        ResultSet RS = Stmt.executeQuery("SELECT * From passenger Where passenger.username=\'" + username + "\' OR passenger.email= \'" + email + "\' ");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();

        try (PrintWriter out = response.getWriter()) {

            if (!RS.isBeforeFirst()) {
                Stmt.executeUpdate("INSERT INTO passenger(username, password, email, phone)" + "VALUES (" + "'" + username + "'" + "," + "'" + password + "'" + "," + "'" + email + "'" + "," + "'" + phone + "'" + ")");
                response.sendRedirect("passengerLogin.jsp?msgReg=1");
            } else {
                if (RS.next()) {
                    if (RS.getString("username").equals(username)) {
                        response.sendRedirect("passengerSignup.jsp?existMsg=1");
                    } else {
                        response.sendRedirect("passengerSignup.jsp?existMsg=2");
                    }
                }
            }
        } finally {
            DBUtil.cleanUpResources(conn, RS, Stmt);
            out.close();
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on
    // the + sign on the left to edit the code.">
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
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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
