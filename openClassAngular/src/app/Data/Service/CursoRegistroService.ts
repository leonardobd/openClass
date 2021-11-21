import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { clase, curso, cursoRegistro } from '../EntityStruct';
@Injectable({
    providedIn: 'root'
})
export class CursoRegistroService {
    private api: string;
    constructor(private httpClient: HttpClient) {
        this.api = 'http://localhost:8080/cursoRegistro/v1';
    }

    lista(usuario:number):any{
        return this.httpClient.get(this.api+"/"+usuario);
    }
    registrarme(cursoRegistro: cursoRegistro):any{
        return this.httpClient.post(this.api,cursoRegistro);
    }
    varificaRegistro(usuario:number,curso:number){
        return this.httpClient.get(this.api+"/verificado/"+usuario+"/"+curso);
    }
}