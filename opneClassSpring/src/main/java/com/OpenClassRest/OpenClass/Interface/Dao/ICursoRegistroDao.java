package com.OpenClassRest.OpenClass.Interface.Dao;

import java.util.List;

import com.OpenClassRest.OpenClass.Entity.cursoRegistro;

public interface ICursoRegistroDao {
    void registro(cursoRegistro cursoRegistro);
    Boolean verificaRegistro(int usuarioId, int cursoId);
    List<cursoRegistro> listaRegistrosByUsuario(int usuarioId);
}
