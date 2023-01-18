/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.actions;

import com.efficient.db.DBConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Datapoint
 */
public class DownloadData extends HttpServlet {

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
        String fileId = request.getParameter("fileid");
        String filename = request.getParameter("filename");
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try  {
            ServletContext context = getServletContext();
            int BUFFER_SIZE = 4096;
                // sets MIME type for the file download
                String mimeType = context.getMimeType(filename);
                if (mimeType == null) {        
                    mimeType = "application/octet-stream";
                }              
                 String fileData = null;
                 String sqlQuery = "select filedata from datafiles where filename = ?";
                 con = DBConnection.getConnection();
                 ps = con.prepareStatement(sqlQuery);
                 ps.setString(1, filename);
                 rs = ps.executeQuery();
                 rs.next();
                 fileData = rs.getString("filedata");
                // set content properties and header attributes for the response
                response.setContentType(mimeType);
                response.setContentLength(fileData.length());
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", filename);
                response.setHeader(headerKey, headerValue);
 
                // writes the file to the client
                OutputStream outStream = response.getOutputStream();
                 
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;               
               
                    outStream.write(fileData.getBytes());
              outStream.close();
        }catch(Exception ex){
            System.out.println("File Download Error "+ex.getMessage());
        }finally{
            try {
                rs.close();
                ps.close();
                con.close();
                
            } catch (Exception e) {
            }
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
