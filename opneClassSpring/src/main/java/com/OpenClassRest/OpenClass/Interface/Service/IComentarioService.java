package com.OpenClassRest.OpenClass.Interface.Service;

import java.util.List;

import com.OpenClassRest.OpenClass.Entity.comentario;

public interface IComentarioService {
    public List<comentario> lista(int claseId);
    public void registrar(comentario comentario);
}
