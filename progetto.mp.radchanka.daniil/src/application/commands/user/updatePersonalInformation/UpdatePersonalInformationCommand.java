package application.commands.user.updatePersonalInformation;

import java.util.Optional;

import application.commands.common.data.AddressCommandData;
import base.mediator.request.Request;
import base.result.ErrorOr;
import base.result.Success;

public class UpdatePersonalInformationCommand
		implements
			Request<ErrorOr<Success>> {
	private final int userId;
	private final Optional<AddressCommandData> address;
	private final Optional<String> phoneNumber;

	public UpdatePersonalInformationCommand(int userId,
			Optional<AddressCommandData> address,
			Optional<String> phoneNumber) {
		this.userId = userId;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public int getUserId() {
		return userId;
	}

	public Optional<AddressCommandData> getNewAddress() {
		return address;
	}

	public Optional<String> getNewPhoneNumber() {
		return phoneNumber;
	}
}
