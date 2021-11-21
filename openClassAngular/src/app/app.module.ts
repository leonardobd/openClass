import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NgxSpinnerModule } from "ngx-spinner";
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routingModule';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DashboardModule } from './dashboard/dashboard.module';
import {  RxReactiveFormsModule } from "@rxweb/reactive-form-validators";

import { CommonModule } from '@angular/common';
import { ConfirmarCuentaComponent } from './confirmar-cuenta/confirmar-cuenta.component';  

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    ConfirmarCuentaComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgxSpinnerModule,
    FormsModule,
    DashboardModule,
    RxReactiveFormsModule 
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
