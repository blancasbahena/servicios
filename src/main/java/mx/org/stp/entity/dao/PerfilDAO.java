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
import mx.org.stp.entity.Perfil;

/**
 *
 * @author Luis Blancas
 */
public class PerfilDAO extends Conexion
{
    String querySelect   ="SELECT id, descripcion FROM cep_perfil";
    String queryMax      ="select nextval ('sec_perfil') ID";
    String querySelectOne=" SELECT id, descripcion FROM cep_perfil WHERE ID = ?";
    String queryUpdate   ="UPDATE cep_perfil SET  descripcion=? WHERE ID = ?";
    String queryDelete   ="DELETE FROM cep_perfil WHERE ID = ? ";
    String queryCreate   ="INSERT INTO  cep_perfil(id, descripcion) VALUES (?, ?) ";
    public Perfil  update(int id,String descripcion)
    {
        Perfil bean=null;
        int updateCont=0;
        System.out.println("updateRecord("+id+","+descripcion+")");
        if(id>0)
        {
            
            Connection connection=null;
            try 
            {
                connection=get_connection();
                PreparedStatement ps=connection.prepareStatement(queryUpdate);
                ps.setString(1, descripcion); 
                ps.setInt   (2, id);
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
                        Logger.getLogger(PerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        else
            System.out.println("Id invalida ");
        return bean;
    }
    public Perfil  insert(String descripcion)
    {
        Perfil bean=null;
        int insertCont=0;
        System.out.println("insertRecord("+descripcion+")");
        int id=getSecuence();
        if(id>0)
        {
            Connection connection=null;
            try 
            {
                connection=get_connection();
                PreparedStatement ps=connection.prepareStatement(queryCreate);
                ps.setInt   (1, id);
                ps.setString(2, descripcion);
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
                        Logger.getLogger(PerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(PerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cont;
    }
    public Perfil findById(int id)
    {
        PreparedStatement ps=null;
        ResultSet rs=null;
        System.out.println("getOne("+id+")");
        Perfil obj=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            ps=connection.prepareStatement(querySelectOne);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next())
            {
                obj=new Perfil();
                obj.setId(rs.getInt("id"));
                obj.setDescripcion(rs.getString("descripcion")); 
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
                Logger.getLogger(PerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return obj;
    }
    public List<Perfil> findAll()
    {
        Statement st=null;
        ResultSet rs=null;
        System.out.println("getAll()");
        List<Perfil> l=new ArrayList<>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            st=connection.createStatement();
            rs = st.executeQuery(querySelect);
            while(rs.next())
            {
                Perfil obj=new Perfil();
                obj.setId(rs.getInt("id"));
                obj.setDescripcion(rs.getString("descripcion")); 
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
                Logger.getLogger(PerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(PerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

}
