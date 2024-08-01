package br.com.fiap.techchallengeparking.dtos;

import br.com.fiap.techchallengeparking.model.Veiculo;

public record VeiculoDTO(String id,
                        String placa, 
                        String marca, 
                        String modelo) {

  public VeiculoDTO(Veiculo v) {
    this(v.getId(), v.getPlaca(), v.getMarca(), v.getModelo());
  }

  public Veiculo mapToVeiculo() {
    return new Veiculo(this.id, this.placa, this.marca, this.modelo);
  }
}
