package tests.application.commands.user.update.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import application.commands.common.address.AddressCommandData;
import application.commands.common.address.AddressCommandDataMapper;
import application.commands.user.update.UpdateUserCommand;
import application.commands.user.update.UpdateUserCommandHandler;
import base.result.ErrorOr;
import base.result.ErrorResult;
import base.result.SuccessResult;
import base.utils.Mapper;
import domain.common.Address;
import domain.user.User;
import domain.user.UserId;
import tests.application.commands.user.update.mocks.DomainEventPublisherMock;
import tests.application.commands.user.update.mocks.UserRepositoryMock;

public class UpdateUserCommandHandlerUnitTests {
    
    private UserRepositoryMock userRepository;
    private DomainEventPublisherMock domainEventPublisher;
    private UpdateUserCommandHandler handler;
    
    @Before
    public void setUp() {
        userRepository = new UserRepositoryMock();
        domainEventPublisher = new DomainEventPublisherMock();
        Mapper<AddressCommandData, Address> addressMapper = new AddressCommandDataMapper();
        handler = new UpdateUserCommandHandler(userRepository, domainEventPublisher, addressMapper);
    }
    
    @Test
    public void handle_WhenUserNotFound_ShouldReturnErrorMessage() {
        UpdateUserCommand command = new UpdateUserCommand(0, Optional.empty(), Optional.empty());
        userRepository.setUser(Optional.empty());
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isTrue();
        assertThat(result.getError()).map(ErrorResult::getMessage).hasValue("User with specified id was not found");
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).isEmpty();
    }
    
    @Test
    public void handle_WhenNoInformationToUpdate_ShouldReturnSuccessMessage() {
        UpdateUserCommand command = new UpdateUserCommand(0, Optional.empty(), Optional.empty());
        User user = createUserWithoutDomainEvents(0);
        userRepository.setUser(Optional.of(user));
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isFalse();
        assertThat(result.getResult())
                .map(SuccessResult::getMessage)
                .hasValue("User already contains provided information. Update is not needed");
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).isEmpty();
    }
    
    @Test
    public void handle_WhenPhoneNumberUpdated_ShouldReturnSuccessMessage() {
        UpdateUserCommand command = new UpdateUserCommand(0, Optional.empty(), Optional.of("TestNew"));
        User user = createUserWithoutDomainEvents(0);
        userRepository.setUser(Optional.of(user));
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isFalse();
        assertThat(result.getResult()).map(SuccessResult::getMessage).hasValue("Successfully updated user");
    }
    
    @Test
    public void handle_WhenAddressUpdated_ShouldReturnSuccessMessage() {
        AddressCommandData newAddressData = new AddressCommandData(
                Optional.empty(),
                Optional.empty(),
                Optional.of("New City"),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
        UpdateUserCommand command = new UpdateUserCommand(0, Optional.of(newAddressData), Optional.empty());
        User user = createUserWithoutDomainEvents(0);
        userRepository.setUser(Optional.of(user));
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isFalse();
        assertThat(result.getResult()).map(SuccessResult::getMessage).hasValue("Successfully updated user");
    }
    
    private User createUserWithoutDomainEvents(int id) {
        User user = User
                .createNewUser(
                        new UserId(id),
                        "Test",
                        "Test",
                        new Address(
                                Optional.of("Test"),
                                Optional.of("Test"),
                                Optional.of("Test"),
                                Optional.of("Test"),
                                Optional.of("Test"),
                                Optional.of("Test")),
                        Optional.of("Test"));
        user.extractAllDomainEvents();
        return user;
    }
}
