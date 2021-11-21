package com.OpenClassRest.OpenClass.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import com.OpenClassRest.OpenClass.Entity.usuario;
import com.OpenClassRest.OpenClass.Interface.Dao.IUsuarioDao;

import org.springframework.stereotype.Service;

@Service("usuarioDao")
@Transactional
public class UsuarioDao implements IUsuarioDao{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    @SuppressWarnings("unchecked")
    public usuario login(String correo, String password) {
        usuario usuario = null;
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("login");
            query.registerStoredProcedureParameter("correo", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("passwordlb", String.class, ParameterMode.IN);
            query.setParameter("correo", correo);
            query.setParameter("passwordlb", password);
            query.execute();
            List<Object[]> result = query.getResultList();
            for(Object[] res:result){
                usuario = new usuario();
                usuario.setUsuarioId((int)res[0]);
                usuario.setUsuarioNombres((String)res[1]);
                usuario.setUsuarioApellidos((String)res[2]);
                usuario.setUsuarioTipo((String)res[3]);
                usuario.setUsuarioCorreo((String)res[4]);
            }
            return usuario;
        } catch (Exception e) {
             System.out.println("error:{msg:'Error:usuarioDao.Registrar:"+e.getMessage()+"'}");
             throw e;
        }       
    }

    @Override
    public void registrar(usuario usuario) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("crear_usuario");
            query.registerStoredProcedureParameter("nombre", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("apellido", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("correo", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("passwordlb", String.class, ParameterMode.IN);
            query.setParameter("nombre", usuario.usuarioNombres);
            query.setParameter("apellido", usuario.usuarioApellidos);
            query.setParameter("correo", usuario.usuarioCorreo);
            query.setParameter("passwordlb", usuario.usuarioPassword);
            query.execute();
        } catch (Exception e) {
             System.out.println("error:{msg:'Error:usuarioDao.Registrar:"+e.getMessage()+"'}");
             throw e;
        } 
    }

    @Override
    public void actualizar(usuario usuario) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("actualizar_usuario");
            query.registerStoredProcedureParameter("idUsuario", int.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("nombre", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("apellido", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("correo", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("passwordlb", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("foto", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("institucion", String.class, ParameterMode.IN);
            query.setParameter("idUsuario", usuario.usuarioId);
            query.setParameter("nombre", usuario.usuarioNombres);
            query.setParameter("apellido", usuario.usuarioApellidos);
            query.setParameter("correo", usuario.usuarioCorreo);
            query.setParameter("passwordlb", usuario.usuarioPassword);
            query.setParameter("foto", usuario.usuarioFoto);
            query.setParameter("institucion", usuario.usuarioInstitucion);
            query.execute();
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:usuarioDao.Registrar:"+e.getMessage()+"'}");
            throw e;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public usuario detalle(int usuarioId) {
        try {
            usuario usuario = null;
            StoredProcedureQuery query = em.createStoredProcedureQuery("obtieneUsuario_usuario");
            query.registerStoredProcedureParameter("usuario", Integer.class, ParameterMode.IN);
            query.setParameter("usuario", usuarioId);
            query.execute();
            List<Object[]> result = query.getResultList();
            for(Object[] res:result){
                usuario = new usuario();
                usuario.setUsuarioId((int)res[0]);
                usuario.setUsuarioNombres((String)res[1]);
                usuario.setUsuarioApellidos((String)res[2]);
                usuario.setUsuarioTipo((String)res[3]);
                usuario.setUsuarioCorreo((String)res[4]);
                usuario.setUsuarioPassword((String)res[5]);
                usuario.setUsuarioFoto((String) res[6]);
                usuario.setUsuarioInstitucion((String) res[7]);
            }
            return usuario;
        } catch (Exception e) {
             System.out.println("error:{msg:'Error:usuarioDao.Registrar:"+e.getMessage()+"'}");
             throw e;
        }
    }

    @Override
    public void actualizarMaestro(int usuarioId, String institucion) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("actualizarMaestro_usuario");
            query.registerStoredProcedureParameter("usuario", int.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("institucion", String.class, ParameterMode.IN);
            query.setParameter("usuario", usuarioId);
            query.setParameter("institucion", institucion);
            query.execute();
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:usuarioDao.Registrar:"+e.getMessage()+"'}");
            throw e;
        }
    }

    @Override
    public void confirmarCorreo(String correo) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("confirmar_usuario");
            query.registerStoredProcedureParameter("correo", String.class, ParameterMode.IN);
            query.setParameter("correo", correo);
            query.execute();
        } catch (Exception e) {
            System.out.println("error:{msg:'Error:usuarioDao.Registrar:"+e.getMessage()+"'}");
            throw e;
        }
    }
    
    

}
