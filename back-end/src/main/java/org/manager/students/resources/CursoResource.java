package org.manager.students.resources;

import org.manager.students.domain.form.CursoForm;
import org.manager.students.domain.services.CursoService;
import org.manager.students.exception.CursoNaoEncontradoException;
import org.manager.students.exception.ItemExistenteException;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("cursos")
public class CursoResource {

    @Inject
    CursoService service;

    @GET
    public Response buscarTodosOsCurso() {
        return Response.ok(service.listarTodosOsCursos()).build();
    }

    @POST
    public Response cadastrarCurso(CursoForm form)
            throws ItemExistenteException {
        service.cadastrarCurso(form);
        return Response.ok().build();
    }

    @DELETE
    @Path("{uuidCurso}")
    public Response deletarCurso(@PathParam("uuidCurso") String uuid)
            throws CursoNaoEncontradoException {
        service.deletarCurso(uuid);
        return Response.noContent().build();
    }
}
