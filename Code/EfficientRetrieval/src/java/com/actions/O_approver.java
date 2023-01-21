
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


@WebServlet(name = "O_approver", urlPatterns = {"/O_approver"})

public class O_approver extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        String id = request.getParameter("id");
        String publicKey=request.getParameter("publicKey");
        
        
        System.out.println(id);
        //java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        Connection con = null;
        PreparedStatement ps = null;
        try {
        con = DBConnection.getConnection();
        String query = "update dataowner set status='Authorized',publicKey='"+publicKey+"' where id='"+id+"'";
   
        ps = con.prepareStatement(query);
        
        int no = ps.executeUpdate();
        if(no > 0){
        response.sendRedirect("activateowner.jsp?msg=success");
        }else{
            
        response.sendRedirect("activateowner.jsp?msg=faild");
        }
        } catch(Exception e){
            System.out.println("Error at User"+e.getMessage());
        response.sendRedirect("activateowner.jsp?msg=faild");
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
