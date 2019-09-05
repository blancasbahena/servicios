/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.entity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.stp.entity.ArchivoBean;

/**
 *
 * @author Luis Blancas
 */
public class ArchivoDAO extends Conexion
{
    String querySelect   ="SELECT id, nombre, finicio, ffinal, fcreacion, "
            + "creacion, num_registros FROM cep_archivos where activo =1";
    String queryMax      ="select nextval ('sec_archivos') ID";
    String querySelectOne="SELECT id, nombre, finicio, ffinal, fcreacion, "
            + "creacion, num_registros FROM cep_archivos WHERE activo =1 and ID = ?";
    String queryUpdate   ="UPDATE cep_archivos set nombre=?, finicio=?, ffinal=?, fcreacion=?, "
            + "creacion=?, num_registros=? WHERE ID = ?";
    String queryUpdateLimpia   ="UPDATE cep_archivos set activo=0  WHERE  activo  =1 ";
    String queryDelete   ="DELETE FROM cep_archivos WHERE ID = ? ";
    String queryCreate   ="INSERT INTO cep_archivos (id,nombre,finicio,ffinal,fcreacion,"
            + "creacion,num_registros,activo,context) VALUES (?,?,?,?,?,?,?,1,?)";
    public ArchivoBean  update(int id,String nombre,String finicio,String ffinal,
            String fcreacion,int creacion,int num_registros )
    {
        ArchivoBean bean=null;
        int updateCont=0;
        System.out.println("updateRecord("+id+","+nombre+","+finicio+","+ffinal+","+fcreacion+","+
                creacion+","+num_registros+")");
        if(id>0)
        {
            
            Connection connection=null;
            try 
            {
                connection=get_connection();
                PreparedStatement ps=connection.prepareStatement(queryUpdate);
                ps.setString(1, nombre);
                ps.setString(2, finicio);
                ps.setString(3, ffinal);
                ps.setString(4, fcreacion);
                ps.setInt   (5, creacion);
                ps.setInt   (6, num_registros);
                ps.setInt   (7, id);
                updateCont=ps.executeUpdate();
                if(updateCont==1)
                {
                    bean  =  findById(id);
                }
                ps.close();
            }
            catch (Exception e) 
            {
                System.out.println(e);
            }
            finally
            {
                if(connection!=null)
                {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ArchivoDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        else
            System.out.println("Id invalida ");
        return bean;
    }
    public ArchivoBean  insert(String nombre,String finicio,String ffinal,
            String fcreacion,int creacion,int num_registros,String context )
    {
        ArchivoBean bean=null;
        int insertCont=0;
        System.out.println("insertRecord("+nombre+","+finicio+","+ffinal+","+fcreacion+","+
                creacion+","+num_registros+","+context+")");
        int id=getSecuence();
        if(id>0)
        {
            Connection connection=null;
            try 
            {
                connection=get_connection();
                PreparedStatement ps=connection.prepareStatement(queryCreate);
                ps.setInt   (1, id);
                ps.setString(2, nombre);
                ps.setString(3, finicio);
                ps.setString(4, ffinal);
                ps.setString(5, fcreacion);
                ps.setInt   (6, creacion);
                ps.setInt   (7, num_registros);
                ps.setString(8, context);
                insertCont=ps.executeUpdate();
                if(insertCont==1)
                {
                    bean  =  findById(id);
                }
                ps.close();
            }
            catch (Exception e) 
            {
                System.out.println(e);
            }
            finally
            {
                if(connection!=null)
                {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ArchivoDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        else
            System.out.println("Secuencia invalida "+queryMax);
        return bean;
    }
    public void  limpiar()
    {
        PreparedStatement ps=null;
        int cont=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            ps=connection.prepareStatement(queryUpdateLimpia);
            cont =ps.executeUpdate();
        }
        catch (Exception e) 
        {
            System.out.println(e);
        }
        finally
        {
            try {
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArchivoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public int  delete(int id)
    {
        PreparedStatement ps=null;
        ResultSet rs=null;
        System.out.println("deleteOne("+id+")");
        int cont=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            ps=connection.prepareStatement(queryDelete);
            ps.setInt(1, id);
            cont =ps.executeUpdate();
        }
        catch (Exception e) 
        {
            System.out.println(e);
        }
        finally
        {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArchivoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cont;
    }
    public ArchivoBean findById(int id)
    {
        PreparedStatement ps=null;
        ResultSet rs=null;
        System.out.println("getOne("+id+")");
        ArchivoBean obj=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            ps=connection.prepareStatement(querySelectOne);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next())
            {
                obj=new ArchivoBean();
                obj.setId(rs.getInt("id"));
                obj.setNombre(rs.getString("nombre"));
                obj.setFecha_pago_i(rs.getString("finicio"));
                obj.setFecha_pago_f(rs.getString("ffinal"));
                obj.setFecha_creacion(rs.getString("fcreacion"));
                obj.setCreado(rs.getInt("creacion"));
            }
        }
        catch (Exception e) 
        {
            System.out.println(e);
        }
        finally
        {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArchivoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return obj;
    }
    public List<ArchivoBean> findAll()
    {
        Statement st=null;
        ResultSet rs=null;
        System.out.println("getAll()");
        List<ArchivoBean> l=new ArrayList<>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            st=connection.createStatement();
            rs = st.executeQuery(querySelect);
            while(rs.next())
            {
                ArchivoBean obj=new ArchivoBean();
                obj.setId(rs.getInt("id"));
                obj.setNombre(rs.getString("nombre"));
                obj.setFecha_pago_i(rs.getString("finicio"));
                obj.setFecha_pago_f(rs.getString("ffinal"));
                obj.setFecha_creacion(rs.getString("fcreacion"));
                obj.setCreado(rs.getInt("creacion"));
                l.add(obj);
            }
        }
        catch (Exception e) 
        {
            System.out.println(e);
        }
        finally
        {
            try {
                rs.close();
                st.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArchivoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return l;
    }

    private int getSecuence()
    {
        int id=0;
        Statement st=null;
        ResultSet rs=null;
        System.out.println("getMax()");
        Connection connection=null;
        try 
        {
            connection=get_connection();
            st=connection.createStatement();
            rs=st.executeQuery(queryMax);
            if(rs.next())
            { 
                id = rs.getInt("ID");
            }
        }
        catch (Exception e) 
        {
            System.out.println(e);
        }
        finally
        {
            try {
                rs.close();
                st.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArchivoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

}
