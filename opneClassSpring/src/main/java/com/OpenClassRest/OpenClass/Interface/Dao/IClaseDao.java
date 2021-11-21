package com.OpenClassRest.OpenClass.Interface.Dao;

import java.util.List;

import com.OpenClassRest.OpenClass.Entity.clase;

public interface IClaseDao {
     List<clase> lista(int curso);
     void registrar(clase clase);
     void actualizar(clase clase);
     void eliminar(int claseId);
     clase detalle(int claseId);
}
