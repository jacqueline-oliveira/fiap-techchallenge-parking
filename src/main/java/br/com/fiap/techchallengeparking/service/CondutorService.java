package br.com.fiap.techchallengeparking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.techchallengeparking.dtos.CondutorDTO;
import br.com.fiap.techchallengeparking.model.Condutor;
import br.com.fiap.techchallengeparking.repository.CondutorRepository;

@Service
public class CondutorService {
  @Autowired
  private CondutorRepository repositorio;

  public List<CondutorDTO> obterTodos() {
    return repositorio.findAll().stream()
          .map(e -> new CondutorDTO(e))
          .toList();
  }

  public Optional<CondutorDTO> obterPorId(String id) {
    Optional<Condutor> condutor = repositorio.findById(id);

    if (condutor.isPresent()) {
      return Optional.of(new CondutorDTO(condutor.get()));  
    } else {
      return Optional.empty(); 
  }
}

  public void excluirPorId(String id) {
    repositorio.deleteById(id);
  }

  public Optional<CondutorDTO> atualizarPorId(String id, CondutorDTO dto) {
    Optional<Condutor> condutor = repositorio.findById(id);

    if (condutor.isPresent()) {
      Condutor condutorAtualizado = dto.mapToCondutor();
      condutorAtualizado.setId(id);
      condutorAtualizado = repositorio.save(condutorAtualizado);      
      return Optional.of(new CondutorDTO(condutorAtualizado)); 
    } else {
      return Optional.empty();
    }
  }

  public CondutorDTO cadastrarCondutor(CondutorDTO dto) {
    Condutor condutor = dto.mapToCondutor();
    condutor = repositorio.save(condutor);
    return new CondutorDTO(condutor);
  }
  
}
