
package com.servix.api.repository;

import com.servix.api.model.Servico;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ServicoRepository {

    private final List<Servico> servicos = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong(0);

    public synchronized List<Servico> listarTodos() {
        return new ArrayList<>(servicos);
    }

    public synchronized Optional<Servico> buscarPorId(Long id) {
        return servicos.stream()
                .filter(servico -> servico.getId().equals(id))
                .findFirst();
    }

    public synchronized Servico salvar(Servico servico) {
        if (servico.getId() == null) {
            servico.setId(contador.incrementAndGet());
        }

        servicos.add(servico);
        return servico;
    }

    public synchronized Optional<Servico> atualizar(
            Long id, Servico atualizado) {

        Optional<Servico> existente = buscarPorId(id);

        if (existente.isEmpty()) {
            return Optional.empty();
        }

        Servico servico = existente.get();
        servico.setNome(atualizado.getNome());
        servico.setDescricao(atualizado.getDescricao());
        servico.setCategoria(atualizado.getCategoria());
        servico.setPreco(atualizado.getPreco());

        return Optional.of(servico);
    }

    public synchronized boolean excluir(Long id) {
        return servicos.removeIf(servico -> servico.getId().equals(id));
    }
}
