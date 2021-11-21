import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { usuario } from '../Data/EntityStruct';
import { UsuarioService } from '../Data/Service/UsuarioService';
import swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private usuarioService: UsuarioService) { }

  email: string = '';
  password: string = '';

  usuario: usuario = new usuario();

  //#region Variables - Mensajes
    loginMessage:string ='';
    registroMessageSuccess:string = '';
    registroMessageError:string[]=[];
  //#endregion



  ngOnInit(): void {
  }

  login() {
    this.usuarioService.login(this.email, this.password).subscribe(response => {
      if (response != null) {
        this.router.navigate(['/dashboard']);
        localStorage.setItem('userSession', JSON.stringify(response));
      } else {
        this.loginMessage = 'Usuario o contraseña incorrectos.';
      }
    }, err => {
      if (err.name == 'HttpErrorResponse') {
        swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Error de conexion al servicio.'
        })
      }else{
        swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Error no controlado.'
        })
      }
    }
    );
  }

  registrarme() {
    this.usuarioService.registrar(this.usuario).subscribe(response => {
      this.usuario = new usuario();
      this.registroMessageSuccess = 'Registrado correctamente';
    }, err => {
      err.error.errors.forEach(element => {
        this.registroMessageError.push(element.defaultMessage);
      });
    });
  }

}
