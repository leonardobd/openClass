package com.OpenClassRest.OpenClass.Service;

import java.util.List;

import javax.transaction.Transactional;

import com.OpenClassRest.OpenClass.Entity.clase;
import com.OpenClassRest.OpenClass.Interface.Dao.IClaseDao;
import com.OpenClassRest.OpenClass.Interface.Service.IClaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("claseService")
@Transactional 
public class ClaseService implements IClaseService{

    @Autowired
    IClaseDao claseDao;

    @Override
    public void eliminar(int claseId) {
        try {
            claseDao.eliminar(claseId);
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:ClaseService.eliminar:"+e.getMessage()+"'}");
            throw e;
        }
    }

    @Override
    public void registrar(clase clase) {
        try {
            claseDao.registrar(clase);
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:ClaseService.registrar:"+e.getMessage()+"'}");
            throw e;
        }
    }

    @Override
    public void actualizar(clase clase) {
        try {
            claseDao.actualizar(clase);
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:ClaseService.actualizar:"+e.getMessage()+"'}");
            throw e;
        }
    }

    @Override
    public List<clase> lista(int curso){
        try {
            return claseDao.lista(curso);
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:ClaseService.lista:"+e.getMessage()+"'}");
            throw e;
        }
    }

    @Override
    public clase detalle(int claseId) {
        try {
            return claseDao.detalle(claseId);
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:ClaseService.lista:"+e.getMessage()+"'}");
            throw e;
        }
    }
    
}
