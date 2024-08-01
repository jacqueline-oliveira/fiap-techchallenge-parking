package br.com.fiap.techchallengeparking.dtos;

import br.com.fiap.techchallengeparking.model.DuracaoTipo;

public record EstacionamentoInputDTO(
            String condutor_id,
            String veiculo_id,
            DuracaoTipo duracaoTipo)
 {
  
}
