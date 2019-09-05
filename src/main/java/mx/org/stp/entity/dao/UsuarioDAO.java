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
import mx.org.stp.entity.Usuario;

/**
 *
 * @author Luis Blancas
 */
public class UsuarioDAO extends Conexion
{
    String querySelect   =" SELECT u.id, login, password, historial, nombre,   paterno, materno, perfil, estatus, " +
            " p.descripcion perfilD,e.descripcion estatusD,fcreacion, fmodificacion, creacion, modificacion  " +
            " FROM cep_usuario u, cep_perfil p, cep_estatus e " +
            " where u.perfil =p.id and  u.estatus = e.id";
    String queryMax      ="select nextval ('sec_usuarios') ID";
    String querySelectOne="SELECT id, login, password, historial, nombre, "
            + " paterno, materno, perfil, estatus, fcreacion, fmodificacion, creacion, modificacion " +
                " FROM cep_usuario  WHERE ID = ?";
    String querySelectLogin="SELECT id, login, password, historial, nombre, "
            + " paterno, materno, perfil, estatus, fcreacion, fmodificacion, creacion, modificacion " +
                " FROM cep_usuario  WHERE login = ?";
    String querySelectLoginPassword="SELECT id, login, password, historial, nombre, "
            + " paterno, materno, perfil, estatus, fcreacion, fmodificacion, creacion, modificacion " +
                " FROM cep_usuario  WHERE login = ? and password =? ";
    String queryUpdate   ="UPDATE cep_usuario " +
            " SET login=?, nombre=?, paterno=?, materno=?, perfil=?, "
            + " estatus=?,fmodificacion=?,modificacion=?  WHERE  ID = ?";
    String queryUpdatePassword   ="UPDATE cep_usuario " +
            " SET  password=?, historial=? ,fmodificacion=?,  modificacion=? " +
            " WHERE  ID = ?";
    String queryDelete   ="DELETE FROM cep_usuario WHERE ID = ? ";
    String queryCreate   ="INSERT INTO cep_usuario( " +
            " id, login, password, historial, nombre, paterno, materno, perfil, estatus, fcreacion, "
            + " fmodificacion, creacion, modificacion) " +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    
   public Usuario  updatePassword( String password,int usuario,int id)
    {
        Usuario bean=null;
        int updateCont=0;
        System.out.println("updateRecord("+id+","+usuario+")");
        if(id>0)
        {
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            Connection connection=null;
            try 
            {
                connection=get_connection();
                PreparedStatement ps=connection.prepareStatement(queryUpdatePassword);
                ps.setString(1, password);
                ps.setString(2, password);
                ps.setString(3, date);
                ps.setInt   (4, usuario);
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
                        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        else
            System.out.println("Id invalida ");
        return bean;
    }        
    public Usuario  update(String login,String nombre, 
            String paterno,String materno,int perfil,int estatus,int usuario,int id)
    {
        Usuario bean=null;
        int updateCont=0;
        System.out.println("updateRecord("+login+","+nombre+")");
        if(id>0)
        {
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            Connection connection=null;
            try 
            {
                connection=get_connection();
                PreparedStatement ps=connection.prepareStatement(queryUpdate);
                ps.setString(1, login);
                ps.setString(2, nombre);
                ps.setString(3, paterno);
                ps.setString(4, materno);
                ps.setInt   (5, perfil);
                ps.setInt   (6, estatus); 
                ps.setString(7, date);
                ps.setInt   (8, usuario);
                ps.setInt   (9, id);
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
                        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        else
            System.out.println("Id invalida ");
        return bean;
    }
    public Usuario  insert(String login,String password,String nombre, 
            String paterno,String materno,int perfil,int estatus,int usuario)
    {
        Usuario bean=null;
        int insertCont=0;
        System.out.println("insertRecord("+login+")");
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
                ps.setString(2, login);
                ps.setString(3, password);
                ps.setString(4, password);
                ps.setString(5, nombre);
                ps.setString(6, paterno);
                ps.setString(7, materno);
                ps.setInt   (8, perfil);
                ps.setInt   (9, estatus);
                ps.setString(10,date);
                ps.setString(11,date);
                ps.setInt   (12,usuario);
                ps.setInt   (13,usuario);
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
                        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cont;
    }
    public Usuario findById(int id)
    {
        PreparedStatement ps=null;
        ResultSet rs=null;
        System.out.println("getOne("+id+")");
        Usuario obj=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            ps=connection.prepareStatement(querySelectOne);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next())
            {
                obj=new Usuario();
                obj.setId(rs.getInt("id"));
                obj.setLogin(rs.getString("login"));
                obj.setPassword(rs.getString("password"));
                obj.setNombre(rs.getString("nombre"));
                obj.setPaterno(rs.getString("paterno"));
                obj.setMaterno(rs.getString("materno"));
                obj.setApellidos(rs.getString("paterno")+" "+rs.getString("materno"));
                obj.setPerfil(rs.getInt("perfil"));
                int estatus= rs.getInt("estatus");
                obj.setActivo(estatus);
                obj.setEstatus(estatus==1?true:false);
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
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return obj;
    }
    public Usuario findByLoginAndPassword(String login,String password)
    {
        PreparedStatement ps=null;
        ResultSet rs=null;
        System.out.println("getOne("+login+")");
        Usuario obj=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            ps=connection.prepareStatement(querySelectLoginPassword);
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next())
            {
                obj=new Usuario();
                obj.setId(rs.getInt("id"));
                obj.setLogin(rs.getString("login"));
                obj.setPassword(rs.getString("password"));
                //obj.setHistorial(rs.getString("historial"));
                obj.setNombre(rs.getString("nombre"));
                obj.setPaterno(rs.getString("paterno"));
                obj.setMaterno(rs.getString("materno"));
                obj.setApellidos(rs.getString("paterno")+" "+rs.getString("materno"));
                obj.setPerfil(rs.getInt("perfil"));
                obj.setActivo(rs.getInt("estatus"));
                //obj.setFcreacion(rs.getString("fcreacion"));
                //obj.setFmodificacion(rs.getString("fmodificacion"));
                //obj.setCreacion(rs.getInt("creacion"));
                //obj.setModificacion(rs.getInt("modificacion"));
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
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return obj;
    }
    public Usuario findByLogin(String login)
    {
        PreparedStatement ps=null;
        ResultSet rs=null;
        System.out.println("getOne("+login+")");
        Usuario obj=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            System.out.println(querySelectLogin);
            ps=connection.prepareStatement(querySelectLogin);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if(rs.next())
            {
                obj=new Usuario();
                obj.setId(rs.getInt("id"));
                obj.setLogin(rs.getString("login"));
                obj.setPassword(rs.getString("password"));
                obj.setNombre(rs.getString("nombre"));
                obj.setPaterno(rs.getString("paterno"));
                obj.setMaterno(rs.getString("materno"));
                obj.setApellidos(rs.getString("paterno")+" "+rs.getString("materno"));
                obj.setPerfil(rs.getInt("perfil"));
                int estatus= rs.getInt("estatus");
                obj.setActivo(estatus);
                obj.setEstatus(estatus==1?true:false);
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
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return obj;
    }
    public List<Usuario> findAll()
    {
        Statement st=null;
        ResultSet rs=null;
        System.out.println("getAll()");
        List<Usuario> l=new ArrayList<>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            st=connection.createStatement();
            rs = st.executeQuery(querySelect);
            while(rs.next())
            {
                Usuario obj=new Usuario();
                obj.setId(rs.getInt("id"));
                obj.setLogin(rs.getString("login"));
                obj.setPassword(rs.getString("password"));
                //obj.setHistorial(rs.getString("historial"));
                obj.setNombre(rs.getString("nombre"));
                obj.setPaterno(rs.getString("paterno"));
                obj.setMaterno(rs.getString("materno"));
                obj.setApellidos(rs.getString("paterno")+" "+rs.getString("materno"));
                obj.setPerfil(rs.getInt("perfil"));
                int estatus= rs.getInt("estatus");
                obj.setActivo(estatus);
                obj.setEstatus(estatus==1?true:false);
                obj.setPerfilstr(rs.getString("perfilD"));
                obj.setActivostr(rs.getString("estatusD")); 
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
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

}
