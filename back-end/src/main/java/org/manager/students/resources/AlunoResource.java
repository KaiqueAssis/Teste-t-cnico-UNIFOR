package org.manager.students.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/alunos")
public class AlunoResource {

    @GET
    public Response pegarAluno() {
        return Response.accepted("Tsete").build();
    }
}
