import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { clase, curso } from '../EntityStruct';
@Injectable({
    providedIn: 'root'
})
export class CursoService {
    private api: string;
    constructor(private httpClient: HttpClient) {
        this.api = 'http://localhost:8080/curso/v1';
    }

    lista():any{
        return this.httpClient.get(this.api);
    }
    
    crear(clase:clase):any{
        clase.claseID = 0;
        return this.httpClient.post(this.api,clase);
    }

    listaPorCreador(usuarioId:number):any{
        return this.httpClient.get(this.api+"/creador/"+usuarioId);
    }

    detalleCurso(cursoId:number):any{
        return this.httpClient.get(this.api+"/detalle/"+cursoId);
    }
}

