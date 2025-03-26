package org.manager.students.domain.services;

import java.util.Optional;

import org.manager.students.domain.entity.Aluno;
import org.manager.students.domain.entity.Curso;
import org.manager.students.domain.entity.Matricula;
import org.manager.students.domain.form.MatriculadoForm;
import org.manager.students.domain.repository.MatriculaRepository;
import org.manager.students.exception.AlunoNaoEncontradoException;
import org.manager.students.exception.CursoNaoEncontradoException;
import org.manager.students.exception.ItemExistenteException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MatriculaService {

    @Inject
    MatriculaRepository repository;

    @Inject
    CursoService cursoService;

    @Inject
    AlunoService alunoService;

    @Transactional
    public void matriculaAlunoAUmCurso(MatriculadoForm form)
            throws AlunoNaoEncontradoException, CursoNaoEncontradoException, ItemExistenteException {
        Aluno aluno = alunoService.buscarAlunoEValidar(form.uuidDoAluno());
        Curso curso = cursoService.buscarEValidarCurso(form.uuidDoCurso());
        verificaSeJaExisteMatriculaDoAlunoNoCursoEspecifico(aluno, curso);
        Matricula novaMatricula = new Matricula(aluno, curso);
        repository.persist(novaMatricula);
    }

    @Transactional
    public void deletarMatricula(String uuidAluno, String uuidCurso)
            throws AlunoNaoEncontradoException, CursoNaoEncontradoException {
        Aluno aluno = alunoService.buscarAlunoEValidar(uuidAluno);
        Curso curso = cursoService.buscarEValidarCurso(uuidCurso);
        Matricula matricula = repository.pegarUmaMatriculaPeloCursoEAluno(aluno, curso)
                .orElseThrow(() -> new CursoNaoEncontradoException("Curso não encontrado!"));
        repository.delete(matricula);
    }

    public void verificaSeJaExisteMatriculaDoAlunoNoCursoEspecifico(Aluno aluno, Curso curso)
            throws ItemExistenteException {
        Optional<Matricula> matricuOptional = repository.pegarUmaMatriculaPeloCursoEAluno(aluno, curso);

        if (matricuOptional.isPresent()) {
            throw new ItemExistenteException("O Aluno já está matriculado nesse curso");
        }
    }

}
