package org.manager.students.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.manager.students.domain.dto.CursoDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Matricula> matriculas = new ArrayList<>();

    public Curso(String nome) {
        this.nome = nome;
    }

    public Curso() {
    }

    public CursoDto converterParaDto() {
        return new CursoDto(nome, uuid, getAlunos().stream().map(Aluno::converterParaDto).toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Aluno> getAlunos() {
        return matriculas.stream()
                .map(matricula -> matricula.getAluno())
                .toList();
    }

}
