import { Aluno } from "./aluno";

export interface Curso {
    uuid: string;
    nome: string;
    alunosDtos: Aluno[]
}