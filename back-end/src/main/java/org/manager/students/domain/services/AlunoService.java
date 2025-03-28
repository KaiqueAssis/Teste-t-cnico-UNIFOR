package org.manager.students.domain.services;

import java.util.List;

import org.manager.students.domain.dto.AlunoDto;
import org.manager.students.domain.entity.Aluno;
import org.manager.students.domain.repository.AlunoRepository;
import org.manager.students.exception.AlunoNaoEncontradoException;
import org.manager.students.exception.ItemNaoPodeSerDeletadoException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AlunoService {

    @Inject
    AlunoRepository repository;

    @Transactional
    public AlunoDto cadastrarAluno(String nome) {
        Aluno aluno = new Aluno(nome);
        repository.persist(aluno);
        return aluno.converterParaDto();
    }

    public AlunoDto buscarAlunoPorUuid(String uuidAluno)
            throws AlunoNaoEncontradoException {
        return buscarAlunoEValidar(uuidAluno)
                .converterParaDto();
    }

    public List<AlunoDto> listarTodosOsAlunos() {
        var ok = repository.buscarTodosOsAlunosOrdenadoPorNome();
        System.out.println(ok.size());
        return ok
                .stream()
                .map(Aluno::converterParaDto)
                .toList();
    }

    @Transactional
    public void deletarAluno(String uuid)
            throws AlunoNaoEncontradoException,
             ItemNaoPodeSerDeletadoException {
        Aluno aluno = buscarAlunoEValidar(uuid);
        verificaSeAlunoEstaMatriculadoEmAlgumCurso(aluno);
        repository.delete(aluno);
    }

    public void verificaSeAlunoEstaMatriculadoEmAlgumCurso(Aluno aluno) throws ItemNaoPodeSerDeletadoException{
        if(aluno.getMatriculas() != null && !aluno.getMatriculas().isEmpty()){
           throw new ItemNaoPodeSerDeletadoException("Aluno está matricula em algum curso."); 
        }
    }

    public Aluno buscarAlunoEValidar(String uuid)
            throws AlunoNaoEncontradoException {
        return repository.buscarAlunoPeloUuid(uuid)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado!"));
    }

}
