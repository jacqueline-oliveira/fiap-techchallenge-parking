package br.com.fiap.techchallengeparking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.techchallengeparking.dtos.VeiculoDTO;
import br.com.fiap.techchallengeparking.model.Veiculo;
import br.com.fiap.techchallengeparking.repository.VeiculoRepository;

@Service
public class VeiculoService {
  @Autowired
  private VeiculoRepository repositorio;

  public List<VeiculoDTO> obterTodos() {
    return repositorio.findAll().stream()
            .map(v -> new VeiculoDTO(v))
            .toList();
  }

  public Optional<VeiculoDTO> obterPorId(String id) {
    Optional<Veiculo> veiculo = repositorio.findById(id);

    if (veiculo.isPresent()) {
      return Optional.of(new VeiculoDTO(veiculo.get()));  
    } else
      return Optional.empty(); 
  }

  public VeiculoDTO cadastrarVeiculo(VeiculoDTO dto) {
    Veiculo veiculo = dto.mapToVeiculo();
    veiculo = repositorio.save(veiculo);
    return new VeiculoDTO(veiculo);
  }

  public void excluirPorId(String id) {
    repositorio.deleteById(id);
  }

  public Optional<VeiculoDTO> atualizarPorId(String id, VeiculoDTO dto) {
    Optional<Veiculo> veiculo = repositorio.findById(id);

    if (veiculo.isPresent()) {
      Veiculo veiculoAtualizado = dto.mapToVeiculo();
      veiculoAtualizado.setId(id);
      veiculoAtualizado = repositorio.save(veiculoAtualizado);      
      return Optional.of(new VeiculoDTO(veiculoAtualizado)); 
    } else {
      return Optional.empty();
    }
  }
}
