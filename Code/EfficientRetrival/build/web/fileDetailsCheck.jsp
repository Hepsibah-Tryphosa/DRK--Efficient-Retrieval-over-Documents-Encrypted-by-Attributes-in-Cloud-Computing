<%-- 
    Document   : filedetailscheck
    Created on : Aug 24, 2018, 1:26:04 PM
    Author     : DHARANI
--%>


<%@page import="com.efficient.util.mail_Send"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.efficient.db.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<%  
    com.efficient.utility.GenerateFileKey gpk = new com.efficient.utility.GenerateFileKey();
//String key = gpk.generateKey();
        String fileid=request.getParameter("fileid");
         System.out.println(fileid);
        String userid=request.getParameter("userid");
        System.out.println(userid);
       String filename=request.getParameter("filename");
       System.out.println(filename);
       //String pkey=request.getParameter("username");
       
       Connection con = null;
       Statement st = null;
       ResultSet rs = null;
        
          con = DBConnection.getConnection();
            Statement stm=con.createStatement();
           rs=stm.executeQuery("select fileid,userid,filename,pubkeys from request where fileid='"+fileid+"'");
        if(rs.next())
            {
                String fid=rs.getString("fileid");
                System.out.println(fid);
                String uid=rs.getString("userid");
                 System.out.println(uid);
                String name=rs.getString("filename");
                System.out.println(name);
                String pubkeys = rs.getString("pubkeys");
                //String filekey=rs.getString("filekey");
               // String publickey=rs.getString("publickey");
               // String fileid=rs.getString("fileid");
                
           if(filename.equals(name)  && fileid.equals(fid)&& userid.equals(uid))
            {   
                HttpSession user=request.getSession();
               // int usid =Integer.parseInt(user.getAttribute("id").toString());
                Statement stm1=con.createStatement();
                ResultSet rs1=stm1.executeQuery("select email from datauser where id='"+userid+"'");
                if(rs1.next()){
                    
                    String email=rs1.getString("email");
                    System.out.println(email);
                //GenerateOtp g=new GenerateOtp();
               String key = gpk.generateKey();
                Statement stm2=con.createStatement();
                int k=stm2.executeUpdate("update request set status='Accepted',SecretKey='"+key+"' where userid='"+userid+"' and filename = '"+name+"' ");
                if(k>0){
                mail_Send.sendMail("The Secret Key for the file "+filename+" is: "+key +" And this Common Public key "+pubkeys, "userid", email);
                session.setAttribute("fileid",""+fileid);
                response.sendRedirect("sendkey.jsp");
            }
                else{
                   System.out.println("error generating otp"); 
                    response.sendRedirect("sendkey.jsp?msg=otpfailed"); 
                }}
              else
              {
                System.out.println("invalid id");
                         response.sendRedirect("sendkey.jsp?msg=invalidid");   
                        }
            }  else
              {
                System.out.println("invalid details");
                         response.sendRedirect("sendkey.jsp?msg=invaliddetails");   
                        }
           }  else
             {
                System.out.println("invalid file id");
                  response.sendRedirect("sendkey.jsp?msg=fileid");   
              }
     %>
    </body>
</html>
