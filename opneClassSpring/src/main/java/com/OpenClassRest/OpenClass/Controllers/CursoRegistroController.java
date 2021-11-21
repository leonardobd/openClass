package com.OpenClassRest.OpenClass.Controllers;

import java.util.List;

import javax.validation.Valid;

import com.OpenClassRest.OpenClass.Entity.cursoRegistro;
import com.OpenClassRest.OpenClass.Interface.Service.ICursoRegistroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cursoRegistro")
public class CursoRegistroController {
    
    @Autowired
    ICursoRegistroService cursoRegistroService;

    @RequestMapping(value = "/v1", method = RequestMethod.POST)
    public void Registrar(@Valid @RequestBody cursoRegistro cursoRegistro){
        try {
            cursoRegistroService.registro(cursoRegistro);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.CursoRegistroController.Registrar:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @RequestMapping(value = "/v1/verificado/{usuarioId}/{cursoId}", method = RequestMethod.GET)
    public Boolean VerificaRegistro(@PathVariable("usuarioId") int usuarioId,@PathVariable("cursoId") int cursoId){
        try {
           return cursoRegistroService.verificaRegistro(usuarioId,cursoId);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.CursoRegistroController.VerificaRegistro:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @RequestMapping(value = "/v1/{usuarioId}", method = RequestMethod.GET)
    public List<cursoRegistro> Lista(@PathVariable("usuarioId") int usuarioId){
        try {
            return cursoRegistroService.listaRegistrosByUsuario(usuarioId);
         } catch (Exception e) {
             System.out.println("error:{msg:'error.CursoRegistroController.Lista:" + e.getMessage() + "'}");
             throw e;
         }
    }

}
