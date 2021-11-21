package com.OpenClassRest.OpenClass.Service;

import javax.transaction.Transactional;

import com.OpenClassRest.OpenClass.Dao.UsuarioDao;
import com.OpenClassRest.OpenClass.Entity.usuario;
import com.OpenClassRest.OpenClass.Interface.Service.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("usarioService")
@Transactional 
public class UsarioService implements IUsuarioService{

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    MailService mailService;

    @Override
    public usuario login(String email, String password) throws Exception{
        try {
            if(email.equals("")||password.equals("")) throw new Exception("Usuario o password no pueden estar vacios.");
            return usuarioDao.login(email, password);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.UsarioService.login:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @Override
    public void registro(usuario usuario) throws Exception {
        try {
            usuarioDao.registrar(usuario);
            mailService.enviar(usuario.usuarioCorreo, "Confirmacion de correo", generarHtml("localhost:8080/usuario/v1/confirmacion/"+usuario.usuarioCorreo));
        } catch (Exception e) {
            System.out.println("error:{msg:'error.UsarioService.registro:" + e.getMessage() + "'}");
            throw e;
        }
    }

    private String generarHtml(String link){
        String cuerpo="<!doctype html>"+
        "<html>"+
        "<head>"+
        "<meta name='viewport' content='width=device-width'>"+
        "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"+
        "<title>OpenClass</title>"+
        "</head>"+
        "<body class='"+
        "style='background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;'>"+
        "<span class='preheader'"+
        "style='color: transparent; display: none; height: 0; max-height: 0; max-width: 0; opacity: 0; overflow: hidden; mso-hide: all; visibility: hidden; width: 0;'>This"+
        "is preheader text. Some clients will show this text as a preview.</span>"+
        "<table border='0' cellpadding='0' cellspacing='0' class='body'"+
        "style='border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background-color: #f6f6f6;'>"+
        "<tr>"+
        "<td style='font-family: sans-serif; font-size: 14px; vertical-align: top;'>&nbsp;</td>"+
        "<td class='container'"+
        "style='font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; Margin: 0 auto; max-width: 580px; padding: 10px; width: 580px;'>"+
        "<div class='content'"+
        "style='box-sizing: border-box; display: block; Margin: 0 auto; max-width: 580px; padding: 10px;'>"+
        "<table class='main'"+
        "style='border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background: #ffffff; border-radius: 3px;'>"+
        "<tr>"+
        "<td class='wrapper'"+
        "style='font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;'>"+
        "<table border='0' cellpadding='0' cellspacing='0'"+
        "style='border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;'>"+
        "<tr>"+
        "<td style='font-family: sans-serif; font-size: 14px; vertical-align: top;'>"+
        "<p"+
        "style='font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;'>"+
        "Bienvenido a OpenClass,</p>"+
        "<p"+
        "style='font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;'>"+
        "Estamos muy contentos de tenerte aqui, por favor pulsa en el siguiente boton para terminar el"+
        "proceso de registro.</p>"+
        "<table border='0' cellpadding='0' cellspacing='0' class='btn btn-primary'"+
        "style='border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; box-sizing: border-box;'>"+
        "<tbody>"+
        "<tr>"+
        "<td align='left'"+
        "style='font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;'>"+
        "<table border='0' cellpadding='0' cellspacing='0'"+
        "style='border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;'>"+
        "<tbody>"+
        "<tr>"+
        "<td"+
        "style='font-family: sans-serif; font-size: 14px; vertical-align: top; background-color: #3498db; border-radius: 5px; text-align: center;'>"+
        "<a href='"+link+"' target='_blank'"+
        "style='display: inline-block; color: #ffffff; background-color: #3498db; border: solid 1px #3498db; border-radius: 5px; box-sizing: border-box; cursor: pointer; text-decoration: none; font-size: 14px; font-weight: bold; margin: 0; padding: 12px 25px; text-transform: capitalize; border-color: #3498db;'>Confirmar</a>"+
        "</td>"+
        "</tr>"+
        "</tbody>"+
        "</table>"+
        "</td>"+
        "</tr>"+
        "</tbody>"+
        "</table>"+
        "</td>"+
        "</tr>"+
        "</table>"+
        "</td>"+
        "</tr>"+
        "</table>"+
        "<div class='footer' style='clear: both; Margin-top: 10px; text-align: center; width: 100%;'>"+
        "<table border='0' cellpadding='0' cellspacing='0'"+
        "style='border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;'>"+
        "<tr>"+
        "<td class='content-block'"+
        "style='font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;'>"+
        "<span class='apple-link'"+
        "style='color: #999999; font-size: 12px; text-align: center;'>OpenClass.com</span>"+
        "<br>"+
        "</td>"+
        "</tr>"+
        "<tr>"+
        "<td class='content-block powered-by'"+
        "style='font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;'>"+
        "Powered by <a href='http://htmlemail.io'"+
        "style='color: #999999; font-size: 12px; text-align: center; text-decoration: none;'>HTMLemail</a>."+
        "</td>"+
        "</tr>"+
        "</table>"+
        "</div>"+
        "</div>"+
        "</td>"+
        "<td style='font-family: sans-serif; font-size: 14px; vertical-align: top;'>&nbsp;</td>"+
        "</tr>"+
        "</table>"+
        "</body>"+
        "</html>";
        return cuerpo;
    }

    @Override
    public void actualizar(usuario usuario) {
        try {
            usuarioDao.actualizar(usuario);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.UsarioService.registro:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @Override
    public usuario detalle(int usuarioId) {
       try {
           return usuarioDao.detalle(usuarioId);
       } catch (Exception e) {
        System.out.println("error:{msg:'error.UsarioService.detalle:" + e.getMessage() + "'}");
        throw e;
       }
    }

    @Override
    public void actualizarMaestro(int usuarioId, String institucion) {
        try {
            usuarioDao.actualizarMaestro(usuarioId,institucion);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.UsarioService.registro:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @Override
    public void confirmarUsuario(String correo) {
        try {
            usuarioDao.confirmarCorreo(correo);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.UsarioService.registro:" + e.getMessage() + "'}");
            throw e;
        }
    }
    
}
