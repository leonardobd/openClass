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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cursoRegistro")
public class cursoRegistro {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "cursoRegistroId")
    public int cursoRegistroId;

    @JoinColumn(name = "alumno")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public usuario alumno;  

    @JoinColumn(name = "fk_cursoCRegistro")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public curso curso; 

    @Column(name = "cursoRegistrooEstado", length=1,columnDefinition = "varchar(1) default 'A'")
    public String cursoRegistrooEstado;

    @Column(name = "cursoRegistroEliminado", length=1,columnDefinition = "bit default 0")
    public Boolean cursoRegistroEliminado;
}
