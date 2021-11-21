package com.OpenClassRest.OpenClass.Interface.Dao;

import com.OpenClassRest.OpenClass.Entity.usuario;

public interface IUsuarioDao {
    usuario login(String correo, String password);
    void registrar(usuario usuario);
    void actualizar(usuario usuario);
    usuario detalle(int usuarioId);
    void actualizarMaestro(int usuarioId,String institucion);
    void confirmarCorreo(String correo);
}
