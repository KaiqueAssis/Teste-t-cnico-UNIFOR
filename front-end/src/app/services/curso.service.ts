import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Curso } from '../models/curso';
import { environment } from '../../enviroments/environment';


@Injectable({
  providedIn: 'root'
})

export class CursoService {

  private apiUrl = environment.apiUrl+"cursos";


  constructor(private http : HttpClient) { }

  listar(): Observable<Curso[]> {
    return this.http.get<Curso[]>(this.apiUrl);
  }

  adicionar(cursoBody : {nome: string}): Observable<void> {
    return this.http.post<void>(this.apiUrl, cursoBody ) 
  }

  deletar(uuid: string): Observable<void> {
    return this.http.delete<void>(this.apiUrl + uuid)
  }

}
