package com.OpenClassRest.OpenClass.Interface.Service;

import com.OpenClassRest.OpenClass.Entity.usuario;

public interface IUsuarioService {
    
    usuario login(String email,String password)throws Exception;
    void registro(usuario usuario)throws Exception;
    void actualizar(usuario usuario);
    usuario detalle(int usuarioId);
    void actualizarMaestro(int usuarioId,String institucion);
    void confirmarUsuario(String correo);
}
