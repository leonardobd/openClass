package com.OpenClassRest.OpenClass.Controllers;

import java.util.List;

import javax.validation.Valid;

import com.OpenClassRest.OpenClass.Entity.clase;
import com.OpenClassRest.OpenClass.Entity.curso;
import com.OpenClassRest.OpenClass.Interface.Service.ICursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/curso")
public class CursoController {
    
    @Autowired
    ICursoService cursoService;

    @RequestMapping(value = "/v1", method = RequestMethod.POST)
    public int Registro(@Valid @RequestBody clase clase){
        return cursoService.registro(clase);
    }

    @RequestMapping(value = "/v1", method = RequestMethod.GET)
    public List<curso> Obtener(){
        List<curso> cursos = cursoService.lista();
        return cursos;
    }

    @RequestMapping(value = "/v1/detalle/{claseId}", method = RequestMethod.GET)
    public List<clase> Detalle(@PathVariable("claseId") int cursoId){
        return cursoService.detalle(cursoId);
    }

    @RequestMapping(value = "/v1/creador/{idUsuario}", method = RequestMethod.GET)
    public List<curso> ObtenerPorCreador(@PathVariable("idUsuario") int idUsuario){
        List<curso> cursos = cursoService.listaByCreador(idUsuario);
        return cursos;
    }

}
