
package com.servix.api.strategy;

import com.servix.api.model.Servico;

public interface TipoServicoStrategy {

    boolean suporta(String categoria);

    String descrever(Servico servico);
}
