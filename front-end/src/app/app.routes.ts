import { Routes } from '@angular/router';
import { AlunosComponent } from './pages/alunos/alunos.component';
import { CursosComponent } from './pages/cursos/cursos.component';
export const routes: Routes = [
    { path : 'alunos', component: AlunosComponent},
    { path : 'cursos', component: CursosComponent},
];
