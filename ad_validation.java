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
import java.util.*;

/**
 *
 * @author anits
 */
@WebServlet(urlPatterns = {"/ad_validation"})
public class ad_validation extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        PrintWriter out = response.getWriter();
        String s1=request.getParameter("un");
        String s2=request.getParameter("pwd");
        //out.println(s1);
        //out.println(s2);
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
            //out.print("select * from admin_table where un='" + s1 + "' <br/>");
            ResultSet rs = st.executeQuery("select * from admin_table where un='" + s1 + "'");
            
            if (rs.next())
            {     
                if (s1.equals(rs.getString(1)) && s2.equals(rs.getString(2))) 
                {
                    //request.setAttribute("un", s1);
                    out.print("login successful");
                    response.sendRedirect("adminPage1.html");
            
                }
                else 
                {
                    out.print("Sorry UserName or Password Error!");
                }
            } 
            else
            {
                    out.println("Invalid User name");
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
