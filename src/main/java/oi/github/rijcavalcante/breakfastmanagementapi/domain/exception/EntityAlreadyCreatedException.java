package oi.github.rijcavalcante.breakfastmanagementapi.domain.exception;

public class EntityAlreadyCreatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityAlreadyCreatedException(String mensagem) {
		super(mensagem);
	}
}
