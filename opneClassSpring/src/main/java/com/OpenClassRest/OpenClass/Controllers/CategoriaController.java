package com.OpenClassRest.OpenClass.Controllers;

import java.util.List;

import com.OpenClassRest.OpenClass.Entity.categoria;
import com.OpenClassRest.OpenClass.Interface.Service.ICategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    
    @Autowired
    ICategoriaService categoriaService;

    @RequestMapping(value = "/v1", method = RequestMethod.GET, produces = "application/json")
    public List<categoria> Obtener(){
        try {
            List<categoria> categorias =  categoriaService.lista();
            return categorias;
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:CategoriaController.Obtener:"+e.getMessage()+"'}");
            throw e;
        }
        
    }
}
