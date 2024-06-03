package application.commands.user.update;

import java.util.Optional;
import java.util.stream.Stream;

import application.commands.common.data.AddressCommandData;
import base.result.Error;
import base.result.ValidationResult;
import base.utils.Validator;

public class UpdateUserCommandValidator
		implements
			Validator<UpdateUserCommand> {

	private final Validator<AddressCommandData> addressValidator;

	public UpdateUserCommandValidator(
			Validator<AddressCommandData> addressValidator) {

		this.addressValidator = addressValidator;
	}

	@Override
	public ValidationResult validate(UpdateUserCommand request) {
		ValidationResult validationResult = ValidationResult.create();

		long providedFieldsCount = Stream
				.of(request.getNewAddress(), request.getNewPhoneNumber())
				.filter(Optional::isPresent)
				.count();

		return request
				.getNewAddress()
				.map(addressValidator::validate)
				.map(validationResult::unionWith)
				.orElse(validationResult)
				.withErrorIf(
						() -> providedFieldsCount == 0,
						Error
								.from(
										"At least new phone number or new address must be present"))
				.withErrorIf(
						() -> request
								.getNewPhoneNumber()
								.map(String::isBlank)
								.orElse(false),
						Error
								.from(
										"Phone number must be not whitespace if provided"));

	}
}
