package com.OpenClassRest.OpenClass.Controllers;

import com.OpenClassRest.OpenClass.Service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService file;

    @RequestMapping(value = "/v1/{tipo}", method = RequestMethod.PUT)
    public String  SubirArchivo(@RequestParam("file") MultipartFile archivo, @PathVariable("tipo") String tipo)  throws Exception{
        try {
            // tipo: video - miniatura - perfil
            String extension = archivo.getOriginalFilename().substring(archivo.getOriginalFilename().lastIndexOf('.'));
            String nombre = file.generaNombreArchivo(extension, tipo);
            //String url = file.subeArchivo(archivo, nombre, tipo);
            return "{\"response\":\""+file.subeArchivo(archivo, nombre, tipo)+"\"}";
        } catch (Exception e) {
            System.out.println("error:{msg:'error.FileController.SubirArchivo:" + e.getMessage() + "'}");
            throw e;
        }

    }
}
