package com.OpenClassRest.OpenClass.Service;

import java.util.List;

import javax.transaction.Transactional;

import com.OpenClassRest.OpenClass.Entity.comentario;
import com.OpenClassRest.OpenClass.Interface.Dao.IComentarioDao;
import com.OpenClassRest.OpenClass.Interface.Service.IComentarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("comentarioService")
@Transactional
public class ComentarioService implements IComentarioService {

    @Autowired
    private IComentarioDao comentarioDao;

    @Override
    public List<comentario> lista(int claseId) {
        try {
            return comentarioDao.lista(claseId);
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:ComentarioService.lista:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @Override
    public void registrar(comentario comentario) {
        try {
            comentarioDao.registrar(comentario);
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:ComentarioService.lista:" + e.getMessage() + "'}");
            throw e;
        }
    }

}
