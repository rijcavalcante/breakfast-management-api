package oi.github.rijcavalcante.breakfastmanagementapi.domain.exception.handlererror;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

public abstract class ApiSubError {

}

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
class ApiValidationError extends ApiSubError{
	private String object;
	private String field;
	private Object rejectedValue;
	private String message;
	
	ApiValidationError(String object, String message) {
		super();
		this.object = object;
		this.message = message;
	}
	
	
	
}
