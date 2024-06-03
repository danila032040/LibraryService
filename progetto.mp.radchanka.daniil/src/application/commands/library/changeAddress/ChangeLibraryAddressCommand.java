package application.commands.library.changeAddress;

import application.commands.common.data.AddressCommandData;
import base.mediator.request.Request;
import base.result.ErrorOr;
import base.result.Success;

public class ChangeLibraryAddressCommand implements Request<ErrorOr<Success>> {
	private final int libraryId;
	private final AddressCommandData address;

	public ChangeLibraryAddressCommand(int libraryId, AddressCommandData address) {
		this.libraryId = libraryId;
		this.address = address;
	}

	public int getLibraryId() {
		return libraryId;
	}

	public AddressCommandData getAddress() {
		return address;
	}
}
