package com.OpenClassRest.OpenClass.Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.OpenClassRest.OpenClass.Entity.categoria;
import com.OpenClassRest.OpenClass.Interface.Dao.ICategoriaDao;

import org.springframework.stereotype.Repository;

@Repository("categoriaDao")
public class CategoriaDao implements ICategoriaDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<categoria> lista() {
        try {
            List<categoria> categorias = new ArrayList<categoria>();
            StoredProcedureQuery query = em.createStoredProcedureQuery("listar_categoria");
            query.execute();
            List<Object[]> result = query.getResultList();
            for(Object[] res:result){
                categoria categoria = new categoria();
                categoria.setCategoriaId((int) res[0]);
                categoria.setCategoriaNombre((String)res[1]);
                categorias.add(categoria);
            }
            return categorias;
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:CursoDao.listarPorUsuario:"+e.getMessage()+"'}");
            throw e;
        }
    }
    
}
