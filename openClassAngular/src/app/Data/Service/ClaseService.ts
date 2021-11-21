import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { clase } from '../EntityStruct';
@Injectable({
    providedIn: 'root'
})
export class ClaseService {
    private api: string;
    constructor(private httpClient: HttpClient) {
        this.api = 'http://localhost:8080/clase/v1/';
    }

    eliminar(idClase:number):any{
        return this.httpClient.delete(this.api+idClase);
    }

    agregar(clase:clase):any{
        return this.httpClient.post(this.api,clase);
    }

    editar(clase:clase):any{
        return this.httpClient.put(this.api,clase);
    }

    detalle(claseId:number):any{
        return this.httpClient.get(this.api+"detalle/"+claseId);
    }

    listaPorCurso(cursoId:number):any{
        return this.httpClient.get(this.api+"listaPorCurso/"+cursoId);
    }
}

