package com.OpenClassRest.OpenClass.Service;

import java.util.List;

import javax.transaction.Transactional;

import com.OpenClassRest.OpenClass.Entity.categoria;
import com.OpenClassRest.OpenClass.Interface.Dao.ICategoriaDao;
import com.OpenClassRest.OpenClass.Interface.Service.ICategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoriaService")
@Transactional 
public class CategoriaService implements ICategoriaService{
    @Autowired
    ICategoriaDao categoriaDao;
    
    @Override
    public List<categoria> lista() {
        return categoriaDao.lista();
    }
    
}
