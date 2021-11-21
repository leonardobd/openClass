package com.OpenClassRest.OpenClass.Interface.Dao;

import java.util.List;

import com.OpenClassRest.OpenClass.Entity.comentario;

import org.springframework.data.repository.CrudRepository;

public interface IComentarioDao{
    public List<comentario> lista(int claseId);
    public void registrar(comentario comentario);
}
