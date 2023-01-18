package com.actions;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "FileDownloadList", urlPatterns = {"/FileDownloadList"})
public class FileDownloadList extends HttpServlet {

    FileDownloadList(String string, String string0, String string1, String string2, String string3, byte[] bytes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
 
  String[] filename = request.getParameterValues("filename");
  List list =  Arrays.asList(filename); 
  request.setAttribute("filename", list); 
 // response.sendRedirect("UpdateFileList");
 RequestDispatcher rd = request.getRequestDispatcher("/Download");
rd.forward(request, response);
 }
}