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
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="comentario")
public class comentario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "comentarioid")
    public int comentarioID;

    @JoinColumn(name = "fk_claseComentario")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public clase clase;

    @JoinColumn(name = "fk_usuarioComentario")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public usuario usuario;

    @Column(name = "comentariodetalle", length=300)
    @NotBlank(message="El campo descripcion no puede estar vacio.")
    public String comentarioDetalle;

    @Column(name = "comentariofecha", columnDefinition = "DATETIME default now()")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    public Date comentarioFecha;

    @Column(name = "comentarioestado",columnDefinition = "bit default false")
    public String comentarioEstado;
}
