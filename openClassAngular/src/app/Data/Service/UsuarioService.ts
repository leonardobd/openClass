import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { usuario } from '../EntityStruct';
import { Observable } from 'rxjs';
@Injectable({
    providedIn: 'root'
})
export class UsuarioService {
    private api: string;
    constructor(private httpClient: HttpClient) {
        this.api = 'http://localhost:8080/usuario/v1';
    }

    login(usuario: string, password: string): any {
        return this.httpClient.get(this.api + "/" + usuario + "/" + password);
    }

    registrar(usuario: usuario): any {
        return this.httpClient.post(this.api, usuario);
    }

    actualizar(usuario:usuario):any{
        return this.httpClient.put(this.api,usuario);
    }

    detalle(usuario:number):any{
        return this.httpClient.get(this.api+"/detalle/"+usuario);
    }

    actualizaMaestro(usuario:number,institucion:String){
        return this.httpClient.put(this.api+"/maestro/"+usuario+"/"+institucion,null);
    }
}