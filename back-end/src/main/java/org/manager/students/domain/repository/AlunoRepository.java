package org.manager.students.domain.repository;

import java.util.List;
import java.util.Optional;

import org.manager.students.domain.entity.Aluno;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlunoRepository implements PanacheRepository<Aluno> {

    public List<Aluno> buscarTodosOsAlunosOrdenadoPorNome() {
        return findAll(Sort.ascending("nome")).list();
    }

    public Optional<Aluno> buscarAlunoPeloUuid(String uuid) {
        return find("uuid", uuid)
                .firstResultOptional();
    }

}
