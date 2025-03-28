package org.manager.students.domain.services;

import java.util.List;
import java.util.Optional;

import org.manager.students.domain.dto.CursoDto;
import org.manager.students.domain.entity.Aluno;
import org.manager.students.domain.entity.Curso;
import org.manager.students.domain.form.CursoForm;
import org.manager.students.domain.repository.CursoRepository;
import org.manager.students.exception.AlunoNaoEncontradoException;
import org.manager.students.exception.CursoNaoEncontradoException;
import org.manager.students.exception.ItemExistenteException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CursoService {

    @Inject
    CursoRepository repository;

    @Inject
    AlunoService alunoService;

    public List<CursoDto> listarTodosOsCursos() {
        return repository.todosOsCursosOrdenadosPeloNome()
                .stream()
                .map(Curso::converterParaDto)
                .toList();
    }

    @Transactional
    public CursoDto cadastrarCurso(CursoForm form)
            throws ItemExistenteException {
        Curso cursoNovo = new Curso(form.nome());
        verificaSeOCursoJaExiste(form.nome());
        repository.persist(cursoNovo);
        return cursoNovo.converterParaDto();
    }

    @Transactional
    public void deletarCurso(String uuid)
            throws CursoNaoEncontradoException {

        repository.delete(buscarEValidarCurso(uuid));
    }

    public Curso buscarEValidarCurso(String uuid)
            throws CursoNaoEncontradoException {
        return repository.pegarCursoPeloUuid(uuid)
                .orElseThrow(() -> new CursoNaoEncontradoException("Curso não encontrado!"));
    }

    private void verificaSeOCursoJaExiste(String nome)
            throws ItemExistenteException {
        Optional<Curso> cursoExistente = repository.pegarCursoPeloNome(nome);
        if (cursoExistente.isPresent()) {
            throw new ItemExistenteException(String.format("Já existe um curso com o nome %s", nome));
        }
    }

    public List<CursoDto> pegarCursoDisponivelParaFazerMatricula(String uuidAluno)
            throws AlunoNaoEncontradoException {
        Aluno aluno = alunoService.buscarAlunoEValidar(uuidAluno);

        return repository.todosOsCursosOrdenadosPeloNome().stream()
                .filter(curso -> !curso.getAlunos().contains(aluno))
                .map(Curso::converterParaDto)
                .toList();

    }

}
