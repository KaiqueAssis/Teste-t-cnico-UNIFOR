package org.manager.students.domain.form;

import org.manager.students.domain.entity.Aluno;

public record AlunoForm(String nome) {

    public Aluno converteParaUmaEntidade() {
        return new Aluno(this.nome);
    }
}
