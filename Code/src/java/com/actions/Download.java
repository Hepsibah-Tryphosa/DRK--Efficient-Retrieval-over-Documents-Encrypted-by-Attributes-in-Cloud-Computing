package com.actions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
import com.efficient.db.DBConnection;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

*/
/**
 *
 * @author Datapoint
 */
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
@WebServlet(name = "Download", urlPatterns = {"/Download"})
public class Download extends HttpServlet {
/*
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        int userid = (int) session.getAttribute("userid");
        List<String> filenames = (List<String>) request.getAttribute("filename");
        List fileList = new ArrayList<FileDownloadList>();
        String course = request.getParameter("course");
        String assignment = request.getParameter("assignment");
        java.sql.PreparedStatement pstmt = null;
        java.sql.Connection conn = null;
        ResultSet rs;
        String queryString;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            queryString = "select * from datafiles where id='" + userid + "'";
            pstmt = conn.prepareStatement(queryString);
            rs = pstmt.executeQuery(queryString);
            while (rs.next()) {
                // filenames.add(new FileDownloadList(rs.getString("username"),rs.getString("courseID"),rs.getString("assignmentID"),rs.getString("fileName"),rs.getString("mimeType"),(Blob) rs.getBlob("contents")));
            }
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=\"allfiles.zip\"");
            ZipOutputStream output = null;
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            try {
                output = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE));
                for (Iterator it = fileList.iterator(); it.hasNext();) {
                    Object filenames1 = it.next();
                    InputStream input = null;
                    try {
                        input = new BufferedInputStream(filenames1.getContents().getBinaryStream(), DEFAULT_BUFFER_SIZE);
                        output.putNextEntry(new ZipEntry(filenames1.getFileName()));
                        for (int length = 0; (length = input.read(buffer)) > 0;) {
                            output.write(buffer, 0, length);
                        }
                    }//try
                    catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                    }
                    output.closeEntry();
                } //for
            }//try
            finally {
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
*/
}
