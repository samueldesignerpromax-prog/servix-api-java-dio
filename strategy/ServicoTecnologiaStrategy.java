
package com.servix.api.strategy;

import com.servix.api.model.Servico;
import org.springframework.stereotype.Component;

@Component
public class ServicoTecnologiaStrategy implements TipoServicoStrategy {

    @Override
    public boolean suporta(String categoria) {
        return categoria != null
                && categoria.equalsIgnoreCase("tecnologia");
    }

    @Override
    public String descrever(Servico servico) {
        return "Serviço de tecnologia: " + servico.getNome()
                + ". Descrição: " + servico.getDescricao();
    }
}
