package org.manager.students.exception;

public class ApiMensagem {

    private String mensagem;

    private String detalhe;

    public ApiMensagem(String mensagem, String detalhe) {
        this.mensagem = mensagem;
        this.detalhe = detalhe;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

}
