package com.OpenClassRest.OpenClass.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="usuario")
public class usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "usuarioId")
    public int usuarioId;
    
    @Column(name = "usuarioNombres", length=100)
    @NotEmpty(message="El campo nombre no puede estar vacio.")
    public String usuarioNombres;
    
    @Column(name = "usuarioApellidos", length=100)
    @NotEmpty(message="El campo apellido no puede estar vacio.")
    public String usuarioApellidos;
    
    @Column(name = "usuarioTipo", columnDefinition ="VARCHAR(2) default 'CE'") //tipos: confirmar email, usuario activo, usuario inactivo
    public String usuarioTipo;
    
    @Column(name = "usuarioCorreo", length=70,unique = true)
    @Email(message = "El campo email no tiene el formato correcto.")
    @NotEmpty(message="El campo email no puede estar vacio.")
    public String usuarioCorreo;

    @Column(name = "usuarioPassword", length=70)
    @NotEmpty(message="El campo contraseña no puede estar vacio.")
    public String usuarioPassword;

    @Column(name = "usuarioInstitucion", length=90)
    public String usuarioInstitucion;

    @Column(name = "usuarioEliminado",columnDefinition = "bit default false")
    public Boolean usuarioEliminado;
    
    @Column(name = "usuarioFoto",columnDefinition = "varchar(200) default 'https://www.dropbox.com/s/utbuh1v5hkz5l13/default.png?raw=1'")
    public String usuarioFoto;
}
