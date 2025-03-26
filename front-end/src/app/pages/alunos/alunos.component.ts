import { Component, OnInit } from '@angular/core';
import { Aluno } from '../../models/aluno';
import { AlunoService } from '../../services/aluno.service';

@Component({
  selector: 'app-alunos',
  standalone: true,
  imports: [],
  templateUrl: './alunos.component.html',
  styleUrl: './alunos.component.scss',
  providers: [AlunoService]
})
export class AlunosComponent implements OnInit{
  alunos: Aluno[] = []

  constructor(private alunoService : AlunoService){}


  ngOnInit(): void {
   this.carregarAlunos();
  }

  carregarAlunos(): void {
    this.alunoService.listarAlunos().subscribe({
      next: (dados) => {
        this.alunos = dados; // Atualiza a lista de alunos
      },
      error: (erro) => {
        console.error('Erro ao carregar alunos:', erro);
      }
    });
  }
}
