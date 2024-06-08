package application.commands.user.register;

import java.util.Objects;
import java.util.Optional;

import application.commands.common.address.AddressCommandData;
import base.mediator.request.Request;
import base.result.ErrorOr;
import domain.user.UserId;

public class RegisterUserCommand implements Request<ErrorOr<UserId>> {
    private final String name;
    private final String surname;
    private final Optional<String> phoneNumber;
    private final AddressCommandData address;
    
    public RegisterUserCommand(String name, String surname, Optional<String> phoneNumber, AddressCommandData address) {
        this.name = Objects.requireNonNull(name);
        this.surname = Objects.requireNonNull(surname);
        this.phoneNumber = Objects.requireNonNull(phoneNumber);
        this.address = Objects.requireNonNull(address);
    }
    
    public AddressCommandData getAddress() {
        return address;
    }
    
    public String getName() {
        return name;
    }
    
    public Optional<String> getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getSurname() {
        return surname;
    }
}
