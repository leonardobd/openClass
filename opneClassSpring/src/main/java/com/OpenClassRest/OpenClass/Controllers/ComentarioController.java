package com.OpenClassRest.OpenClass.Controllers;

import java.util.List;

import javax.validation.Valid;

import com.OpenClassRest.OpenClass.Entity.comentario;
import com.OpenClassRest.OpenClass.Interface.Service.IComentarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/comentario")
public class ComentarioController {
    
    @Autowired
    IComentarioService comentarioService;


    @RequestMapping(value = "/v1/{claseId}", method = RequestMethod.GET)
    public List<comentario> lista(@PathVariable("claseId") int claseId){
        try {
            return comentarioService.lista(claseId);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.ComentarioController.lista:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @RequestMapping(value = "/v1", method = RequestMethod.POST)
    public void Registro(@Valid @RequestBody comentario comentario){
        try {
             comentarioService.registrar(comentario);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.ComentarioController.Registro:" + e.getMessage() + "'}");
            throw e;
        }
    }
}
