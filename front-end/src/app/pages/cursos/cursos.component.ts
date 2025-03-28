import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators,  ReactiveFormsModule } from '@angular/forms';
import { CursoService } from '../../services/curso.service';
import { TableModule } from 'primeng/table';
import { Curso } from '../../models/curso';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { Aluno } from '../../models/aluno';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-cursos',
  standalone: true,
  imports: [ButtonModule  ,TableModule,CommonModule,ToastModule, DialogModule, ReactiveFormsModule, InputTextModule],
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.scss',
  providers: [MessageService]
})
export class CursosComponent {

  cursoForm = new FormGroup({
    nome: new FormControl('', Validators.required)
  }) ;
  cursos: Curso[] = []
  modalCadastrarCurso = false 
  modalMatriculas = false
  alunosMatriculados: Aluno[] = []

  constructor(
    private cursoService : CursoService,
    private messageService : MessageService
  ) {}

  ngOnInit(): void {
    this.carregarCursos();
  }

  mostrarModalDasMatriculas(alunos: Aluno[]): void {
    this.modalMatriculas = true
    console.log(alunos)
    this.alunosMatriculados = alunos
  }

  fecharModalDasMatriculas(): void {
    this.modalMatriculas = false
    this.alunosMatriculados =[]
  }

  mostrarModalDeCadastro(): void {
    this.modalCadastrarCurso = true;
  }

  fecharModalDeCadastro(): void {
    this.modalCadastrarCurso = false;
  }

  carregarCursos(): void{
    this.cursoService.listar().subscribe({
      next: (data) => {
        this.cursos = data;
      }, error: (erro) => {
        console.error('Erro ao carregar cursos:', erro);
      }
    })
  }

  adicionarCurso(): void {
    if (this.cursoForm.invalid) {
      console.log('Formulário inválido');
      return;
    }
  const nome = this.cursoForm.value.nome ?? ''
   const cursoBody = { nome };

  this.cursoService.adicionar(cursoBody).subscribe({
    next: () => {
      this.cursoForm.reset(); 
      this.carregarCursos();
      this.fecharModalDeCadastro()
      this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Message Content' });
      
    },
    error: (erro) => console.error('Erro ao criar curso:', erro)
  });
  }

  deletarCurso(uuid: string): void {
    this.cursoService.deletar(uuid).subscribe({
      next: ()=> {
        this.carregarCursos();
        alert("Curso deletado com sucesso!")
      }, error: (err) =>{
        alert(err)
      }

    })
  }
}
