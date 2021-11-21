package com.OpenClassRest.OpenClass.Dao;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.OpenClassRest.OpenClass.Entity.curso;
import com.OpenClassRest.OpenClass.Entity.cursoRegistro;
import com.OpenClassRest.OpenClass.Interface.Dao.ICursoRegistroDao;

import org.springframework.stereotype.Repository;

@Repository("cursoRegistradoDao")
public class CursoRegistroDao implements ICursoRegistroDao{

    @PersistenceContext
    private EntityManager em;

    
    @Override
    public void registro(cursoRegistro cursoRegistro) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("registrar_cursoRegistro");
            query.registerStoredProcedureParameter("usuario", int.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("curso", int.class, ParameterMode.IN);
            query.setParameter("usuario", cursoRegistro.alumno.usuarioId);
            query.setParameter("curso", cursoRegistro.curso.cursoId);
            query.execute();
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:CursoRegistroDao.registro:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @Override
    public Boolean verificaRegistro(int usuarioId, int cursoId) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("validaCursoPorUsuario_cursoRegistro");
            query.registerStoredProcedureParameter("usuarioId", int.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("cursoId", int.class, ParameterMode.IN);
            query.setParameter("usuarioId", usuarioId);
            query.setParameter("cursoId", cursoId);
            query.execute();
            List<BigInteger> result = query.getResultList();
            int cantidad = 0;
            for(BigInteger res:result){
                cantidad = res.intValue();
            }
            if(cantidad>0) return true; else return false;
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:IClaseDao.lista:"+e.getMessage()+"'}");
            throw e;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<cursoRegistro> listaRegistrosByUsuario(int usuarioId) {
        try {
            List<cursoRegistro> cursosRegistro = new ArrayList<cursoRegistro>();
            StoredProcedureQuery query = em.createStoredProcedureQuery("listaPorUsuarios_cursoRegistro");
            query.registerStoredProcedureParameter("usuarioId", int.class, ParameterMode.IN);
            query.setParameter("usuarioId", usuarioId);
            query.execute();
            List<Object[]> result = query.getResultList();
            for(Object[] res:result){
                cursoRegistro cursoRegistro = new cursoRegistro();
                cursoRegistro.setCursoRegistroId((int) res[0]);
                curso curso = new curso();
                curso.setCursoId((int) res[1]);
                curso.setCursoNombre((String) res[2]);
                cursoRegistro.setCurso(curso);
                cursosRegistro.add(cursoRegistro);
            }
            return cursosRegistro;
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:IClaseDao.lista:"+e.getMessage()+"'}");
            throw e;
        }
    }

   
    
}
