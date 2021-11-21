import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { usuario } from '../EntityStruct';
import { Observable } from 'rxjs';
@Injectable({
    providedIn: 'root'
})
export class CategoriaService {
    private api: string;
    constructor(private httpClient: HttpClient) {
        this.api = 'http://localhost:8080/categoria/v1';
    }

    lista():any{
        return this.httpClient.get(this.api);
    }
}