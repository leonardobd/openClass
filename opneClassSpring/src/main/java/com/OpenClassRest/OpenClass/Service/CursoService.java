package com.OpenClassRest.OpenClass.Service;

import java.util.List;

import javax.transaction.Transactional;

import com.OpenClassRest.OpenClass.Entity.clase;
import com.OpenClassRest.OpenClass.Entity.curso;
import com.OpenClassRest.OpenClass.Interface.Dao.IClaseDao;
import com.OpenClassRest.OpenClass.Interface.Dao.ICursoDao;
import com.OpenClassRest.OpenClass.Interface.Service.ICursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cursoService")
@Transactional
public class CursoService implements ICursoService {

    @Autowired
    ICursoDao cursoDao;

    @Autowired
    IClaseDao claseDao;

    @Override
    public int registro(clase clase) {
        /*
         * Al registrar un curso nuevo se tiene que registrar por defecto el primer
         * video introductorio del curso por eso la clase que pasa es clase y no curso
         */
        try {
            int cursoId = cursoDao.registrar(clase.curso);
            clase.curso.cursoId = cursoId;
            clase.claseNombre = "Introduccion al curso";
            claseDao.registrar(clase);
            return cursoId;
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:CursoService.registro:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @Override
    public List<curso> lista() {
        try {
            return cursoDao.lista();
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:CursoService.lista:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @Override
    public List<clase> detalle(int cursoId) {
        return cursoDao.detalle(cursoId);
    }

    @Override
    public List<curso> listaByCreador(int usuarioId) {
        return cursoDao.listaByCreador(usuarioId);
    }

}
