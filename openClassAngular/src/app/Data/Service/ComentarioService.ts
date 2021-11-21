import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { comentario } from '../EntityStruct';
@Injectable({
    providedIn: 'root'
})
export class ComentarioService {
    private api: string;
    constructor(private httpClient: HttpClient) {
        this.api = 'http://localhost:8080/comentario/v1/';
    }

    listar(idClase:number):any{
        return this.httpClient.get(this.api+idClase);
    }

    agregar(comentario:comentario):any{
        return this.httpClient.post(this.api,comentario);
    }
}

