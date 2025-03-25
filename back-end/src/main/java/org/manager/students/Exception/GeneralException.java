package org.manager.students.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GeneralException implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {

        if (e instanceof AlunoNaoEncontradoException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Erro: Aluno não encontrado")
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erro: Por favor entre em contato")
                .build();
    }

}
