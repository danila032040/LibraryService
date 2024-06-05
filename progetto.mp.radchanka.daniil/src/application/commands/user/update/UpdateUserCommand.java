package application.commands.user.update;

import java.util.Optional;

import application.commands.common.address.AddressCommandData;
import base.mediator.request.Request;
import base.result.ErrorOr;
import base.result.SuccessResult;

public class UpdateUserCommand implements Request<ErrorOr<SuccessResult>> {
    private final int userId;
    private final Optional<AddressCommandData> newAddress;
    private final Optional<String> newPhoneNumber;
    
    public UpdateUserCommand(int userId, Optional<AddressCommandData> newAddress, Optional<String> newPhoneNumber) {
        this.userId = userId;
        this.newAddress = newAddress;
        this.newPhoneNumber = newPhoneNumber;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public Optional<AddressCommandData> getNewAddress() {
        return newAddress;
    }
    
    public Optional<String> getNewPhoneNumber() {
        return newPhoneNumber;
    }
}
