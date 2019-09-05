/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.entity.dao; 
import javax.naming.*;
import javax.sql.*;
import java.sql.*;

/**
 *
 * @author Luis Blancas
 */

public class Conexion 
{       
    //local get_connectionVariablesEntorno
    public Connection get_connection1() throws Exception
    { 
        String url = System.getenv ("data.source.url");  
        String usuario = System.getenv("data.source.usuario");  
        String password = System.getenv("data.source.password");   
        Connection connection=null;
        try {
            
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection(url,usuario,password);
        }
        catch (ClassNotFoundException | SQLException e) 
        {            
            System.out.println(e);
        }
        
        return connection;
    }
    public Connection get_connection() throws Exception
    {
        //String url="jdbc:postgresql://localhost:5432/seguridad?sslmode=require";
        String url="jdbc:postgresql://localhost:5432/spei5";
        String usuario="postgres";
        String password="";
        Connection connection=null;
        try {
            
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection(url,usuario,password);
            }
        catch (ClassNotFoundException | SQLException e) 
        {            
            System.out.println(e);
        }
        return connection;
    }
    //get_connectionDataSource
    public Connection get_connectionDataSource () throws Exception
    {
        DataSource ds=null;
        Connection conn=null;
        InitialContext cxt = new InitialContext();
        if ( cxt == null ) {
           throw new Exception("Uh oh -- no context!");
        }
        ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/postgres" );
        if ( ds == null ) {
           throw new Exception("Data source not found!");
        }
        
        if (ds != null)
        {
            conn = ds.getConnection();
            if (conn != null)
            {
                Statement stmt = conn.createStatement();
                ResultSet rst = stmt.executeQuery("select id from cep_usuario where id=1 ");
                if (rst.next())
                {
                    int id = rst.getInt("id");
                    System.out.println("Exito en contexto");
                }
                rst.close();
                stmt.close();
                //conn.close();
            }
        }
        return conn;
    }

}
