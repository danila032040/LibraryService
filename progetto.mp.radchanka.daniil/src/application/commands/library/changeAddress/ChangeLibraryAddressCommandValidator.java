package application.commands.library.changeAddress;

import application.commands.common.address.AddressCommandData;
import base.result.ValidationResult;
import base.utils.Validator;

public class ChangeLibraryAddressCommandValidator implements Validator<ChangeLibraryAddressCommand> {

	private final Validator<AddressCommandData> addressValidator;

	public ChangeLibraryAddressCommandValidator(Validator<AddressCommandData> addressValidator) {

		this.addressValidator = addressValidator;
	}

	@Override
	public ValidationResult validate(ChangeLibraryAddressCommand request) {
		ValidationResult addressValidationResult = addressValidator.validate(request.getAddress());
		return ValidationResult.create().unionWith(addressValidationResult);

	}
}
