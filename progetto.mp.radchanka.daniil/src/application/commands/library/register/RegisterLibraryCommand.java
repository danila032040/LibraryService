package application.commands.library.register;

import application.commands.common.address.AddressCommandData;
import base.mediator.request.Request;
import base.result.ErrorOr;
import domain.library.LibraryId;

public class RegisterLibraryCommand implements Request<ErrorOr<LibraryId>> {
    private AddressCommandData address;
    
    public RegisterLibraryCommand(AddressCommandData address) {
        this.address = address;
    }
    
    public AddressCommandData getAddress() {
        return address;
    }
    
}
