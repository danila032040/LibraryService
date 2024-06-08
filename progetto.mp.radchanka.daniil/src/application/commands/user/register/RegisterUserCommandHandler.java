package application.commands.user.register;

import application.commands.common.address.AddressCommandData;
import base.ddd.DomainEventPublisher;
import base.mediator.request.RequestHandler;
import base.repository.AlreadyExistsException;
import base.result.ErrorOr;
import base.utils.Mapper;
import domain.common.Address;
import domain.user.User;
import domain.user.UserId;
import domain.user.UserRepository;

public class RegisterUserCommandHandler implements RequestHandler<RegisterUserCommand, ErrorOr<UserId>> {
    private final UserRepository userRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Mapper<AddressCommandData, Address> addressMapper;
    
    public RegisterUserCommandHandler(
            UserRepository userRepository,
            DomainEventPublisher domainEventPublisher,
            Mapper<AddressCommandData, Address> addressMapper) {
        this.userRepository = userRepository;
        this.domainEventPublisher = domainEventPublisher;
        this.addressMapper = addressMapper;
    }
    
    @Override
    public ErrorOr<UserId> handle(RegisterUserCommand request) {
        try {
            
            User userToRegister = User
                    .createNewUser(
                            userRepository.generateNewUserId(),
                            request.getName(),
                            request.getSurname(),
                            addressMapper.map(request.getAddress()),
                            request.getPhoneNumber());
            
            userRepository.add(userToRegister);
            
            domainEventPublisher.publishDomainEvents(userToRegister.extractAllDomainEvents());
            
            return ErrorOr.fromResult(userToRegister.getId());
        } catch (AlreadyExistsException exc) {
            return ErrorOr.fromErrorMessage("User already exists");
        }
    }
    
}
