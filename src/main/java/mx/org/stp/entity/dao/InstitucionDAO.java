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
import mx.org.stp.entity.InstitucionBean;

/**
 *
 * @author Luis Blancas
 */
public class InstitucionDAO extends Conexion
{
    String querySelect   ="SELECT id, descricorta, descripcion, clave, fcreacion,"
            + "fmodificacion, creacion, modificacion FROM  cep_instituciones ";
    String queryMax      ="select nextval ('sec_instituciones') ID";
    String querySelectOne=" SELECT id, descricorta, descripcion, clave, fcreacion, "
            + "fmodificacion, creacion, modificacion FROM  cep_instituciones WHERE ID = ?";
    String queryUpdate   ="UPDATE cep_instituciones SET "
            + " descricorta=?, descripcion=?, clave=?,  fmodificacion=?,  modificacion=? WHERE ID = ?";
    String queryDelete   ="DELETE FROM cep_instituciones WHERE ID = ? ";
    String queryCreate   ="INSERT INTO  cep_instituciones"
            + "( id, descricorta, descripcion, clave, fcreacion, fmodificacion, creacion, modificacion)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
    public InstitucionBean  update(int id,String descricorta,
            String descripcion,String clave,int usuario)
    {
        InstitucionBean bean=null;
        int updateCont=0;
        System.out.println("updateRecord("+id+","+descricorta+","+descripcion+","+clave+","+usuario+")");
        if(id>0)
        {
            
            Connection connection=null;
            try 
            {
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date());
                connection=get_connection();
                PreparedStatement ps=connection.prepareStatement(queryUpdate);
                ps.setString(1, descricorta);
                ps.setString(2, descripcion);
                ps.setString(3, clave);
                ps.setString(4, date);
                ps.setInt   (5, usuario);
                ps.setInt   (6, id);
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
                        Logger.getLogger(InstitucionDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        else
            System.out.println("Id invalida ");
        return bean;
    }
    public InstitucionBean  insert(String descricorta,
            String descripcion,String clave,int usuario)
    {
        InstitucionBean bean=null;
        int insertCont=0;
        System.out.println("insertRecord("+descricorta+","+descripcion+","+clave+","+usuario+")");
        int id=getSecuence();
        if(id>0)
        {
            Connection connection=null;
            try 
            {
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date());
                connection=get_connection();
                PreparedStatement ps=connection.prepareStatement(queryCreate);
                ps.setInt   (1, id);
                ps.setString(2, descricorta);
                ps.setString(3, descripcion);
                ps.setString(4, clave);
                ps.setString(5, date);
                ps.setString(6, date);
                ps.setInt   (7, usuario);
                ps.setInt   (8, usuario);
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
                        Logger.getLogger(InstitucionDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                //rs.close();
                ps.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(InstitucionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cont;
    }
    public InstitucionBean findById(int id)
    {
        PreparedStatement ps=null;
        ResultSet rs=null;
        System.out.println("getOne("+id+")");
        InstitucionBean obj=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            ps=connection.prepareStatement(querySelectOne);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next())
            {
                obj=new InstitucionBean();
                obj.setId(rs.getInt("id"));
                obj.setCorta(rs.getString("descricorta")); 
                obj.setNombre(rs.getString("descripcion")); 
                obj.setClave(rs.getString("clave"));
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
                Logger.getLogger(InstitucionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return obj;
    }
    public List<InstitucionBean> findAll()
    {
        Statement st=null;
        ResultSet rs=null;
        System.out.println("getAll()");
        List<InstitucionBean> l=new ArrayList<>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            st=connection.createStatement();
            rs = st.executeQuery(querySelect);
            while(rs.next())
            {
                InstitucionBean obj=new InstitucionBean();
                obj.setId(rs.getInt("id"));
                obj.setCorta(rs.getString("descricorta")); 
                obj.setNombre(rs.getString("descripcion")); 
                obj.setClave(rs.getString("clave")); 
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
                Logger.getLogger(InstitucionDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(InstitucionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

}
