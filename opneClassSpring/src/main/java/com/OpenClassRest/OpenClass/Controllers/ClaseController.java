package com.OpenClassRest.OpenClass.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.OpenClassRest.OpenClass.Entity.clase;
import com.OpenClassRest.OpenClass.Interface.Service.IClaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clase")
public class ClaseController {
    
    @Autowired
    IClaseService claseService;

    @RequestMapping(value = "/v1/obtienePorCurso/{cursoId}", method = RequestMethod.GET, produces = "application/json")
    public clase ObtienePorCurso(@PathVariable("cursoId") int cursoId){
        clase clase = new clase();
        return clase;
    }

    @RequestMapping(value = "/v1/detalle/{claseId}", method = RequestMethod.GET, produces = "application/json")
    public clase detalle(@PathVariable("claseId") int claseId){
        try {
            return claseService.detalle(claseId);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.ClaseController.detalle:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @RequestMapping(value = "/v1/listaPorCurso/{cursoId}", method = RequestMethod.GET, produces = "application/json")
    public List<clase> listaByCource(@PathVariable("cursoId") int cursoId){
        try {
            return claseService.lista(cursoId);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.ClaseController.listaByCource:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @RequestMapping(value = "/v1", method = RequestMethod.POST)
    public void Registro(@Valid @RequestBody clase clase){
        try {
            claseService.registrar(clase);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.ClaseController.Registro:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @PutMapping(value = "/v1")
    public void Editar(@Valid @RequestBody clase clase){
        try {
            claseService.actualizar(clase);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.ClaseController.Editar:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @DeleteMapping(value = "/v1/{curso}")
    public void Eliminar(@PathVariable("curso") int claseId){
        try {
            claseService.eliminar(claseId);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.ClaseController.Eliminar:" + e.getMessage() + "'}");
            throw e;
        }
    }
}
