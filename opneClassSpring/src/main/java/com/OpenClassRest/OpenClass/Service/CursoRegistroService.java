package com.OpenClassRest.OpenClass.Service;

import java.util.List;

import javax.transaction.Transactional;

import com.OpenClassRest.OpenClass.Entity.cursoRegistro;
import com.OpenClassRest.OpenClass.Interface.Dao.ICursoRegistroDao;
import com.OpenClassRest.OpenClass.Interface.Service.ICursoRegistroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cursoRegistroService")
@Transactional 
public class CursoRegistroService implements ICursoRegistroService{

    @Autowired
    ICursoRegistroDao cursoRegistroDao;
    @Override
    public void registro(cursoRegistro cursoRegistro) {
        cursoRegistroDao.registro(cursoRegistro);
    }

    @Override
    public Boolean verificaRegistro(int usuarioId, int cursoId) {
        return cursoRegistroDao.verificaRegistro(usuarioId, cursoId);
    }

    @Override
    public List<cursoRegistro> listaRegistrosByUsuario(int usuarioId) {
        return cursoRegistroDao.listaRegistrosByUsuario(usuarioId);
    }

    
}
