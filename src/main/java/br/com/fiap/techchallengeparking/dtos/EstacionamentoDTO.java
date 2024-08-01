package br.com.fiap.techchallengeparking.dtos;

import java.time.LocalDateTime;

import br.com.fiap.techchallengeparking.model.Condutor;
import br.com.fiap.techchallengeparking.model.DuracaoTipo;
import br.com.fiap.techchallengeparking.model.Estacionamento;
import br.com.fiap.techchallengeparking.model.Veiculo;

public record EstacionamentoDTO(String id,
                               Condutor condutor,
                               Veiculo veiculo,
                               DuracaoTipo duracaoTipo,
                               LocalDateTime inicio,
                               LocalDateTime fim) {

  public EstacionamentoDTO(Estacionamento e) {
    this(e.getId(), e.getCondutor(), e.getVeiculo(),
      e.getDuracaoTipo(), e.getInicio(), e.getFim());
  }

  public Estacionamento mapToEstacionamento() {
    return new Estacionamento(this.id, this.condutor, this.veiculo, this.duracaoTipo,
      this.inicio, this.fim);
  }
}
