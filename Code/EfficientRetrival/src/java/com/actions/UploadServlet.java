/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.actions;
import com.efficient.db.DBConnection;
import com.efficient.util.EncryptionAlgoritham;
import com.efficient.util.GettingTermIndexing;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.catalina.connector.Response;

/**
 *
 * @author Ramu Maloth
 */
@MultipartConfig(maxFileSize = 161772150)
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = null;
        PreparedStatement ps = null, ps1 = null;
      
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        String username = request.getParameter("username");
        String publickey = request.getParameter("publickey");
       // String file = request.getParameter("file");
        System.out.println(username);
        HttpSession hs = request.getSession();
      
        String branch = hs.getAttribute("branch").toString();
        String department = hs.getAttribute("department").toString();
        String subdept = hs.getAttribute("subdept").toString();
        String jobrole = hs.getAttribute("jobrole").toString();        
        long filesize = 0;
        InputStream inputStream = null;
        InputStream is = null;
        //Part filePart = request.getPart("file");
       //String filename =filePart.getSubmittedFileName();
        StringBuffer totalData = new StringBuffer();
        
       // System.out.println("File name is  " + filename);
        Collection<Part> parts = request.getParts();
        
        Iterator<Part> iter = parts.iterator();
       
        while(iter.hasNext()) {            
            final Part filePart = iter.next();            
            readFileData(filePart,username,publickey,branch,department,subdept,jobrole);
           // totalData.append(data+" ");            
        }
       
        response.sendRedirect("fileupload.jsp?msg=success");
    }

    
   
    private void readFileData(Part filePart, String username, String publickey, String branch, String department, String subdept, String jobrole) {
       Connection con = null;
       PreparedStatement ps = null;
         InputStream is = null;
         StringBuffer buf = new StringBuffer();
         int repeatedFrequency = 0;
        int termFrequency = 0;
        StringBuffer wordIndexes = new StringBuffer();
        StringBuffer termFrequencyData = new StringBuffer();
         String data = null;
         String filename = filePart.getSubmittedFileName();
         if(filename!= null){
           try { 
            is = filePart.getInputStream();
            String str = " ";
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            if(is!=null){
                while((str=br.readLine()) != null){
                    buf.append(str+" ");
                }
            }
             data = buf.toString();
             
              data = data.replace("is", "");
              data = data.replace("was", "");
              data = data.replace("and", "");
              data = data.replace("there", "");
              data = data.replace("these", "");
        
               Map map = GettingTermIndexing.getDocumentIndexes(data);               
                Set set = map.entrySet();
        Iterator it = set.iterator();
        while(it.hasNext()){
        Map.Entry me = (Map.Entry)it.next();
          
        String value = (String)me.getValue();
        int valNo = Integer.parseInt(value);
        if(valNo == 1){
            String words = (String)me.getKey();
            String keys = (String)me.getValue();          
            wordIndexes.append(words+" ");
            termFrequencyData.append(words+" : "+keys+"\n");
            termFrequency+= Integer.parseInt(keys);
            
        }else{
        String keys = (String)me.getValue();
        repeatedFrequency+= Integer.parseInt(keys);
        }
        }
        String cipherData = EncryptionAlgoritham.encrypt(data);
        float trmFrnc = (float)termFrequency/repeatedFrequency;
        
        con = DBConnection.getConnection();
        String sqlQuery = "insert into datafiles(username,publickey,branch,deptment,subdepot,jobrole,filename,filedata,frequency,cipherdata,wordindexs) values(?,?,?,?,?,?,?,?,?,?,?)";
         ps = con.prepareStatement(sqlQuery);
         ps.setString(1, username);
         ps.setString(2, publickey);
        ps.setString(3, branch);
        ps.setString(4, department);
        ps.setString(5, subdept);
        ps.setString(6, jobrole);
        ps.setString(7, filename);
        ps.setString(8, data);
        ps.setFloat(9, trmFrnc);
        ps.setString(10, cipherData);
        ps.setString(11, wordIndexes.toString());
        int no = ps.executeUpdate();
        
        
           
            
        } catch (Exception ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
                
            } catch (IOException ex) {
                Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        
     }
    }
   
}
