package tests.application.commands.user.register.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import application.commands.common.address.AddressCommandData;
import application.commands.common.address.AddressCommandDataMapper;
import application.commands.user.register.RegisterUserCommand;
import application.commands.user.register.RegisterUserCommandHandler;
import base.repository.AlreadyExistsException;
import base.result.ErrorOr;
import base.result.ErrorResult;
import base.utils.Mapper;
import domain.common.Address;
import domain.user.UserId;
import domain.user.events.UserCreatedDomainEvent;
import tests.application.commands.user.register.mocks.DomainEventPublisherMock;
import tests.application.commands.user.register.mocks.UserRepositoryMock;

public class RegisterUserCommandHandlerUnitTests {
    
    private UserRepositoryMock userRepository;
    private DomainEventPublisherMock domainEventPublisher;
    private RegisterUserCommandHandler handler;
    
    @Before
    public void setUp() {
        userRepository = new UserRepositoryMock();
        domainEventPublisher = new DomainEventPublisherMock();
        Mapper<AddressCommandData, Address> addressMapper = new AddressCommandDataMapper();
        handler = new RegisterUserCommandHandler(userRepository, domainEventPublisher, addressMapper);
    }
    
    @Test
    public void handle_WhenUserSuccessfullyRegistered_ShouldReturnUserId() throws AlreadyExistsException {
        RegisterUserCommand command = new RegisterUserCommand(
                "Test",
                "Test",
                Optional.empty(),
                new AddressCommandData(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()));
        UserId generatedUserId = new UserId(1);
        userRepository.setGeneratedUserId(generatedUserId);
        
        ErrorOr<UserId> result = handler.handle(command);
        
        assertThat(result.getResult()).map(UserId::getId).hasValue(1);
        assertThat(userRepository.isAddCalled()).isTrue();
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).hasValueSatisfying(domainEvents -> {
            assertThat(domainEvents).satisfiesExactly(domainEvent1 -> {
                assertThat(domainEvent1)
                        .isInstanceOfSatisfying(UserCreatedDomainEvent.class, userCreatedDomainEvent -> {
                            assertThat(userCreatedDomainEvent.getUser().getId()).isEqualTo(generatedUserId);
                        });
            });
        });
    }
    
    @Test
    public void handle_WhenUserAlreadyExists_ShouldReturnErrorMessage() throws AlreadyExistsException {
        RegisterUserCommand command = new RegisterUserCommand(
                "Test",
                "Test",
                Optional.empty(),
                new AddressCommandData(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()));
        userRepository.setGeneratedUserId(new UserId(0));
        userRepository.setThrowAlreadyExistsException(true);
        
        ErrorOr<UserId> result = handler.handle(command);
        
        assertThat(result.getError()).map(ErrorResult::getMessage).hasValue("User already exists");
        assertThat(userRepository.isAddCalled()).isFalse();
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).isEmpty();
    }
}
