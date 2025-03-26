import { Injectable } from '@angular/core';
import { environment } from '../../enviroments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Aluno } from '../models/aluno';


@Injectable({
  providedIn: 'root'
})


export class AlunoService {

  private apiUrl = environment.apiUrl+"alunos";

  constructor(private http: HttpClient) {}

  listarAlunos(): Observable<Aluno[]> {
    return this.http.get<Aluno[]>(this.apiUrl)
  }

  criar(nome: string): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/${nome}`, {});
  }

  deletar(uuid: string): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${uuid}`);
  }


}
