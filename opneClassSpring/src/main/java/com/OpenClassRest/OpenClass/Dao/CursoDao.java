package com.OpenClassRest.OpenClass.Dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.OpenClassRest.OpenClass.Entity.categoria;
import com.OpenClassRest.OpenClass.Entity.clase;
import com.OpenClassRest.OpenClass.Entity.curso;
import com.OpenClassRest.OpenClass.Entity.usuario;
import com.OpenClassRest.OpenClass.Interface.Dao.ICursoDao;

import org.springframework.stereotype.Repository;

@Repository("cursoDao")
public class CursoDao implements ICursoDao{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    @SuppressWarnings("unchecked")
    public List<curso> lista() {
        try {
            List<curso> cursos = new ArrayList<curso>();
            StoredProcedureQuery query = em.createStoredProcedureQuery("listar_curso");
            query.execute();
            List<Object[]> result = query.getResultList();
            for(Object[] res:result){
                curso curso = new curso();
                curso.setCursoId((int) res[0]);
                curso.setCursoNombre((String) res[1]);
                curso.setCursoMinuatura((String) res[2]);
                categoria categoria = new categoria();
                categoria.setCategoriaId((int) res[3]);
                curso.setCategoria(categoria);
                curso.setCursoEstado((String) res[4]);
                cursos.add(curso);
            }
            return cursos;
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:CursoDao.lista:"+e.getMessage()+"'}");
            throw e;
        }
    }

    @Override
    public List<curso> listaByCategoria(int categoriaId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int registrar(curso curso) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("crear_curso");
            query.registerStoredProcedureParameter("nombre", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("usuario", int.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("categoria", int.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("fechaInicio", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("sesiones", int.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("miniatura", String.class, ParameterMode.IN);
            query.setParameter("nombre", curso.cursoNombre);
            query.setParameter("usuario", curso.creador.usuarioId);
            query.setParameter("categoria", curso.categoria.categoriaId);
            query.setParameter("fechaInicio", curso.cursoFechaInicio);
            query.setParameter("sesiones", curso.cursoNumeroSesiones);
            query.setParameter("miniatura", curso.cursoMinuatura);
            query.execute();
            List<BigInteger> result = query.getResultList();
             int idCurso = 0;
             for(BigInteger res:result){
                 idCurso = res.intValue();
             }
             return idCurso;
         } catch (Exception e) {
             System.out.println("error:{msg:'Error:CursoDao.Registrar:"+e.getMessage()+"'}");
             throw e;
         }
    }

    @Override
    public void editar(curso curso) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void eliminar(int cursoId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<curso> listaByCreador(int idUsuario) {
        try {
            List<curso> cursos = new ArrayList<curso>();
            StoredProcedureQuery query = em.createStoredProcedureQuery("listarPorUsuario_curso");
            query.registerStoredProcedureParameter("usuario", int.class, ParameterMode.IN);
            query.setParameter("usuario", idUsuario);
            query.execute();
            List<Object[]> result = query.getResultList();
            for(Object[] res:result){
                curso curso = new curso();
                curso.setCursoId((int) res[0]);
                curso.setCursoNombre((String) res[1]);
                curso.setCursoEstado((String) res[2]);
                categoria categoria = new categoria();
                categoria.setCategoriaId((int) res[3]);
                categoria.setCategoriaNombre((String) res[4]);
                curso.setCategoria(categoria);
                cursos.add(curso);
            }
            return cursos;
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:CursoDao.lista:"+e.getMessage()+"'}");
            throw e;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<clase> detalle(int cursoId) {
        try {
            /* El detalle involucra la lista de clases y el detalle del curso
            */
            List<clase> cursos = new ArrayList<clase>();
            StoredProcedureQuery query = em.createStoredProcedureQuery("detalle_curso");
            query.registerStoredProcedureParameter("cursoId", int.class, ParameterMode.IN);
            query.setParameter("cursoId", cursoId);
            query.execute();
            List<Object[]> result = query.getResultList();
            for(Object[] res:result){
                clase clase = new clase();
                clase.setClaseID((int)  res[0]);
                clase.setClaseNombre((String) res[1]);
                clase.setClaseDescripcion((String) res[2]);
                clase.setClaseUrl((String) res[3]);

                curso curso = new curso();
                curso.setCursoId((int) res[4]);
                curso.setCursoNombre((String) res[5]);
                curso.setCursoMinuatura((String) res[6]);
                curso.setCursoFechaInicio((Date) res[7]);
                curso.setCursoNumeroSesiones((int) res[8]);
                clase.setCurso(curso);

                categoria categoria = new categoria();
                categoria.setCategoriaId((int) res[9]);
                categoria.setCategoriaNombre((String) res[10]);
                curso.setCategoria(categoria);

                usuario usuario = new usuario();
                usuario.setUsuarioId((int) res[11]);
                usuario.setUsuarioNombres((String) res[12]);
                usuario.setUsuarioApellidos((String) res[13]);
                usuario.setUsuarioFoto((String) res[14]);
                curso.setCreador(usuario);

                cursos.add(clase);
            }
            return cursos;
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:CursoDao.lista:"+e.getMessage()+"'}");
            throw e;
        }
    }
    
}
