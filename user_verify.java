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
@WebServlet(urlPatterns = {"/user_verify"})
public class user_verify extends HttpServlet {
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        PrintWriter out = response.getWriter();
        String tid=request.getParameter("tid");
        String un=request.getParameter("un");
      
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
            ResultSet ps=st.executeQuery("select * from user_table where tid='" + tid + "'");
            
            if(ps.next())
            {
                if(tid.equals(ps.getString(1)) && un.equals(ps.getString(2)))
                {
                    ResultSet rs = st.executeQuery("select * from track_table where tid='" + tid + "'");
            
                    if (rs.next())
                    {
                        out.println(rs.getString(1));
                        out.println(rs.getString(2));
                        out.println(rs.getString(3));
                        out.println(rs.getString(4));
                        out.println(rs.getString(5));
                        
                        out.println(rs.getString(6));
                        out.println(rs.getString(7));
                        out.println(rs.getString(8));
                        out.println(rs.getString(9));
                    }
                    else
                    {
                        response.sendRedirect("user_login1.html");
                    }
                }    
            }
            else
            {
               response.sendRedirect("user_login1.html");
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
