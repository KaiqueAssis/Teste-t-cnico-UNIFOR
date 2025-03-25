package org.manager.students.exception;

public class MensagemExceptional {

    private String mensagem;

    private String detalhe;

    public MensagemExceptional(String mensagem, String detalhe) {
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
