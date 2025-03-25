package org.manager.students.domain.repository;

import java.util.List;
import java.util.Optional;

import org.manager.students.domain.entity.Curso;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CursoRepository implements PanacheRepository<Curso> {

    public List<Curso> todosOsCursosOrdenadosPeloNome() {
        return findAll(Sort.ascending("nome")).list();
    }

    public Optional<Curso> pegarCursoPeloUuid(String uuid) {
        return find("uuid", uuid).firstResultOptional();
    }

    public Optional<Curso> pegarCursoPeloNome(String nomeDoCurso) {
        return find("nome", nomeDoCurso).firstResultOptional();
    }
}
