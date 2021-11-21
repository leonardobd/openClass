package com.OpenClassRest.OpenClass.Controllers;

import javax.validation.Valid;

import com.OpenClassRest.OpenClass.Entity.usuario;
import com.OpenClassRest.OpenClass.Interface.Service.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    IUsuarioService usuarioServicio;

    @RequestMapping(value = "/v1/{email}/{password}", method = RequestMethod.GET)
    public usuario Login(@PathVariable("email") String email,@PathVariable("password") String password)throws Exception{
        try {
            return usuarioServicio.login(email, password);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.UsuarioController.Login:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @RequestMapping(value = "/v1", method = RequestMethod.POST)
    public Boolean Registro(@Valid @RequestBody usuario usuario) throws Exception{
        try {
            usuarioServicio.registro(usuario);
            return true;
        } catch (Exception e) {
            System.out.println("error:{msg:'error.UsuarioController.Registro:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @RequestMapping(value = "/v1", method = RequestMethod.PUT)
    public void Actualizar(@Valid @RequestBody usuario usuario){
        try {
            usuarioServicio.actualizar(usuario);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.UsuarioController.Registro:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @RequestMapping(value = "/v1/detalle/{usuarioId}", method = RequestMethod.GET)
    public usuario detalle(@PathVariable("usuarioId") int usuarioId){
        try {
            return usuarioServicio.detalle(usuarioId);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.UsuarioController.Registro:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @RequestMapping(value = "/v1/maestro/{usuarioId}/{institucion}", method = RequestMethod.PUT)
    public void ActualizarMaestro(@PathVariable("usuarioId") int usuarioId,@PathVariable("institucion") String institucion) throws Exception{
        try {
            if(usuarioId>1 && institucion.length()>1){
                usuarioServicio.actualizarMaestro(usuarioId,institucion);
            }else{
                throw new Exception("Error al registrar los datos.");  
            }
        } catch (Exception e) {
            System.out.println("error:{msg:'error.UsuarioController.Registro:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @RequestMapping(value="/v1/confirmacion/{correo}", method=RequestMethod.GET)
    public Boolean confirmacion(@PathVariable("correo") String correo) {
        try {
            usuarioServicio.confirmarUsuario(correo);
            return true;
        } catch (Exception e) {
            System.out.println("error:{msg:'error.UsuarioController.confirmacion:" + e.getMessage() + "'}");
            throw e;
        }
       
    }
    
}
