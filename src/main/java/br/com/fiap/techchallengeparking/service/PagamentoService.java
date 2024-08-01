package br.com.fiap.techchallengeparking.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.techchallengeparking.dtos.EstacionamentoDTO;
import br.com.fiap.techchallengeparking.dtos.PagamentoDTO;
import br.com.fiap.techchallengeparking.dtos.PagamentoInputDTO;
import br.com.fiap.techchallengeparking.exception.EstacionamentoNotFoundException;
import br.com.fiap.techchallengeparking.model.DuracaoTipo;
import br.com.fiap.techchallengeparking.model.Pagamento;
import br.com.fiap.techchallengeparking.repository.PagamentoRepository;
import jakarta.validation.Valid;

@Service
public class PagamentoService {
  @Autowired
  private PagamentoRepository repositorio;

  @Autowired
  private EstacionamentoService estacionamentoService;

  public List<PagamentoDTO> obterTodos() {
    return repositorio.findAll().stream()
    .map(p -> new PagamentoDTO(p))
    .toList();
  }

  public Optional<PagamentoDTO> obterPorId(String id) {
    Optional<Pagamento> pagamento = repositorio.findById(id);

    if (pagamento.isPresent()) {
      return Optional.of(new PagamentoDTO(pagamento.get()));  
    } else {
      return Optional.empty(); 
  }
  }

  public PagamentoDTO registrarPagamento(@Valid PagamentoInputDTO dto) {
    Optional<EstacionamentoDTO> estacionamento = estacionamentoService.obterPorId(dto.estacionamento_id());

    if (estacionamento.isPresent()) {
      Pagamento pagamento = new Pagamento();
      pagamento.setEstacionamento_id(dto.estacionamento_id());
      pagamento.setTipo(dto.tipo());
      pagamento.setValor(calcularValor(estacionamento.get().duracaoTipo(), estacionamento.get().inicio(), 
        estacionamento.get().fim()));
        repositorio.save(pagamento);
      return new PagamentoDTO(pagamento);
    } else {
      throw new EstacionamentoNotFoundException("Ticket não encontrado. Pagamento não efetuado.");
    }
  }

  

  public void excluirPorId(String id) {
    repositorio.deleteById(id);
  }

  public Optional<PagamentoDTO> atualizarPorId(String id, @Valid PagamentoDTO dto) {
    Optional<Pagamento> pagamento = repositorio.findById(id);

    if (pagamento.isPresent()) {
      Pagamento pagamentoAtualizado = dto.mapToPagamento();
      pagamentoAtualizado.setId(id);
      pagamentoAtualizado = repositorio.save(pagamentoAtualizado);      
      return Optional.of(new PagamentoDTO(pagamentoAtualizado)); 
    } else {
      return Optional.empty();
    }
  }

  private Double calcularValor(DuracaoTipo duracaoTipo, LocalDateTime inicio, LocalDateTime fim) {
    if (duracaoTipo == DuracaoTipo.VARIAVEL) {
      Long periodo = Duration.between(inicio, fim).toHours();
      Double valor = periodo * duracaoTipo.getValor();
      return valor;
    } else {
      return duracaoTipo.getValor();
    }

  }
}
