package com.OpenClassRest.OpenClass.Interface.Service;

import java.util.List;

import com.OpenClassRest.OpenClass.Entity.cursoRegistro;

public interface ICursoRegistroService {
    void registro(cursoRegistro cursoRegistro);
    Boolean verificaRegistro(int usuarioId, int cursoId);
    List<cursoRegistro> listaRegistrosByUsuario(int usuarioId);
}
