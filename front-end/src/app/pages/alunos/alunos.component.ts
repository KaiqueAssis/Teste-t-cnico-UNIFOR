import { Component, OnInit } from '@angular/core';
import { Aluno } from '../../models/aluno';
import { AlunoService } from '../../services/aluno.service';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { FormControl } from '@angular/forms';
import { MultiSelectModule } from 'primeng/multiselect';
import { Curso } from '../../models/curso';
import { CursoService } from '../../services/curso.service';
import { Select } from 'primeng/select';
import { MatriculaService } from '../../services/matricula.service';


@Component({
  selector: 'app-alunos',
  standalone: true,
  imports: [TableModule,FormsModule, Select, ReactiveFormsModule ,ButtonModule,MultiSelectModule, DialogModule, InputTextModule],
  templateUrl: './alunos.component.html',
  styleUrl: './alunos.component.scss',
  providers: [AlunoService, CursoService, MatriculaService]
})
export class AlunosComponent implements OnInit{

  alunos: Aluno[] = []
  cursosDisponiveis: Curso[] = []

  modalCadastrarAluno: boolean = false;
  modalFazerMatricula : boolean = false;

  formAluno = new FormGroup({
    nome: new FormControl("", Validators.required)
});

  formMatricula = new FormGroup({
      uuidCurso: new FormControl("", Validators.required)
  })

  uuidAlunoSelecionado: string = '';

  constructor(private alunoService : AlunoService, private cursoService : CursoService, private matriculaService : MatriculaService){}


  ngOnInit(): void {
   this.carregarAlunos();
  }

  mostrarModalFazerMatricula(uuidAluno: string): void {
    this.modalFazerMatricula = true;
    this.carregarCursoDisponiveisParaMatricula(uuidAluno);
    this.uuidAlunoSelecionado = uuidAluno;
  }

  fecharModalFazerMatricula(): void {
    this.modalFazerMatricula = false

  }


  mostrarModalCadastroAluno(): void {
    this.modalCadastrarAluno = true;
  }

  fecharModalCadastroAluno(): void {
    this.modalCadastrarAluno = false;
  }

  matricularAluno(){
    if(this.formMatricula.value.uuidCurso?.trim()){
      const form = {uuidDoAluno: this.uuidAlunoSelecionado, uuidDoCurso: this.formMatricula.value.uuidCurso}
      this.matriculaService.fazerMatricula(form).subscribe({
        next: () => {
          alert("Nova matricula feita!");
          this.formMatricula.reset();
          this.modalFazerMatricula = false
        }, error: (err) =>{
          alert("Erro ao fazer a matricula.")
        }
      })
    }
  }

  addAluno(){
    if (this.formAluno.value.nome?.trim()) {
      this.alunoService.criar(this.formAluno.value.nome?.trim()).subscribe({
        next : () => {
          alert("Aluno adicionado com sucesso!")
        this.carregarAlunos();
        this.formAluno.value.nome = ""
        this.modalCadastrarAluno = false
        },
        error: (err) => {
          console.error("Erro ao adicionar aluno:", err);
          alert("Erro ao adicionar aluno. Tente novamente.");
        }
      });
      } else {
      alert("Por favor, insira o nome do aluno");
    }
  }

  carregarAlunos(): void {
    this.alunoService.listarAlunos().subscribe({
      next: (dados) => {
        this.alunos = dados;
      },
      error: (erro) => {
        console.error('Erro ao carregar alunos:', erro);
      }
    });
  }

  carregarCursoDisponiveisParaMatricula(uuidAluno: string): void {
    this.cursoService.listarCursoDisponivelParaMatricula(uuidAluno).subscribe({
      next: (dados) => {
        this.cursosDisponiveis = dados;
      }
    })
  }

  deletarAlunos(uuid: string): void {
    this.alunoService.deletar(uuid).subscribe({
      next: ()=> {
        this.carregarAlunos();
        alert("Aluno deletado com sucesso!")
      }, error: (err) =>{
        alert(err)
      }

    })
  }
}
