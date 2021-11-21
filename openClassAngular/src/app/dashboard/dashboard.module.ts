import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { CursoComponent } from './curso/curso.component';
import { PerfilComponent } from './perfil/perfil.component';
import { DashboardComponent } from './dashboard.component';
import { PrincipalComponent } from './principal/principal.component';
import { PresentacionCursoComponent } from './presentacion-curso/presentacion-curso.component';

import { FormsModule } from '@angular/forms';
import { DetalleClaseComponent } from './detalle-clase/detalle-clase.component';
@NgModule({
  declarations: [DashboardComponent,PrincipalComponent,CursoComponent, PerfilComponent, PresentacionCursoComponent, DetalleClaseComponent],
  imports: [
    CommonModule,
    FormsModule,
    DashboardRoutingModule,
    ReactiveFormsModule
  ]
})
export class DashboardModule { }
