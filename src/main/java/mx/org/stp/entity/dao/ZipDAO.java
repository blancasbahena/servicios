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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.stp.entity.Zip;

/**
 *
 * @author Luis Blancas
 */
public class ZipDAO extends Conexion
{
    String querySelect   ="SELECT id, nombre, fcreacion, creacion, num_pdf, num_xml, num_pro "
            + "  FROM cep_zip where activo =1 ";
    String queryMax      ="select nextval ('sec_zip') ID";
    String queryUpdateLimpia   ="UPDATE cep_zip set activo=0  WHERE  activo  =1 ";
    String querySelectOne="SELECT id, nombre, fcreacion, creacion, num_pdf, num_xml, num_pro FROM cep_zip "
            + "WHERE activo =1 and  ID = ?";
    String queryUpdate   ="UPDATE cep_zip set nombre=?,   num_pdf=?, "
            + " num_xml=?, num_pro=? WHERE ID = ?";
    String queryDelete   ="DELETE FROM cep_zip WHERE ID = ? ";
    String queryCreate   ="INSERT INTO cep_zip (id, nombre, fcreacion, creacion, num_pdf, num_xml, num_pro, activo, context) "
            + " VALUES (?, ?, ?, ?, ?, ?, ?, 1, ?) ";
    String queryGraf="select  fcreacion,sum(num_pdf) num_pdf,sum(num_xml) num_xml,sum(num_pro) num_pro "
            + "  from  public.cep_zip group by fcreacion order by 1";
    public Zip  update(int id,String nombre, int num_pdf,int num_xml,int num_pro)
    {
        Zip bean=null;
        int updateCont=0;
        System.out.println("updateRecord("+id+","+nombre+","+num_pdf+","+
                num_xml+","+num_pro+")");
        if(id>0)
        {
            
            Connection connection=null;
            try 
            {
                connection=get_connection();
                PreparedStatement ps=connection.prepareStatement(queryUpdate);
                ps.setString(1, nombre);
                ps.setInt   (2, num_pdf);
                ps.setInt   (3, num_xml);
                ps.setInt   (4, num_pro);
                ps.setInt   (5, id);
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
                        Logger.getLogger(ZipDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        else
            System.out.println("Id invalida ");
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
    public Zip  insert(String nombre,int usuario, int num_pdf,int num_xml,int num_pro,String context)
    {
        Zip bean=null;
        int insertCont=0;
        System.out.println("insertRecord("+nombre+","+usuario+","+num_pdf+","+
                num_xml+","+num_pro+","+context+")");
        int id=getSecuence();
        if(id>0)
        {
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            Connection connection=null;
            try 
            {
                connection=get_connection();
                PreparedStatement ps=connection.prepareStatement(queryCreate);
                ps.setInt   (1, id);
                ps.setString(2, nombre);
                ps.setString(3, date);
                ps.setInt   (4, usuario);
                ps.setInt   (5, num_pdf);
                ps.setInt   (6, num_xml);
                ps.setInt   (7, num_pro);
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
                        Logger.getLogger(ZipDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        else
            System.out.println("Secuencia invalida "+queryMax);
        return bean;
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
                Logger.getLogger(ZipDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cont;
    }
    public Zip findById(int id)
    {
        PreparedStatement ps=null;
        ResultSet rs=null;
        System.out.println("getOne("+id+")");
        Zip obj=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            ps=connection.prepareStatement(querySelectOne);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next())
            {
                obj=new Zip();
                obj.setId(rs.getInt("id"));
                obj.setNombre(rs.getString("nombre"));
                obj.setFcreacion(rs.getString("fcreacion"));
                obj.setCreacion(rs.getInt("creacion")); 
                obj.setPdf(rs.getInt("num_pdf"));
                obj.setXml(rs.getInt("num_xml"));
                obj.setPro(rs.getInt("num_pro"));
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
                Logger.getLogger(ZipDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return obj;
    }
    public List<Zip> findAllForGraph()
    {
        Statement st=null;
        ResultSet rs=null;
        System.out.println("queryGraf()");
        List<Zip> l=new ArrayList<>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            st=connection.createStatement();
            rs = st.executeQuery(queryGraf);
            while(rs.next())
            {
                Zip obj=new Zip();
                obj.setFcreacion(rs.getString("fcreacion"));
                obj.setPdf(rs.getInt("num_pdf"));
                obj.setXml(rs.getInt("num_xml"));
                obj.setPro(rs.getInt("num_pro"));
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
                Logger.getLogger(ZipDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return l;
    }
    
    public List<Zip> findAll()
    {
        Statement st=null;
        ResultSet rs=null;
        System.out.println("getAll()");
        List<Zip> l=new ArrayList<>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            st=connection.createStatement();
            rs = st.executeQuery(querySelect);
            while(rs.next())
            {
                Zip obj=new Zip();
                obj.setId(rs.getInt("id"));
                obj.setNombre(rs.getString("nombre"));
                obj.setFcreacion(rs.getString("fcreacion"));
                obj.setCreacion(rs.getInt("creacion")); 
                obj.setPdf(rs.getInt("num_pdf"));
                obj.setXml(rs.getInt("num_xml"));
                obj.setPro(rs.getInt("num_pro"));
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
                Logger.getLogger(ZipDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ZipDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

}
