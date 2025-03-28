import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../enviroments/environment';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class MatriculaService {

  private apiUrl = environment.apiUrl+"matriculas";

  constructor(private http : HttpClient) { }

  fazerMatricula(form: {uuidDoAluno: string, uuidDoCurso: string}): Observable<void>{
    return this.http.post<void>(this.apiUrl, form)
  }
}
