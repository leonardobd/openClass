package com.OpenClassRest.OpenClass.Entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="curso")
public class curso {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "cursoId")
    public int cursoId;

    @Column(name = "cursoNombre")
    @NotBlank(message="El nombre del curso no puede estar vacio.")
    @Size(min = 1, message = "El campo nombre no puede estar vacio.")
    public String cursoNombre;

    @JoinColumn(name = "fk_creadorCurso")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public usuario creador; /*ID del usuario tipo maestro que creará cursos*/

    @JoinColumn(name = "fk_categoriaCurso")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public categoria categoria;

    @Column(name = "cursoFechaInicio")
    @NotBlank(message="El inicio del curso no puede estar vacio.")
    @Future
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public Date cursoFechaInicio;

    @Column(name = "cursoNumeroSesiones")
    @Size(min = 2)
    @NotBlank(message="No se puede registrar menos de 2 sesiones.")
    public int cursoNumeroSesiones;
    
    @Column(name = "cursoMinuatura",columnDefinition = "varchar(200) default 'https://www.dropbox.com/s/4gluhj7u8hbpjbc/miniaturaDefault.png?raw=1'")
    public String cursoMinuatura;

    @Column(name = "cursoEstado", length=1,columnDefinition = "varchar(1) default 'A'") 
    public String cursoEstado;
}
