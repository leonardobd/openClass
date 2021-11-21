import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
    providedIn: 'root'
})
export class FileService {
    
    private api: string;
    constructor(private httpClient: HttpClient) {
        this.api = 'http://localhost:8080/file/v1';
    }

    upload(archivo: File, tipo: string): any {
        let file:FormData = new FormData();
        file.append('file',archivo);
        return this.httpClient.put(this.api+"/"+tipo, file);
    }
}
