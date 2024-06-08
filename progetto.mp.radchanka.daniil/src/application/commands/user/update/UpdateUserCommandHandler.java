package application.commands.user.update;

import java.util.Objects;
import java.util.Optional;

import application.commands.common.address.AddressCommandData;
import base.ddd.DomainEventPublisher;
import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import base.result.SuccessResult;
import base.utils.Mapper;
import domain.common.Address;
import domain.user.User;
import domain.user.UserId;
import domain.user.UserRepository;
import domain.user.specifications.UserByIdSpecification;

public class UpdateUserCommandHandler implements RequestHandler<UpdateUserCommand, ErrorOr<SuccessResult>> {
    private final UserRepository userRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Mapper<AddressCommandData, Address> addressMapper;
    
    public UpdateUserCommandHandler(
            UserRepository userRepository,
            DomainEventPublisher domainEventPublisher,
            Mapper<AddressCommandData, Address> addressMapper) {
        this.userRepository = userRepository;
        this.domainEventPublisher = domainEventPublisher;
        this.addressMapper = addressMapper;
    }
    
    @Override
    public ErrorOr<SuccessResult> handle(UpdateUserCommand request) {
        Optional<Address> newAddress = request.getNewAddress().map(addressMapper::map);
        Optional<String> newPhoneNumber = request.getNewPhoneNumber();
        
        Optional<User> optionalExistingUser = userRepository
                .getFirst(new UserByIdSpecification(new UserId(request.getUserId())));
        
        if (optionalExistingUser.isEmpty()) {
            return ErrorOr.fromErrorMessage("User with specified id was not found");
        }
        
        User existingUser = optionalExistingUser.orElseThrow();
        
        boolean hasPersonalInformationToUpdate = false;
        
        if (isPhoneNumberNewerForTheUser(newPhoneNumber, existingUser)) {
            newPhoneNumber.ifPresent(existingUser::changePhoneNumber);
            hasPersonalInformationToUpdate = true;
        }
        if (isAddressNewerForTheUser(newAddress, existingUser)) {
            newAddress.ifPresent(existingUser::changeAddress);
            hasPersonalInformationToUpdate = true;
        }
        
        if (!hasPersonalInformationToUpdate) {
            return ErrorOr
                    .fromResult(SuccessResult.from("User already contains provided information. Update is not needed"));
        }
        
        userRepository.update(existingUser);
        
        domainEventPublisher.publishDomainEvents(existingUser.extractAllDomainEvents());
        
        return ErrorOr.fromResult(SuccessResult.from("Successfully updated user"));
    }
    
    private Boolean isPhoneNumberNewerForTheUser(Optional<String> newPhoneNumber, User existingUser) {
        return newPhoneNumber
                .map(phoneNumber -> !Objects.equals(existingUser.getPhoneNumber().orElse(phoneNumber), phoneNumber))
                .orElse(false);
    }
    
    private Boolean isAddressNewerForTheUser(Optional<Address> newAddress, User existingUser) {
        return newAddress.map(address -> !Objects.equals(existingUser.getAddress(), address)).orElse(false);
    }
    
}
