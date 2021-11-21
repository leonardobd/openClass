import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { clase, usuario, comentario } from 'src/app/Data/EntityStruct';
import { forkJoin } from 'rxjs';
import swal from 'sweetalert2';

import { ClaseService } from '../../Data/Service/ClaseService';
import { ComentarioService } from '../../Data/Service/ComentarioService';

@Component({
  selector: 'app-detalle-clase',
  templateUrl: './detalle-clase.component.html',
  styleUrls: ['./detalle-clase.component.scss']
})
export class DetalleClaseComponent implements OnInit {
  claseIdUrl: number = 0;
  cursoIdUrl: number = 0;
  usuario: usuario = new usuario(); // sirve para tener los datos del usuario ingresado
  clase: clase = new clase(); //guarda la clase que se esta observando
  listaClases: clase[] = []; //guarda la lista de clases del curso
  comentarios: comentario[] = [];//guarda los comentarios de la clase que se esta observando
  nuevoComentario:comentario = new comentario();
  constructor(private route: ActivatedRoute, private claseService: ClaseService, private comentarioService: ComentarioService) { }

  ngOnInit(): void {
    this.cargaDataInicial();
  }

  cargaDataInicial() {
    this.claseIdUrl = parseInt(this.route.snapshot.paramMap.get("claseId"));
    this.cursoIdUrl = parseInt(this.route.snapshot.paramMap.get("cursoId"));
    this.usuario = JSON.parse(localStorage.getItem('userSession'));
    this.cargaDataBasica();
  }

  cargaDataBasica() {
    let obtieneComentariosLista = this.comentarioService.listar(this.claseIdUrl);
    forkJoin([this.obtieneClaseDetalle(this.claseIdUrl), this.obtieneClaseLista(this.cursoIdUrl), obtieneComentariosLista]).subscribe(r => {
      let captureResponse:any=r;
      this.clase = captureResponse[0];
      this.listaClases = captureResponse[1];
      this.comentarios = captureResponse[2];
      console.log(this.clase);
      console.log(this.listaClases);
      console.log(this.comentarios);
    }, err => {
      swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: err
      });
    });
  }

  obtieneClaseDetalle(claseId: number): any {
    return this.claseService.detalle(claseId);
  }

  obtieneClaseLista(cursoId: number): any {
    return this.claseService.listaPorCurso(cursoId);
  }

  obtieneComentariosLista(claseId: number) {
    this.comentarioService.listar(claseId).subscribe(response => {
      this.comentarios = response;
    }, err => {
      swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: err
      });
    });
  }

  agregarComentario(){
    let claseNueva:clase = new clase();
    claseNueva.claseID = this.claseIdUrl;
    this.nuevoComentario.clase = claseNueva;
    let usuarioNueva:usuario = new usuario();
    usuarioNueva.usuarioId= this.usuario.usuarioId;
    this.nuevoComentario.usuario = usuarioNueva;
    
    this.comentarioService.agregar(this.nuevoComentario).subscribe(r=>{
      this.nuevoComentario = new comentario();
      this.obtieneComentariosLista(this.claseIdUrl);
      swal.fire({
        icon: 'success',
        title: 'Hecho',
        text: 'Comentario hecho correctamente'
      });
    },err=>{
      swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: err
      });
    });
  }

}
