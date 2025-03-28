package org.manager.students.resources;

import org.manager.students.domain.dto.AlunoDto;
import org.manager.students.domain.services.AlunoService;
import org.manager.students.exception.AlunoNaoEncontradoException;
import org.manager.students.exception.ItemNaoPodeSerDeletadoException;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunoResource {

    @Inject
    AlunoService service;

    @GET
    @Path("/{uuid}")
    public Response pegarAluno(@PathParam("uuid") String uuid)
            throws AlunoNaoEncontradoException {
        AlunoDto dto = service.buscarAlunoPorUuid(uuid);
        return Response.ok(dto).build();
    }

    @GET
    public Response todosOsAlunos() {
        return Response.ok(service.listarTodosOsAlunos()).build();
    }

    @POST
    @Path("{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarAluno(@PathParam("nome") String nome) {
        return Response.ok(service.cadastrarAluno(nome)).build();
    }

    @DELETE
    @Path("{uuid}")
    public Response deletarItens(@PathParam("uuid") String uuid)
            throws AlunoNaoEncontradoException, ItemNaoPodeSerDeletadoException {
        service.deletarAluno(uuid);
        return Response.ok().build();
    }
}
