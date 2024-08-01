package br.com.fiap.techchallengeparking.dtos;

import br.com.fiap.techchallengeparking.model.Condutor;

public record CondutorDTO(String id,
                          String nome,
                          String cpf,
                          String endereco,
                          String email) {

  public CondutorDTO(Condutor e) {
    this(e.getId(), e.getNome(), e.getCpf(), e.getEndereco(), e.getEmail());
  }

  public Condutor mapToCondutor() {
    return new Condutor(this.id, this.nome, this.cpf, this.endereco, this.email);
  }
}
