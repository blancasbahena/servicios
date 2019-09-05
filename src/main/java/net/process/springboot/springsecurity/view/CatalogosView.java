/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.process.springboot.springsecurity.view;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

/**
 *
 * @author Luis Blancas
 */
@ManagedBean
public class CatalogosView {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 5; i++) {
            images.add("nature" + i + ".png");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}