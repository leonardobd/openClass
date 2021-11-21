import { Component, OnInit } from '@angular/core';
import { categoria, curso } from 'src/app/Data/EntityStruct';
import { CategoriaService } from '../../Data/Service/CategoriaService';
import { CursoService } from '../../Data/Service/CursoService';
import swal from 'sweetalert2';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-curso',
  templateUrl: './curso.component.html',
  styleUrls: ['./curso.component.scss']
})
export class CursoComponent implements OnInit {

  constructor(private categoriaService: CategoriaService, private CursoService:CursoService) { }
  categorias:categoria[]=[];
  cursos:curso[]=[];
  cursosMuestra:curso[]=[];
  buscar:string='';

  ngOnInit(): void {
    this.listaCategorias();
    this.listaCursos();
  }

  listaCategorias(){
    this.categoriaService.lista().subscribe(response=>{
      this.categorias = response;
    },err=>{
      swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'No se han podido cargar las categorias'
      })
    });
  }

  listaCursos(){
    this.CursoService.lista().subscribe(response=>{
        this.cursos = response;
        this.cursosMuestra = response;
    }, err=>{
      swal.fire({
        icon: 'error',
        title: 'Oops...', 
        text: 'No se han podido cargar las clases'
      })
    });
  }

  filtrarPorNombre(){
    this.cursosMuestra = [];
    this.cursos.forEach(r=>{
      if(r.cursoNombre.toLocaleLowerCase().indexOf(this.buscar.toLocaleLowerCase())>-1){
        this.cursosMuestra.push(r);
      }
    });
  }

  filtrarCategoria(categoria:number){
    this.cursosMuestra = this.cursos.filter(x=>x.categoria.categoriaId==categoria);
  }

  resetearFiltro(){
    this.cursosMuestra=this.cursos;
    this.buscar="";

  }

}
