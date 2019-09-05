/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.process.springboot.springsecurity.view;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;
import net.process.springboot.springsecurity.model.Image;
@ManagedBean
@Getter
@Setter
public class ImagesView
{

	private List<String> images;
    private List<Image>  imagesList;
    @PostConstruct
    public void init() 
    {
        images = new ArrayList<String>();
        for (int i = 1; i <= 5; i++) {
            images.add("nature" + i + ".png");
        }
        
        imagesList = new ArrayList<Image>();
        imagesList.add(new Image("Usuarios","../../../resources/images/nature1.png","/faces/catalogos/nature1.xhtml"));
        imagesList.add(new Image("Grupos","../../../resources/images/nature2.png","/faces/catalogos/nature2.xhtml"));
        imagesList.add(new Image("Restriciones","../../../resources/images/nature3.png","/faces/catalogos/nature3.xhtml"));
        imagesList.add(new Image("Sucursales","../../../resources/images/nature4.png","/faces/catalogos/nature4.xhtml"));
        imagesList.add(new Image("Companias","../../../resources/images//nature5.png","/faces/catalogos/nature5.xhtml"));
        
    } 
    public void action()
    {
    	FacesContext context = FacesContext.getCurrentInstance();
    	Map<String,String> params = context.getExternalContext().getRequestParameterMap();
    	String link =  params.get("link");
    	System.out.println("data :::::::::"+link);
		try {
			context.getExternalContext().redirect(link);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}