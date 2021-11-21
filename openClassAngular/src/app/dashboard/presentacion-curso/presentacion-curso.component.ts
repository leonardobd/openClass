import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { clase, curso, cursoRegistro, usuario } from 'src/app/Data/EntityStruct';
import swal from 'sweetalert2';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { CursoService } from '../../Data/Service/CursoService';
import { ClaseService } from '../../Data/Service/ClaseService';
import { FileService } from 'src/app/Data/Service/FileService';
import { CursoRegistroService } from 'src/app/Data/Service/CursoRegistroService';

@Component({
  selector: 'app-presentacion-curso',
  templateUrl: './presentacion-curso.component.html',
  styleUrls: ['./presentacion-curso.component.scss']
})
export class PresentacionCursoComponent implements OnInit {

  //#region  variables
  cursoIdUrl: number = 0;
  cursoDetalle: clase[] = [];
  cursoDatos: curso = new curso(); //volcamos aqui la data del primer registro del cursoDetalle con los datos basicos del curso
  validaPropiedadCurso: Boolean = false;
  usuario: usuario = new usuario(); // sirve para tener los datos del usuario ingresado
  usuarioPropietarioCurso: usuario = new usuario(); //volcamos aqui la data del primer registro del cursoDetalle con los datos basicos del usuario creador del curso
  cargaVideo: Boolean = false; //Estado del icono al subir el video
  cargaVideoAgregar: Boolean = false; //Estado del icono al subir el video en agregar clase
  urlVideo; //base64 local al seleccionar un video
  urlVideoAgregar; //base64 local al seleccionar un video en agregar clase
  fileVideo: File;//archivo donde se guarda el video seleccionado
  fileVideoAgregar: File;//archivo donde se guarda el video seleccionado en agregar clase
  formatVideo; //tipo de archivo subido - solo acepta video
  formatVideoAgregar; //tipo de archivo subido - solo acepta video en agregar clase
  verificaUsuarioRegistrado:boolean=false;
  //#endregion

  constructor(
    private route: ActivatedRoute, 
    private cursoService: CursoService, 
    private claseService: ClaseService, 
    private fileService: FileService, 
    private cursoRegistroService: CursoRegistroService) { }

  ngOnInit(): void {
    this.cargaDatosIniciales();
  }

  //#region CargaData inicial
  cargaDatosIniciales() {
    this.cursoIdUrl = parseInt(this.route.snapshot.paramMap.get("cursoId"));
    this.usuario = JSON.parse(localStorage.getItem('userSession'));
    this.cargaDetalleCurso();
    this.validaRegistro(this.usuario.usuarioId,this.cursoIdUrl);
    this.editarClase = new FormGroup({
      descripcion: new FormControl('', [Validators.minLength(1), Validators.maxLength(300)]),
      nombre: new FormControl('', [Validators.minLength(1), Validators.maxLength(50)]),
      url: new FormControl('', [Validators.minLength(1), Validators.maxLength(200)])

    });
    this.agregarFrmClase = new FormGroup({
      descripcion: new FormControl('', [Validators.minLength(1), Validators.maxLength(300)]),
      nombre: new FormControl('', [Validators.minLength(1), Validators.maxLength(50)]),
    });
  }

  cargaDetalleCurso() {
    this.cursoService.detalleCurso(this.cursoIdUrl).subscribe(response => {
      this.cursoDetalle = response;
      //Valida si este curso es del usuario logeado
      this.validacionCursoPropiedad(this.cursoDetalle[0].curso.creador.usuarioId);
      //Volcado de data a cursoDatos
      this.cursoDatos = this.cursoDetalle[0].curso;
      //Volcado de data a usuarioPropietarioCurso
      this.usuarioPropietarioCurso = this.cursoDetalle[0].curso.creador;
    }, err => {
      swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: err
      })
    });
  }

  validacionCursoPropiedad(usuarioID: number) {
    if (this.usuario.usuarioId == usuarioID) this.validaPropiedadCurso = true;
    else this.validaPropiedadCurso = false;
  }

  //#endregion

  //#region  cursoRegistro
  registarme() {
    let cursoRegistroNuevo:cursoRegistro = new cursoRegistro();

    let cursoNuevo:curso=new curso();
    cursoNuevo.cursoId = this.cursoIdUrl;
    cursoRegistroNuevo.curso = cursoNuevo;

    let usuarioNuevo:usuario = new usuario();
    usuarioNuevo.usuarioId = this.usuario.usuarioId;
    cursoRegistroNuevo.alumno = usuarioNuevo;

    this.cursoRegistroService.registrarme(cursoRegistroNuevo).subscribe(r => {
      cursoRegistroNuevo = new cursoRegistro();
      swal.fire({
        icon: 'success',
        title: 'Hecho',
        text: 'Te haz registrado correctamente'
      });
      this.validaRegistro(this.usuario.usuarioId,this.cursoIdUrl);
     }, err => {
      swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: err
      })
    });
  }

  validaRegistro(usuarioId:number, curso:number){
    this.cursoRegistroService.varificaRegistro(usuarioId,curso).subscribe(r => {
      this.verificaUsuarioRegistrado = r as boolean;
     }, err => {
      swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: err
      })
    });
  }
  //#endregion


  //#region Seleccionar video
  selectVideo(event) {
    this.fileVideo = event.target.files[0];
    if (this.fileVideo != null) {
      var reader = new FileReader();
      reader.readAsDataURL(this.fileVideo);
      if (this.fileVideo.type.indexOf('video') > -1) {
        this.formatVideo = 'video'
      }
      reader.onload = (event) => {
        this.urlVideo = (<FileReader>event.target).result;
      }
    }
  }

  selectVideoAgregarClase(event) {
    this.fileVideoAgregar = event.target.files[0];
    if (this.fileVideoAgregar != null) {
      var reader = new FileReader();
      reader.readAsDataURL(this.fileVideoAgregar);
      if (this.fileVideoAgregar.type.indexOf('video') > -1) {
        this.formatVideoAgregar = 'video'
      }
      reader.onload = (event) => {
        this.urlVideoAgregar = (<FileReader>event.target).result;
      }
    }
  }
  //#endregion


  //#region agregar clase
  agregarFrmClase: FormGroup;
  claseAgregar: clase = new clase();
  @ViewChild('inputVideoAgregarClase', { static: false })
  inputVideoAgregarClase: ElementRef;
  @ViewChild('closebuttonAgregar') closebuttonAgregar;
  agregarClase() {
    let cursoTempData: curso = new curso();
    cursoTempData.cursoId = this.cursoDetalle[0].curso.cursoId;
    this.claseAgregar.curso = cursoTempData;
    if (this.claseAgregar.claseUrl == null || this.claseAgregar.claseUrl == undefined || this.claseAgregar.claseUrl == '') {
      if (this.fileVideoAgregar != undefined) {
        this.cargaVideoAgregar = true;
        this.fileService.upload(this.fileVideoAgregar, 'video').subscribe(response => {
          this.claseAgregar.claseUrl = response.response;
          this.cargaVideoAgregar = false;
          this.claseService.agregar(this.claseAgregar).subscribe(response => {
            this.closebuttonAgregar.nativeElement.click();
            this.limpiarAgregarClase();
            swal.fire({
              icon: 'success',
              title: 'Hecho',
              text: 'Clase agregada correctamente.'
            });
            this.cargaDatosIniciales();
          }, err => {
            swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: err
            });
          });
        }, err => {
          this.cargaVideoAgregar = false;
          swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: err
          });
        });
      } else {
        swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'No se ah encontrado ningun video agregado.'
        });
      }
    } else {
      this.claseService.agregar(this.claseAgregar).subscribe(response => {
        this.closebutton.nativeElement.click();
        this.limpiarAgregarClase();
        swal.fire({
          icon: 'success',
          title: 'Hecho',
          text: 'Clase agregada correctamente.'
        });
        this.cargaDatosIniciales();
        this.closebuttonAgregar.nativeElement.click();
      }, err => {
        swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: err
        });
      });
    }
  }
  //#endregion


  //#region  Editar
  editarClase: FormGroup;
  claseEditar: clase = new clase();
  @ViewChild('inputVideoAgregarEditar', { static: false })
  inputVideoAgregarEditar: ElementRef;
  editar(clase: clase) {
    this.claseEditar = clase;
  }

  @ViewChild('closebutton') closebutton;
  actualizar() {
    debugger
    if (this.fileVideo != undefined) {
      this.cargaVideo = true;
      this.fileService.upload(this.fileVideo, 'video').subscribe(
        respuesta => {
          this.cargaVideo = false;
          this.limpiarEditarClase();
          this.claseEditar.claseUrl = respuesta.response;
          this.claseService.editar(this.claseEditar).subscribe(response => {
            this.fileVideo = null;
            this.claseEditar = new clase();
            this.closebutton.nativeElement.click();
            swal.fire({
              icon: 'success',
              title: 'Hecho',
              text: 'Clase actualizada correctamente.'
            });
            this.urlVideo = '';
            this.cargaDatosIniciales();
          }, err => {
            this.cargaVideo = false;
            swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: err
            })
          });
        }, err => {
          swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: err
          })
        }
      );
    } else {
      this.claseService.editar(this.claseEditar).subscribe(response => {
        this.closebutton.nativeElement.click();
        this.fileVideo = null;
        this.claseEditar = new clase();
        this.urlVideo = '';
        swal.fire({
          icon: 'success',
          title: 'Hecho',
          text: 'Clase actualizada correctamente.'
        });
        this.cargaDatosIniciales();
      }, err => {
        swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: err
        })
      });
    }
  }
  //#endregion

  eliminarClase(cursoId: number) {
    swal.fire({
      title: 'Estas seguro?',
      text: "Estas a punto de eliminar una clase!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.claseService.eliminar(cursoId).subscribe(response => {
          swal.fire(
            'Eliminado correctamente!',
            'La clase ah sido eliminada.',
            'success'
          );
          this.cargaDetalleCurso();
        }, err => {
          swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: err
          })
        });


      }
    })
  }

  limpiarAgregarClase() {
    this.fileVideo = null;
    this.urlVideo = "";
    this.claseAgregar = new clase();
    this.inputVideoAgregarClase.nativeElement.value = "";
  }

  limpiarEditarClase() {
    this.inputVideoAgregarEditar.nativeElement.value = "";
  }
}

