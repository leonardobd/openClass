package com.OpenClassRest.OpenClass.Interface.Service;

import java.util.List;

import com.OpenClassRest.OpenClass.Entity.clase;

public interface IClaseService {
    List<clase> lista(int curso);
    clase detalle(int claseId);
    void registrar(clase clase);
     void actualizar(clase clase);
    void eliminar(int claseId);
}
