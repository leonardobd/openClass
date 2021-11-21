package com.OpenClassRest.OpenClass.Interface.Service;

import java.util.List;

import com.OpenClassRest.OpenClass.Entity.clase;
import com.OpenClassRest.OpenClass.Entity.curso;


public interface ICursoService {
    int registro(clase clase);
    List<curso> lista();
    List<curso> listaByCreador(int usuarioId);
    List<clase> detalle(int cursoId);


}
