
package com.servix.api.service;

public class ServicoNaoEncontradoException extends RuntimeException {

    public ServicoNaoEncontradoException(Long id) {
        super("Serviço não encontrado: " + id);
    }
}
