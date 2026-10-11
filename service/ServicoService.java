
package com.servix.api.service;

import com.servix.api.model.Servico;
import com.servix.api.repository.ServicoRepository;
import com.servix.api.strategy.TipoServicoStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    private final ServicoRepository repository;
    private final List<TipoServicoStrategy> estrategias;

    public ServicoService(
            ServicoRepository repository,
            List<TipoServicoStrategy> estrategias) {
        this.repository = repository;
        this.estrategias = estrategias;
    }

    public List<Servico> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Servico> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Servico criar(Servico servico) {
        return repository.salvar(servico);
    }

    public Optional<Servico> atualizar(Long id, Servico servico) {
        return repository.atualizar(id, servico);
    }

    public boolean excluir(Long id) {
        return repository.excluir(id);
    }

    public String descreverServico(Long id) {
        Servico servico = repository.buscarPorId(id)
                .orElseThrow(() ->
                        new ServicoNaoEncontradoException(id));

        return estrategias.stream()
                .filter(estrategia ->
                        estrategia.suporta(servico.getCategoria()))
                .findFirst()
                .map(estrategia -> estrategia.descrever(servico))
                .orElse("Nenhuma estratégia disponível para a categoria: "
                        + servico.getCategoria());
    }
}
