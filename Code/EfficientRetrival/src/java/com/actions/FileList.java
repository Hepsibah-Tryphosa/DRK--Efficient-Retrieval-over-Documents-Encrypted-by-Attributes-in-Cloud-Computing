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
@WebServlet(name = "FileList", urlPatterns = {"/FileList"})
public class FileList extends HttpServlet {
 
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
 
  String[] filename = request.getParameterValues("filename");
  List list =  Arrays.asList(filename); 
  request.setAttribute("filename", list); 
  // response.sendRedirect("UpdateFileList");
  RequestDispatcher rd = request.getRequestDispatcher("/UpdateFileList");
  rd.forward(request, response);
 }
}