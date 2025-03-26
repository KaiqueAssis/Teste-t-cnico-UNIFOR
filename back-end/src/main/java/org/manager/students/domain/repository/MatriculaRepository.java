package org.manager.students.domain.repository;

import java.util.List;
import java.util.Optional;

import org.manager.students.domain.entity.Aluno;
import org.manager.students.domain.entity.Curso;
import org.manager.students.domain.entity.Matricula;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MatriculaRepository implements PanacheRepository<Matricula> {

    public List<Matricula> buscarAsMatriculasDeUmCursoEspecificos(Curso curso) {
        return find("curso", curso.getId()).list();
    }

    public Optional<Matricula> pegarUmaMatriculaPeloCursoEAluno(Aluno aluno, Curso curso) {
        return find("aluno = ?1 and curso = ?2", aluno, curso).firstResultOptional();
    }
}
