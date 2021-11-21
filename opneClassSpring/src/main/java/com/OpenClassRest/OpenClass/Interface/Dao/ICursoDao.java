package com.OpenClassRest.OpenClass.Interface.Dao;

import java.util.List;

import com.OpenClassRest.OpenClass.Entity.clase;
import com.OpenClassRest.OpenClass.Entity.curso;

public interface ICursoDao {
    List<curso> lista();
    int registrar(curso curso);
    List<curso> listaByCategoria(int categoriaId);
    List<clase> detalle(int cursoId);
    
    void editar(curso curso);
    void eliminar(int cursoId);
    List<curso> listaByCreador(int usuarioId);


}
