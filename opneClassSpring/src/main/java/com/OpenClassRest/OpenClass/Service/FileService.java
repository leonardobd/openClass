package com.OpenClassRest.OpenClass.Service;

import javax.transaction.Transactional;

import com.OpenClassRest.OpenClass.Interface.Service.IFileService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;

@Service("FileService")
@Transactional
public class FileService implements IFileService {

    private static final String ACCESS_TOKEN = ".....";

    @Override
    public String subeArchivo(MultipartFile file, String Nombre, String tipo) throws Exception {
        try {
            String name = file.getOriginalFilename();
            String prefix = name.substring(name.lastIndexOf("."));
            File fileUpload = File.createTempFile(name, prefix);
            file.transferTo(fileUpload);
            InputStream is = new FileInputStream(fileUpload);
            DbxClientV2 token = generarCredenciales();
            token.files().uploadBuilder(determinaCarpetaSave(tipo) + Nombre).withMode(WriteMode.ADD)
                    .uploadAndFinish(is);
            SharedLinkMetadata lsharedResult = token.sharing()
                    .createSharedLinkWithSettings(determinaCarpetaSave(tipo) + Nombre);
            String result = lsharedResult.getUrl();
            return rawConverterLink(result);
        } catch (Exception e) {
            System.out.println("error:{msg:'error.fileServices.subeArchivo:" + e.getMessage() + "'}");
            throw e;
        }
    }

    private DbxClientV2 generarCredenciales() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        return client;
    }

    private String determinaCarpetaSave(String tipo) throws Exception {
        try {
            if (tipo.equals("video") || tipo.equals("miniatura"))
                return "/clases/";
            else if (tipo.equals(""))
                throw new Exception("El campo tipo de archivo no puede ir vacio");
            else
                return "/perfil/";
        } catch (Exception e) {
            System.out.println("error:{msg:'error.fileServices.subeArchivo:" + e.getMessage() + "'}");
            throw e;
        }

    }

    private String rawConverterLink(String urlOriginal) {
        return urlOriginal.replace("dl=0", "raw=1");
    }

    @Override
    public String generaNombreArchivo(String extension, String nombreAsignado) {
        DateFormat dateFormat = new SimpleDateFormat("HHmmss");
        String variable = dateFormat.format(new Date());
        return nombreAsignado + variable + extension;
    }

}
