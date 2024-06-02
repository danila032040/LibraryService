package application.commands.author.register;

import base.result.Error;
import base.result.ValidationResult;
import base.utils.Validator;

public class RegisterAuthorCommandValidator
		implements
			Validator<RegisterAuthorCommand> {

	@Override
	public ValidationResult validate(RegisterAuthorCommand request) {
		return ValidationResult
				.create()
				.withErrorIf(
						() -> request.getName() == null
								|| request.getName().isBlank(),
						Error.from("Name must be not whitespace and not empty"))
				.withErrorIf(
						() -> request.getSurname() == null
								|| request.getSurname().isBlank(),
						Error
								.from(
										"Surname must be not whitespace and not empty"))
				.withErrorIf(
						() -> request.getCountry() == null
								|| request.getCountry().isBlank(),
						Error
								.from(
										"Country must be not whitespace and not empty"));
	}

}
