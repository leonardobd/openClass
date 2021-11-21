package com.OpenClassRest.OpenClass.Dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.OpenClassRest.OpenClass.Entity.comentario;
import com.OpenClassRest.OpenClass.Entity.usuario;
import com.OpenClassRest.OpenClass.Interface.Dao.IComentarioDao;

import org.springframework.stereotype.Repository;

@Repository("comentarioDao")
public class ComentarioDao implements IComentarioDao{
    @PersistenceContext
    private EntityManager em;

    
    @Override
    @SuppressWarnings("unchecked")
    public List<comentario> lista(int claseId) {
        try {
            List<comentario> comentarios = new ArrayList<comentario>();
            StoredProcedureQuery query = em.createStoredProcedureQuery("lista_comentarios");
            query.registerStoredProcedureParameter("claseId", int.class, ParameterMode.IN);
            query.setParameter("claseId", claseId);
            query.execute();
            List<Object[]> result = query.getResultList();
            for(Object[] res:result){
                comentario comentario = new comentario();
                comentario.setComentarioID((int) res[0]);
                Timestamp fecha = (Timestamp) res[1];
                Date date=new Date(fecha.getTime());
                comentario.setComentarioFecha(date);
                comentario.setComentarioDetalle((String) res[2]);
                usuario usuario = new usuario();
                usuario.setUsuarioNombres((String) res[3]);
                usuario.setUsuarioApellidos((String) res[4]);
                usuario.setUsuarioFoto((String) res[5]);
                comentario.setUsuario(usuario);
                comentarios.add(comentario);
            }
            return comentarios;
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:IClaseDao.lista:"+e.getMessage()+"'}");
            throw e;
        }
    }

    @Override
    public void registrar(comentario comentario) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("agregar_comentario");
            query.registerStoredProcedureParameter("claseId", int.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("usuario", int.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("detalle", String.class, ParameterMode.IN);
            query.setParameter("claseId", comentario.clase.claseID);
            query.setParameter("usuario", comentario.usuario.usuarioId);
            query.setParameter("detalle", comentario.comentarioDetalle);
            query.execute();
            
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:IClaseDao.lista:"+e.getMessage()+"'}");
            throw e;
        }
    }

    
}
