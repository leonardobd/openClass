import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CursoComponent } from './curso/curso.component';
import { DashboardComponent } from './dashboard.component';
import { DetalleClaseComponent } from './detalle-clase/detalle-clase.component';
import { PerfilComponent } from './perfil/perfil.component';
import { PresentacionCursoComponent } from './presentacion-curso/presentacion-curso.component';
import { PrincipalComponent } from './principal/principal.component';

const routes: Routes = [
  {path:'dashboard', component: DashboardComponent,children:[
    {path:'', component: PrincipalComponent},
    {path:'perfil',component: PerfilComponent},
    {path:'curso',component: CursoComponent},
    {path:'presentacion-curso/:cursoId',component: PresentacionCursoComponent},
    {path:'detalle-clase/:cursoId/:claseId',component: DetalleClaseComponent},
    {path:'**',redirectTo:''}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
