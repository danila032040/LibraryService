package application.commands.user.register;

import application.commands.common.address.AddressCommandData;
import base.ddd.DomainEventPublisher;
import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import base.result.ValidationResult;
import base.utils.Mapper;
import base.utils.Validator;
import domain.common.Address;
import domain.user.User;
import domain.user.UserId;
import domain.user.UserRepository;

public class RegisterUserCommandHandler implements RequestHandler<RegisterUserCommand, ErrorOr<UserId>> {
    private final Validator<RegisterUserCommand> validator;
    private final UserRepository userRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Mapper<AddressCommandData, Address> addressMapper;
    
    public RegisterUserCommandHandler(
            Validator<RegisterUserCommand> validator,
            UserRepository userRepository,
            DomainEventPublisher domainEventPublisher,
            Mapper<AddressCommandData, Address> addressMapper) {
        this.validator = validator;
        this.userRepository = userRepository;
        this.domainEventPublisher = domainEventPublisher;
        this.addressMapper = addressMapper;
    }
    
    @Override
    public ErrorOr<UserId> handle(RegisterUserCommand request) {
        try {
            ValidationResult validationResult = validator.validate(request);
            if (!validationResult.isValid()) {
                return ErrorOr.fromErrorMessage(validationResult.getErrors().get(0).getErrorMessage());
            }
            
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
        } catch (Exception exc) {
            return ErrorOr.fromErrorMessage(exc.getMessage());
        }
    }
    
}
