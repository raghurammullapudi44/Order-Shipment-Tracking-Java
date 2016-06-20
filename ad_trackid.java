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
@WebServlet(urlPatterns = {"/ad_trackid"})
public class ad_trackid extends HttpServlet {
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        PrintWriter out = response.getWriter();
        String pid=request.getParameter("pid");
        String tid=""+(int)((Math.random())*1000000)+1;
        String usid=request.getParameter("usid");
        String ord_date=request.getParameter("ord_date");
        String del_date=request.getParameter("del_date");
        String address=request.getParameter("address");
        String status=request.getParameter("status");
        String phno=request.getParameter("phno");
        String email=request.getParameter("email");     
        
        try 
        {
        Connection con = null;
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
            
            ResultSet rs = st.executeQuery("select * from track_table where tid='" + tid + "'");
            while(rs.next())
            {
                tid=""+(int)((Math.random())*1000000)+1;
                rs = st.executeQuery("select * from track_table where tid='" + tid + "'");
            }
            
            int s = st.executeUpdate("insert into user_table values('"+tid+"','"+usid+"')");
     
            int r = st.executeUpdate("insert into track_table values('"+tid+"','"+pid+"','"+usid+"','"+address+"','"+status+"','"+phno+"','"+email+"','"+del_date+"','"+ord_date+"')");
            
            if(r>0)
                 response.sendRedirect("adminPage4.html");
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
