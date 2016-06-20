/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anits
 */
@WebServlet(urlPatterns = {"/ad_update"})
public class ad_update extends HttpServlet {
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        PrintWriter out = response.getWriter();
        String tid=request.getParameter("tid");
        String status=request.getParameter("status");
      
        Connection con = null;
        try 
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try 
            {
                con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "raghu", "it26");
            } 
            catch (SQLException ex) 
            {
                out.println(ex);
            }
            Statement st = con.createStatement();
     
            int r = st.executeUpdate("update track_table set status='"+status+"' where tid='"+tid+"'");
            
            if(r>0)
                 response.sendRedirect("adminPage5.html");
            else
            {
                out.println("invalid trackid");
            }
        }
            
        catch (ClassNotFoundException e) 
        {
            out.println(e);
        }
        catch (SQLException e)
        {
            out.println(e);
        }
    }
}
