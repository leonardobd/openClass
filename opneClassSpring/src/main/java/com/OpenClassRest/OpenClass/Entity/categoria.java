package com.OpenClassRest.OpenClass.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="categoria")
public class categoria {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "categoriaId")
    public int categoriaId;
    
    @Column(name = "categoriaNombre", length=50)
    @NotBlank(message="El campo nombre no puede estar vacio.")
    public String categoriaNombre;
    
    @Column(name = "categoriaEliminado",columnDefinition = "bit default false")
    public Boolean categoriaEliminado;
}
