package br.com.fiap.techchallengeparking.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.techchallengeparking.dtos.PagamentoDTO;
import br.com.fiap.techchallengeparking.dtos.PagamentoInputDTO;
import br.com.fiap.techchallengeparking.service.PagamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

  
  @Autowired
  private PagamentoService servico;

  @GetMapping
  public ResponseEntity<List<PagamentoDTO>> obterPagamentos() {
    return ResponseEntity.ok(servico.obterTodos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PagamentoDTO> obterPagamentosPorId(@PathVariable String id) {
    Optional<PagamentoDTO> pagamento = servico.obterPorId(id);

    if (!pagamento.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(pagamento.get());
  }

  @PostMapping()
  public ResponseEntity<PagamentoDTO> registrarPagamento(@RequestBody @Valid PagamentoInputDTO dto,
      UriComponentsBuilder uriBuilder) {
    PagamentoDTO pagamento = servico.registrarPagamento(dto);
    URI endpoint = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.id()).toUri();
    return ResponseEntity.created(endpoint).body(pagamento);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> excluirveiculo(@PathVariable String id) {
    servico.excluirPorId(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<PagamentoDTO> atualizarveiculo(@PathVariable String id, @RequestBody @Valid PagamentoDTO dto) {
    Optional<PagamentoDTO> pagamento = servico.atualizarPorId(id, dto);
    
    if (!pagamento.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(pagamento.get());
  }
  
}
