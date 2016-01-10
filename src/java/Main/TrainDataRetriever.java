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
@WebServlet(urlPatterns = {"/TrainDataRetriever"})
public class TrainDataRetriever extends HttpServlet {

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
            String username = (String)request.getSession().getAttribute("username");
            if(username==null){
                out1.println("<script type=\"text/javascript\">");
                out1.println("alert('You Are Not logged in!');");
                out1.println("location='index.html';");
                out1.println("</script>");
            }
        
        String StringTrainID = request.getParameter("trainID");
        int trainID = Integer.parseInt(StringTrainID);
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Connection con = DBUtil.getConnection();
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
            System.out.println("connection established successfully...!!");
            
            ResultSet rs = st.executeQuery("SELECT id, model, capacity FROM train WHERE train.id="+trainID+" ");
            
            ResultSet rs1 = st1.executeQuery("SELECT model FROM train GROUP BY model");
            
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Update Train</title>");
            out.println("<link rel=\"stylesheet\" href=\"styles.css\">");
            out.println("<script language=\"javascript\" type=\"text/javascript\" src=\"datetimepicker.js\"></script>");
            
            
            out.println("<script>\n" +
"            function checkForm(form)\n" +
"            {\n" +
"                \n" +
"                if (form.trainCapacity.value == \"\") {\n" +
"                    alert(\"Error: Please Enter Train Capacity!\");\n" +
"                    form.trainCapacity.focus();\n" +
"                    return false;\n" +
"                }\n" +
"                \n" +
"            }\n" +
"        </script>");
            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<div id='cssmenu'>\n" +
"            <ul>\n" +
"                <li><a href='adminAddTrainJSP.jsp'><span>Add Train</span></a></li>\n" +
"                <li><a href='adminAddTrip'><span>Add Trip</span></a></li>\n" +
"                <li class=\"active\"><a href='adminUpdateTrainJSP.jsp'><span>Update Train</span></a></li>\n" +
"                <li><a href='adminUpdateTripJSP.jsp'><span>Update Trip</span></a></li>\n" +
                    "<li><a href='adminDeleteTrain.jsp'><span>Delete Train</span></a></li>\n" +
"                <li><a href='adminDeleteTrip.jsp'><span>Delete Trip</span></a></li>"+
"                <li><a href='Logout'><span>logout</span></a></li>\n" +
"            </ul>\n" +
"        </div>");

            out.println("<div>");
            out.println("<form class=\"formInput3\" onsubmit= \"return checkForm(this);\" action=\"AdminUpdateTrain\" method=\"POST\">");
            out.println("<table cellspacing=\"5\" border=\"0\">");
            
            out.println("<tr>");
            out.println("<td><h3>Train ID:</h3></td>");
            out.println("<td>");
            
            while (rs.next()) {
            out.println("<h3 name=\"trainID\">"+rs.getString(1)+"</h3>");
            }  
            
            out.println("</td>");
            out.println("</tr>");
            
                        
            out.println("<tr>");
            out.println("<td><h3>Train Model:</h3></td>");
            out.println("<td>");
            
            rs.beforeFirst();
            while (rs.next()) {
            out.println("<h3>"+rs.getString(2)+"</h3>");
            }  
            
            out.println("</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td><h3>Train Capacity:</h3></td>");
            out.println("<td>");
            
            rs.beforeFirst();
            while (rs.next()) {
            out.println("<h3>"+rs.getString(3)+"</h3>");
            }  
            
            out.println("</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>");
            out.println("<h3>New Train Model:</h3>");
            out.println("</td>");
            out.println("<td>");
            out.println("<select name=\"trainModel\">");
            while (rs1.next()) {
                out.println(" <option value=\"" + rs1.getString("model") + "\">" + rs1.getString("model") + "</option>");
            }
            out.println("</select>");
            out.println("</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>");
            out.println("<h3>New Train Capacity:</h3>");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" title=\"Train Capacity\" name=\"trainCapacity\">");
            out.println("</td>");
            out.println("</tr>");
            
            
            
            out.println("<tr>");
            out.println(" <td></td>");
            out.println("<td><input type=\"submit\" id=\"updateTrain\" value=\"Update Train\" name=\"updateTrainButton\"/></td>");
            out.println("</tr>");
            
            
            
            out.println("</table>");
            rs.beforeFirst();
            while(rs.next()){
            out.println("<input type=\"hidden\" title=\"Train ID\" name=\"trainID\" value=\"" + rs.getString(1) + "\">");
            }
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }catch (Exception e) {
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
