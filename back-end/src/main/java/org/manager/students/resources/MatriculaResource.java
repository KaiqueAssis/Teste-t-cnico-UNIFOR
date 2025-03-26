package org.manager.students.resources;

import org.manager.students.domain.form.MatriculadoForm;
import org.manager.students.domain.services.MatriculaService;
import org.manager.students.exception.AlunoNaoEncontradoException;
import org.manager.students.exception.CursoNaoEncontradoException;
import org.manager.students.exception.ItemExistenteException;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("matriculas")
public class MatriculaResource {

    @Inject
    MatriculaService service;

    @POST
    public Response matricularAlunoAUmCurso(MatriculadoForm form)
            throws AlunoNaoEncontradoException, CursoNaoEncontradoException, ItemExistenteException {
        service.matriculaAlunoAUmCurso(form);
        return Response.ok().build();
    }

    @DELETE
    @Path("{uuidAluno}/{uuidCurso}")
    public Response deletarMatriculaDoAluno(@PathParam("uuidAluno") String uuidAluno,
            @PathParam("uuidCurso") String uuidCurso)
            throws AlunoNaoEncontradoException, CursoNaoEncontradoException {

        service.deletarMatricula(uuidAluno, uuidCurso);
        return Response.noContent().build();
    }
}
