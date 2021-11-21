package com.OpenClassRest.OpenClass.Entity;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="clase")
public class clase {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "claseID")
    public int claseID;

    @JoinColumn(name = "fk_cursoClase", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public curso curso;

    @Column(name = "claseDescripcion", length=300)
    @NotBlank(message="El campo descripcion no puede estar vacio.")
    public String claseDescripcion;

    @Column(name = "claseNombre", length=50)
    
    @NotBlank(message="El campo nombre no puede estar vacio.")
    public String claseNombre;

    @Column(name = "claseUrl", length=200)
    @NotBlank(message="El campo url no puede estar vacio.")
    public String claseUrl;

    @Column(name = "claseEliminado",columnDefinition = "bit default false")
    public Boolean claseEliminado;
}
