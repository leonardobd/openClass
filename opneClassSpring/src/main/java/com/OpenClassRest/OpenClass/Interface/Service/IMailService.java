package com.OpenClassRest.OpenClass.Interface.Service;

public interface IMailService {
    void enviar(String destinatario, String asunto,String cuerpo)throws Exception;
}
