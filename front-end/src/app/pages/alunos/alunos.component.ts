import { Component, OnInit } from '@angular/core';
import { Aluno } from '../../models/aluno';
import { AlunoService } from '../../services/aluno.service';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { FormControl } from '@angular/forms';


@Component({
  selector: 'app-alunos',
  standalone: true,
  imports: [TableModule, ReactiveFormsModule ,ButtonModule, DialogModule, InputTextModule],
  templateUrl: './alunos.component.html',
  styleUrl: './alunos.component.scss',
  providers: [AlunoService]
})
export class AlunosComponent implements OnInit{
  alunos: Aluno[] = []
  modalVisivel: boolean = false;
  myGroup = new FormGroup({
    firstName: new FormControl("")
});

  constructor(private alunoService : AlunoService){}


  ngOnInit(): void {
   this.carregarAlunos();
  }

  mostrarModal(): void {
    this.modalVisivel = true;
  }

  fecharModal(): void {
    this.modalVisivel = false;
  }

  addAluno(){
    console.log(this.myGroup.value.firstName)
    if (this.myGroup.value.firstName?.trim()) {
      this.alunoService.criar(this.myGroup.value.firstName?.trim()).subscribe({
        next : () => {
          alert("Aluno adicionado com sucesso!")
        this.carregarAlunos();
        this.myGroup.value.firstName = ""
        this.modalVisivel = false
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
        this.alunos = dados; // Atualiza a lista de alunos
      },
      error: (erro) => {
        console.error('Erro ao carregar alunos:', erro);
      }
    });
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
