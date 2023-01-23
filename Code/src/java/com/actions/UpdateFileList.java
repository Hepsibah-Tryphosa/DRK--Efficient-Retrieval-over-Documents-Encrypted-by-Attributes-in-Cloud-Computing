package com.actions;

import com.efficient.db.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UpdateFileList", urlPatterns = {"/UpdateFileList"})

public class UpdateFileList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("id");
        String username = (String) session.getAttribute("username");
        List<String> filenames = (List<String>) request.getAttribute("filename");
        String status = "waiting";
        String pubkey = request.getParameter("uniqueys");
        System.out.println(id);
        System.out.println(username);

        //java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            for (String ee : filenames) {
                System.out.println(ee);
                String query = "insert into request(userid,username,filename,status,pubkeys) values(?,?,?,?,?)";
                ps = con.prepareStatement(query);
                ps.setInt(1, id);
                ps.setString(2, username);
                ps.setString(3, ee);
                ps.setString(4, status);
                ps.setString(5, pubkey);
                ps.executeUpdate();
            }
            response.sendRedirect("search.jsp?msg=success");

        } catch (Exception e) {
            System.out.println("Error at User" + e.getMessage());
            response.sendRedirect("search.jsp?msg=faild");
        } finally {
            out.close();
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
