import { Component, OnInit } from '@angular/core';
import { usuario } from '../Data/EntityStruct';
import { Router } from '@angular/router';
import { UsuarioService } from '../Data/Service/UsuarioService';
import swal from 'sweetalert2';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  usuario:usuario;
  institucion:string='';
  constructor(private router: Router,private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.usuario = JSON.parse(localStorage.getItem('userSession'));
  }

  irVista(url:string,tipo:number){
    if(tipo>0){
      localStorage.removeItem('userSession');
    }
    this.router.navigate([url]);
  }

  actualizaMaestro(){
    this.usuarioService.actualizaMaestro(this.usuario.usuarioId,this.institucion).subscribe(r=>{
      swal.fire({
        icon: 'success',
        title: 'Hecho',
        text: 'Cuenta actualizada a maestro correctamente. Inice sesion nuevamente para ver los cambios.'
      })
    },err=>{
      swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: err
      })
    });
  }

}
