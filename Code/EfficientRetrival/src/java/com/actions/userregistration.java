package com.actions;
import com.efficient.db.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ramu Maloth
 */
@WebServlet(name = "userregistration", urlPatterns = {"/userregistration"})
public class userregistration extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String status="waiting";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String branch = request.getParameter("branch");
        String department = request.getParameter("department");
        String subdept = request.getParameter("subdept");
        String jobrole = request.getParameter("jobrole");
        
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        Connection con = null;
        PreparedStatement ps = null;
        try {
        con = DBConnection.getConnection();
        String query = "insert into datauser(username,password,email,mobile,branch,department,subdept,jobrole,status) values(?,?,?,?,?,?,?,?,?)";
        ps = con.prepareStatement(query);
        ps.setString(1,username);
        ps.setString(2,password);
        ps.setString(3,email);
        ps.setString(4,mobile);
        ps.setString(5,branch);
        
        ps.setString(6,department);
        ps.setString(7,subdept);
        ps.setString(8,jobrole);
        ps.setString(9,status);
        int no = ps.executeUpdate();
        if(no > 0){
        response.sendRedirect("DataOwnerRegister.jsp?msg=success");
        }else{
        response.sendRedirect("DataOwnerRegister.jsp?msg=faild");
        }
        } catch(Exception e){
            System.out.println("Error at Faculty registeer "+e.getMessage());
        response.sendRedirect("Registration.jsp?msg=faild");
        }finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
