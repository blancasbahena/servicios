/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.process.springboot.springsecurity.view;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import net.process.springboot.springsecurity.model.Photo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@RequestScoped
public class ImageBean
{

	public static final int MAX_IMAGE_COUNT = 5;
	private static final Logger logger = LoggerFactory.getLogger(ImageBean.class);
	private List<Photo> images;

	public ImageBean()
	{
		images = new ArrayList<Photo>();
		
		for (int ctr = 0; ctr < MAX_IMAGE_COUNT; ctr++)
		{
			Photo photo = new Photo();
			photo.setId(ctr);
			photo.setTitle("Mock Title #" + ctr);
			String description = "This photo is used to represent item #" + ctr + " in a selection of images."; 
			photo.setDescription(description);
			images.add(photo);
			logger.info("Added Photo {} to collection.", ctr);
		}
	}

	public List<Photo> getImages()
	{
		return images;
	}

	public void setImages(List<Photo> images)
	{
		this.images = images;
	}

}