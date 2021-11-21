import { Component, OnInit, ViewChild } from '@angular/core';
import { CategoriaService } from '../../Data/Service/CategoriaService';
import { CursoService } from '../../Data/Service/CursoService';
import { FileService } from '../../Data/Service/FileService';
import { CursoRegistroService } from '../../Data/Service/CursoRegistroService';
import swal from 'sweetalert2';
import { categoria, clase, curso, cursoRegistro, usuario } from 'src/app/Data/EntityStruct';
import { forkJoin } from 'rxjs';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.scss']
})
export class PrincipalComponent implements OnInit {
  categorias: categoria[] = []; //lista de categorias
  curso: curso = new curso(); //nuevo curso
  clase: clase = new clase();
  fechaActual: Date = new Date(); //limite de fecha de inicio del curso
  usuario: usuario = new usuario(); // sirve para tener los datos del usuario ingresado
  cursosCreados:curso[]=[];
  cursosRegistro:cursoRegistro[]=[];//cursos en los que estoy participando como alumno
  constructor(private categoriaService: CategoriaService, private cursoService: CursoService, private fileService: FileService,private cursoRegistroService:CursoRegistroService) { }

  ngOnInit(): void {
    this.iniciaConsultaDatosPorTipoUsuario();
  }

  iniciaConsultaDatosPorTipoUsuario() {
    //Obtiene sesion actual
    this.usuario = JSON.parse(localStorage.getItem('userSession'));
    //Valida inicializacion de data
    if (this.usuario.usuarioTipo == 'MS') {
      this.inicializaParametrosCursoNuevo();
      this.listaCategorias();
      this.listaCursosRegistrados();
      this.listaCursosPropios()
    } else {
      this.listaCursosPropios();
    }
  }

  agregaCursoForm: FormGroup;
  //#region Data inicial usuario tipo maestro


  inicializaParametrosCursoNuevo() {
    this.curso.categoria = new categoria();
    this.curso.categoria.categoriaId = 0;
    this.agregaCursoForm = new FormGroup({
      nombre: new FormControl('', Validators.minLength(1)),
      detalle: new FormControl('', Validators.maxLength(300)),
      sesiones: new FormControl(Validators.min(2)),
      categoria: new FormControl(Validators.min(1))
    });
  }

  listaCategorias() {
    this.categoriaService.lista().subscribe(response => {
      this.categorias = response;
    }, err => {
      swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'No se han podido cargar las categorias'
      })
    });
  }

  listaCursosRegistrados() {
    this.cursoService.listaPorCreador(this.usuario.usuarioId).subscribe(response=>{
      this.cursosCreados = response;
    },err=>{
      swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'No se han podido cargar los cursos que eh registrado'
      });
    });
  }

  listaCursosPropios(){
    this.cursoRegistroService.lista(this.usuario.usuarioId).subscribe(r=>{
      this.cursosRegistro=r;
    },err=>{
      swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'No se han podido cargar los cursos en los que estoy registrado.'
      });
    });
  }
  //#endregion



  //#region seleccion de video y miniatura
  urlVideo;
  urlImage;

  formatVideo;
  formatImagen;

  fileVideo: File;
  fileImage: File;

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
  //#endregion

  //#region  crea curso
  estadoCarga: string = "NI"; //NI:No iniciado, I:iniciado, F:finalizado, E:error
  crearCurso() {
    //0. Establece data de campos basicos
    this.dataInicialCamposBasicos();
    //1.Bloquea los controles del formulario y carga de archivos
    this.cargaIniciada('inicio');
    //2.Registra en curso el id del usuario creador
    let usuarioPareo: usuario = new usuario();
    usuarioPareo.usuarioId = this.usuario.usuarioId
    this.curso.creador = usuarioPareo;
    //3. Valida que solo puede proceder si el usuario logeado es un maestro
    if (this.usuario.usuarioTipo == 'MS') {
      //4. Verifica que las url no tengan data
      if (this.curso.cursoMinuatura != undefined && this.clase.claseUrl != undefined) {
        this.enviaDataCurso();
      } else {
        //5. Valida que esten presentes el video y la miniatura
        if (this.validaExistenciaArchivos(this.fileImage, this.fileVideo)) {
          //7.Declara url de subida de archivos
          let miniatura = this.fileService.upload(this.fileImage, 'miniatura');
          let video = this.fileService.upload(this.fileVideo, 'video');

          //7. Ejecuta la subida de archivos en paralelo
          forkJoin([miniatura, video]).subscribe(r => {
            let responseCapture: any = r;
            this.curso.cursoMinuatura = responseCapture[0].response.toString();
            this.clase.claseUrl = responseCapture[1].response.toString();
            this.clase.curso = this.curso;
            //Oculta icono de carga
            this.cargaVideo = false;
            this.cargaImagen = false;
            //8.Registra en la BD el curso y la clase   
            this.enviaDataCurso();
            //9. Limpiar formulario
            this.resetFormulario();
            
          }, err => {
            swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'No se han podido cargar algun(os) archivo(s).'
            });
            this.cargaIniciada('error');
            console.log(err);
          });
        } else {
          swal.fire({
            icon: 'warning',
            title: 'Oops...',
            text: 'Necesita registrar el video y la miniatura para poder seguir.'
          });
          this.cargaIniciada('error');
        }
      }
    } else {
      swal.fire({
        icon: 'warning',
        title: 'Oops...',
        text: 'Esta funcionalidad solo esta permitida para un maestro.'
      });
      this.cargaIniciada('error');
    }
  }

  enviaDataCurso() {
    this.cursoService.crear(this.clase).subscribe(response => {
      this.nombre = true;
      this.sesiones = true;
      this.categoria = true;
      this.fechaInicio = true;
      this.videoInput = true;
      this.imageInput = true;
      this.descripcion = true;
      this.closebutton.nativeElement.click();
      // Actualizar la data del dashboard
      this.listaCursosRegistrados();
    }, err => {
      swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Error al registrar la clase'
      });
      console.log(err);
    });
  }

  dataInicialCamposBasicos() {
    this.clase.claseNombre = 'Introduccion al curso de'
  }

  validaExistenciaArchivos(miniatura: File, video: File): Boolean {
    return true;
  }


  cargaVideo: Boolean = false;
  cargaImagen: Boolean = false;
  nombre: Boolean = false;
  sesiones: Boolean = false;
  categoria: Boolean = false;
  fechaInicio: Boolean = false;
  videoInput: Boolean = false;
  imageInput: Boolean = false;
  descripcion: Boolean = false;
  @ViewChild('closebutton') closebutton;
  cargaIniciada(tipo: string) {
    if (tipo == 'inicio') {
      this.cargaVideo = true;
      this.cargaImagen = true;
      this.nombre = true;
      this.sesiones = true;
      this.categoria = true;
      this.fechaInicio = true;
      this.videoInput = true;
      this.imageInput = true;
      this.descripcion = true;
    } else if (tipo == 'general') {
      this.cargaVideo = false;
      this.cargaImagen = false;
      this.nombre = false;
      this.sesiones = false;
      this.categoria = false;
      this.fechaInicio = false;
      this.videoInput = false;
      this.imageInput = false;
      this.descripcion = false;
    } else if (tipo == 'error') {
      this.cargaVideo = true;
      this.cargaImagen = true;
      this.nombre = true;
      this.sesiones = true;
      this.categoria = true;
      this.fechaInicio = true;
      this.videoInput = false;
      this.imageInput = false;
      this.cargaVideo = false;
      this.cargaImagen = false;
      this.descripcion = true;
    }
  }



  //#endregion

  resetFormulario() {
    this.cargaIniciada('general');
    this.clase = new clase();
    this.curso = new curso();
    this.curso.categoria = new categoria();
    this.curso.categoria.categoriaId = 0;
    this.fileVideo = null;
    this.fileImage = null;
    this.formatVideo = '';
    this.formatImagen = '';
    this.urlVideo = [];
    this.urlImage = [];
  }

}