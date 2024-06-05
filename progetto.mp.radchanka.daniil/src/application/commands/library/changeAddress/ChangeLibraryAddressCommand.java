package application.commands.library.changeAddress;

import application.commands.common.address.AddressCommandData;
import base.mediator.request.Request;
import base.result.ErrorOr;
import base.result.SuccessResult;

public class ChangeLibraryAddressCommand implements Request<ErrorOr<SuccessResult>> {
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
