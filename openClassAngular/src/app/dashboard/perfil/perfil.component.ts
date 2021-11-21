import { Component, OnInit } from '@angular/core';
import { usuario } from 'src/app/Data/EntityStruct';
import { UsuarioService } from '../../Data/Service/UsuarioService';
import swal from 'sweetalert2';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FileService } from '../../Data/Service/FileService';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.scss']
})
export class PerfilComponent implements OnInit {
  usuario: usuario = new usuario(); // sirve para tener los datos del usuario ingresado
  usuarioDetalle: usuario = new usuario();
  usuarioForm: FormGroup;
  constructor(private usuarioService: UsuarioService, private fileService: FileService) { }

  ngOnInit(): void {
    this.usuario = JSON.parse(localStorage.getItem('userSession'));
    this.detalleUsuario();
    this.usuarioForm = new FormGroup({
      nombre: new FormControl(''),
      apellidos: new FormControl(''),
      correo: new FormControl(''),
      foto: new FormControl(''),
      password: new FormControl('')
    });
  }

  detalleUsuario() {
    this.usuarioService.detalle(this.usuario.usuarioId).subscribe(r => {
      this.usuarioDetalle = r;
    }, err => {
      swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: err
      })
    });
  }


  urlImage;
  formatImagen;
  fileImage: File;
  selectMiniatura(event) {
    this.fileImage = event.target.files[0];
    if (this.fileImage != null) {
      var reader = new FileReader();
      reader.readAsDataURL(this.fileImage);
      if (this.fileImage.type.indexOf('image') > -1) {
        this.formatImagen = 'imagen'
      }
      reader.onload = (event) => {
        this.urlImage = (<FileReader>event.target).result;
      }
    }
  }


  acutalizarPerfil() {
    this.usuarioService.actualizar(this.usuarioDetalle).subscribe(r => {
      swal.fire({
        icon: 'success',
        title: 'Hecho',
        text: 'Perfil actualizado correctamente'
      });
    }, err => {
      swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: err
      });
    });
  }

  actualizaFoto() {
    if(this.fileImage!=undefined){
      this.fileService.upload(this.fileImage, 'perfil').subscribe(r => {
        this.usuarioDetalle.usuarioFoto = r.response;
        this.acutalizarPerfil();
      }, err => {
        swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: err
        });
      });
    }else{
      this.acutalizarPerfil();
    }
    
  }
}
