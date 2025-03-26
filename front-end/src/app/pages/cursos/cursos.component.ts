import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CursoService } from '../../services/curso.service';
import { Curso } from '../../models/curso';

@Component({
  selector: 'app-cursos',
  standalone: true,
  imports: [],
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.scss'
})
export class CursosComponent {

  cursoForm: FormGroup | any;
  cursos: Curso[] = []
  
  constructor(
    private fb : FormBuilder,
    private cursoService : CursoService
  ) {}

  ngOnInit(): void {
    this.carregarCursos();
    this.cursoForm = this.fb.group({
      nome: ['', Validators.required]
    });
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

  onSubmitForCreate(): void {
    if (this.cursoForm.invalid) {
      console.log('Formulário inválido');
      return;
    }


  const nome = this.cursoForm.value.nome;
  const cursoBody = { nome };

  this.cursoService.adicionar(cursoBody).subscribe({
    next: () => {
      console.log('Curso criado com sucesso');
      this.cursoForm.reset(); // Limpar o formulário após o envio
    },
    error: (erro) => console.error('Erro ao criar curso:', erro)
  });
  }
}
