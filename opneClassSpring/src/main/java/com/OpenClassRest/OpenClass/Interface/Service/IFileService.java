package com.OpenClassRest.OpenClass.Interface.Service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String subeArchivo(MultipartFile file, String Nombre, String tipo) throws Exception;
    String generaNombreArchivo(String extension, String nombreAsignado);
}
