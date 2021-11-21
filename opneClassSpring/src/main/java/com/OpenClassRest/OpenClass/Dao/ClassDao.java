package com.OpenClassRest.OpenClass.Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.OpenClassRest.OpenClass.Entity.clase;
import com.OpenClassRest.OpenClass.Interface.Dao.IClaseDao;

import org.springframework.stereotype.Repository;

@Repository("claseDao")
public class ClassDao implements IClaseDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<clase> lista(int curso) {
        try {
            List<clase> clases = new ArrayList<clase>();
            StoredProcedureQuery query = em.createStoredProcedureQuery("listarClasePorCurso");
            query.registerStoredProcedureParameter("curso", int.class, ParameterMode.IN);
            query.setParameter("curso", curso);
            query.execute();
            List<Object[]> result = query.getResultList();
            for(Object[] res:result){
                clase clase = new clase();
                clase.setClaseID((int) res[0]);
                clase.setClaseUrl((String) res[1]);
                clase.setClaseNombre((String) res[2]);
                clases.add(clase);
            }
            return clases;
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:IClaseDao.lista:"+e.getMessage()+"'}");
            throw e;
        }
    }

    @Override
    public void registrar(clase clase) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("registrar_clase");
            query.registerStoredProcedureParameter("curso", int.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("descripcion", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("nombre", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("url", String.class, ParameterMode.IN);
            query.setParameter("curso", clase.curso.cursoId);
            query.setParameter("descripcion", clase.claseDescripcion);
            query.setParameter("nombre", clase.claseNombre);
            query.setParameter("url", clase.claseUrl);
            query.execute();
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:ClassDao.registrar:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @Override
    public void actualizar(clase clase) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("editar_clase");
            query.registerStoredProcedureParameter("clase", int.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("curso", int.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("descripcion", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("nombre", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("url", String.class, ParameterMode.IN);
            query.setParameter("clase", clase.claseID);
            query.setParameter("curso", clase.curso.cursoId);
            query.setParameter("descripcion", clase.claseDescripcion);
            query.setParameter("nombre", clase.claseNombre);
            query.setParameter("url", clase.claseUrl);
            query.execute();
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:ClassDao.registrar:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @Override
    public void eliminar(int claseId) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("eliminar_clase");
            query.registerStoredProcedureParameter("clase", int.class, ParameterMode.IN);
            query.setParameter("clase", claseId);
            query.execute();
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:ClassDao.eliminar:" + e.getMessage() + "'}");
            throw e;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public clase detalle(int claseId) {
        try {
            clase clase = null;
            StoredProcedureQuery query = em.createStoredProcedureQuery("detalle_clase");
            query.registerStoredProcedureParameter("clase", int.class, ParameterMode.IN);
            query.setParameter("clase", claseId);
            query.execute();
            List<Object[]> result = query.getResultList();
            for(Object[] res:result){
                clase = new clase();
                clase.setClaseID((int) res[0]);
                clase.setClaseUrl((String) res[1]);
                clase.setClaseNombre((String) res[2]);
                clase.setClaseDescripcion((String) res[3]);
                clase.setClaseEliminado((Boolean) res[4]);
            }
            return clase;
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:IClaseDao.detalle:"+e.getMessage()+"'}");
            throw e;
        }
    }


}
