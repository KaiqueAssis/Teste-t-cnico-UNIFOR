package org.manager.students.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandle implements ExceptionMapper<Exception> {

    @Override

    public Response toResponse(Exception e) {

        e.printStackTrace();

        if (e instanceof ItemExistenteException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MensagemExceptional("Item Existente", e.getMessage()))
                    .build();
        }

        if (e instanceof AlunoNaoEncontradoException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new MensagemExceptional("Error", e.getMessage()))
                    .build();
        }
        if (e instanceof CursoNaoEncontradoException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new MensagemExceptional("Error", e.getMessage()))
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new MensagemExceptional("Error", "Por favor entre em contato"))
                .build();
    }

}
