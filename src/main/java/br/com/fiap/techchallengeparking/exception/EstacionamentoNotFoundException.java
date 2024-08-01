package br.com.fiap.techchallengeparking.exception;

public class EstacionamentoNotFoundException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

	public EstacionamentoNotFoundException(String message) {
		super(message);
	}

}
