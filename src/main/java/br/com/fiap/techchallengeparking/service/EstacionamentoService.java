package br.com.fiap.techchallengeparking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.techchallengeparking.dtos.CondutorDTO;
import br.com.fiap.techchallengeparking.dtos.EstacionamentoDTO;
import br.com.fiap.techchallengeparking.dtos.EstacionamentoInputDTO;
import br.com.fiap.techchallengeparking.dtos.VeiculoDTO;
import br.com.fiap.techchallengeparking.exception.EstacionamentoNotFoundException;
import br.com.fiap.techchallengeparking.model.Condutor;
import br.com.fiap.techchallengeparking.model.Estacionamento;
import br.com.fiap.techchallengeparking.model.Veiculo;
import br.com.fiap.techchallengeparking.repository.EstacionamentoRepository;
import jakarta.validation.Valid;

@Service
public class EstacionamentoService {

  @Autowired
  private EstacionamentoRepository repositorio;

  @Autowired
  private CondutorService condutorService;

  @Autowired
  private VeiculoService veiculoService;

  public List<EstacionamentoDTO> obterTodos() {
    return repositorio.findAll().stream()
    .map(e -> new EstacionamentoDTO(e))
    .toList();
  }

  public Optional<EstacionamentoDTO> obterPorId(String id) {
    Optional<Estacionamento> estacionamento = repositorio.findById(id);

    if (estacionamento.isPresent()) {
      return Optional.of(new EstacionamentoDTO(estacionamento.get()));  
    } else
      return Optional.empty(); 
  }

  public EstacionamentoDTO iniciarEstacionamento(@Valid EstacionamentoInputDTO dto) {
    Optional<CondutorDTO> condutorDto = condutorService.obterPorId(dto.condutor_id());

    if (condutorDto.isPresent()) {
      Condutor condutor = new Condutor(condutorDto.get().id(),
        condutorDto.get().nome(), condutorDto.get().cpf(), condutorDto.get().endereco(),
        condutorDto.get().email());

      Optional<VeiculoDTO> veiculoDTO = veiculoService.obterPorId(dto.veiculo_id());

      if (veiculoDTO.isPresent()) {
        Veiculo veiculo = new Veiculo(veiculoDTO.get().id(), veiculoDTO.get().placa(),
          veiculoDTO.get().marca(), veiculoDTO.get().modelo());  
      
        Estacionamento estacionamento = new Estacionamento();
        estacionamento.setCondutor(condutor);
        estacionamento.setVeiculo(veiculo);  
        estacionamento.setDuracaoTipo(dto.duracaoTipo());
        estacionamento.setInicio(LocalDateTime.now());
        estacionamento = repositorio.save(estacionamento);
        return new EstacionamentoDTO(estacionamento);
      
      } else {
        throw new EstacionamentoNotFoundException("Veículo não cadastrado. Impossível inicar o parquímetro.");
      }
    } else {
      throw new EstacionamentoNotFoundException("Condutor não cadastrado. Impossível inicar o parquímetro.");  
    }

  }

  public Optional<EstacionamentoDTO> atualizarPorId(String id, @Valid EstacionamentoDTO dto) {
    Optional<Estacionamento> estacionamento = repositorio.findById(id);

    if (estacionamento.isPresent()) {
      Estacionamento estacionamentoAtualizado = dto.mapToEstacionamento();
      estacionamentoAtualizado.setId(id);
      estacionamentoAtualizado = repositorio.save(estacionamentoAtualizado);      
      return Optional.of(new EstacionamentoDTO(estacionamentoAtualizado)); 
    } else {
      return Optional.empty();
    }
  }

  public EstacionamentoDTO finalizarEstacionamento(String id) {
    Optional<Estacionamento> estacionamento = repositorio.findById(id);

     if (estacionamento.isPresent() && estacionamento.get().getFim() == null ) {
      estacionamento.get().setFim(LocalDateTime.now());
      repositorio.save(estacionamento.get());      
      return new EstacionamentoDTO(estacionamento.get()); 
    } else {
      throw new EstacionamentoNotFoundException("Ticket não encontrado ou já finalizado. Impossível inicar o parquímetro.");  
    }

  }
  
}
