/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.actions;

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
import javax.servlet.http.HttpSession;
import com.efficient.db.DBConnection;

/**
 *
 * @author IBN5
 */
@WebServlet(name = "userLoginCheck", urlPatterns = {"/userLoginCheck"})
public class userLoginCheck extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String username=request.getParameter("username");
            String pass=request.getParameter("password");
            
          Connection con= DBConnection.getConnection();
          Statement st=con.createStatement();
          ResultSet rt=st.executeQuery("select * from datauser where username='"+username+"'");
          if(rt.next()){
               int id=rt.getInt("id");
              System.out.println(id);
              String p=rt.getString("password");
              String activate=rt.getString("status");
              String name=rt.getString("username");
              
              if(pass.equalsIgnoreCase(p)){
                  if(activate.equalsIgnoreCase("Authorized")){
                      HttpSession user=request.getSession();
                      user.setAttribute("name", name);
                      user.setAttribute("id", id);
                      user.setAttribute("username", username);
                      
                      response.sendRedirect("User_home.jsp");
                  }
                  else{
                      out.println("Your not Yet Activeted");
                  }
              }
              else{
                  out.println("incorrect password");
              }
          }
          else{
              out.println("Incorrect username");
          }
        }
        catch(Exception e){
            out.println(e);
        } finally {            
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
