/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.stp.controller;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream; 
import mx.org.stp.entity.ArchivoBean;
import mx.org.stp.entity.DetailFile;
import mx.org.stp.entity.dao.ArchivoDAO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
/**
 *
 * @author Luis Blancas
 */
public class ArchivoController {
    
    public void limpiar()
    {
        ArchivoDAO dao= new ArchivoDAO();
        dao.limpiar();
    }
    public List<ArchivoBean> getAll() 
    {
        List<ArchivoBean> lista=new ArrayList();
        ArchivoDAO dao = new ArchivoDAO();
        lista = dao.findAll();
        return lista;
    }
    public String obtieneCadena(String fecha1,String f2)
    { 
         String resp="";
        try 
        {
            RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);  
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            //String url ="https://demo.stpmex.com:7024/conciliadorcep/consultaDatosCep?fechaInicial="+fecha1+"&fechaFinal="+f2;
            String url ="https://local.stpmex.com:7002/conciliadorcep/consultaDatosCep?fechaInicial="+fecha1+"&fechaFinal="+f2;
            System.out.println("Invocando servicio ::" +url);
            ResponseEntity<String> result = restTemplate.getForEntity((new URI(url)), String.class); 
            System.out.println("Resultado conciliador CEP::  "+result.getBody());
            resp=  result.getBody();
        } catch (URISyntaxException ex) {
            Logger.getLogger(ArchivoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }
    private ClientHttpRequestFactory getClientHttpRequestFactory() 
    {
        int timeout = 320000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
          new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }
    public  ArchivoBean obtieneCadenayCreaArchivo( String fecha_pago_i,String fecha_pago_f, 
                String nombreRuta ,int creado,String context) 
    { 
        String pattern = "yyyy-MM-dd";
        String time =(""+(new Date()).getTime()); 
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        System.out.println(date); 
        ArchivoBean arc= new ArchivoBean(8,nombreRuta+time+".zip",fecha_pago_i,fecha_pago_f,creado,date,true,time+".zip");
        arc.setNombreZip(time+".zip");
        Gson gson = new Gson();
        String ressultado =obtieneCadena(fecha_pago_i,fecha_pago_f);
        DetailFile[]  listaDetalle =  gson.fromJson(ressultado,DetailFile[].class);
        int indice=1;
        int total =501;
        int numeroArchivo=0;
        String cadena="";
        List<String> archivos = new ArrayList<String>();
        for(int cont=0;cont<listaDetalle.length;cont++)
        { 
            DetailFile dfile=listaDetalle[cont];
            if(indice<=total)
            {
                cadena = cadena +dfile.toString()+"\n";
                indice ++;
            }
            if(indice==total)
            {
                indice  =1;
                numeroArchivo ++;
                String name =nombreRuta+ time+"_"+numeroArchivo+".txt";
                
                if(createFile(name,cadena))
                    archivos.add(name);
                cadena="";
            }
            else
            {
                int registros = (numeroArchivo*(total-1))+(indice-1);
                if(registros ==listaDetalle.length )
                {
                    numeroArchivo ++;
                    String name =nombreRuta+ time+"_"+numeroArchivo+".txt";
                    if(createFile(name,cadena))
                        archivos.add(name);
                }
            }
        }
        if(creaZipdeArchivos(archivos,nombreRuta+time+"_"+numeroArchivo+".zip"))
        {
            arc.setNombre(nombreRuta+time+"_"+numeroArchivo+".zip");
        }
        ArchivoDAO dao= new ArchivoDAO();
        dao.insert(arc.getNombre(),arc.getFecha_pago_i(),arc.getFecha_pago_i(),arc.getFecha_creacion(),creado,listaDetalle.length,context);
        return arc;
    }
    private boolean creaZipdeArchivos(List<String> srcFiles,String nameZip)
    { 
        try
        {
            FileOutputStream fos = new FileOutputStream(nameZip);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            for (String srcFile : srcFiles) {
                File fileToZip = new File(srcFile);
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int length;
                while((length = fis.read(bytes)) >= 0) 
                {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            }
            zipOut.close();
            fos.close();
            return true;
        }
        catch(IOException ioE)
        {
            ioE.printStackTrace();
        }
        return false;
    }

    private  boolean createFile(String name,String body)
    {	
        try 
        {
            FileWriter fileWriter = new FileWriter(new File(name));
            fileWriter.write(body);
            fileWriter.flush();
            fileWriter.close();
            return true;
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        return false;
    }

    
}
