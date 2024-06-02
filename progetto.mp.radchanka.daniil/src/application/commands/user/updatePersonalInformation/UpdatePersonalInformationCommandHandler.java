package application.commands.user.updatePersonalInformation;

import java.util.Objects;
import java.util.Optional;

import application.commands.common.data.AddressCommandData;
import base.ddd.DomainEventPublisher;
import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import base.result.Success;
import base.utils.Mapper;
import domain.common.Address;
import domain.user.User;
import domain.user.UserId;
import domain.user.UserRepository;
import domain.user.specifications.UserByIdSpecification;

public class UpdatePersonalInformationCommandHandler
		implements
			RequestHandler<UpdatePersonalInformationCommand, ErrorOr<Success>> {

	private final UserRepository userRepository;
	private final DomainEventPublisher domainEventPublisher;
	private final Mapper<AddressCommandData, Address> addressMapper;

	public UpdatePersonalInformationCommandHandler(
			UserRepository userRepository,
			DomainEventPublisher domainEventPublisher,
			Mapper<AddressCommandData, Address> addressMapper) {
		this.userRepository = userRepository;
		this.domainEventPublisher = domainEventPublisher;
		this.addressMapper = addressMapper;
	}

	@Override
	public ErrorOr<Success> handle(UpdatePersonalInformationCommand request) {
		try {
			Optional<Address> newAddress = request
					.getNewAddress()
					.map(addressMapper::map);
			Optional<String> newPhoneNumber = request.getNewPhoneNumber();

			Optional<User> optionalExistingUser = userRepository
					.getFirst(
							new UserByIdSpecification(
									new UserId(request.getUserId())));

			if (optionalExistingUser.isEmpty())
				return ErrorOr
						.fromErrorMessage(
								"User with specified id was not found");

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

			if (!hasPersonalInformationToUpdate)
				return ErrorOr
						.fromResult(
								Success
										.from(
												"User already contains provided personal information. Update not needed"));

			userRepository.update(existingUser);

			domainEventPublisher
					.publishDomainEvents(existingUser.extractAllDomainEvents());

			return ErrorOr
					.fromResult(
							Success
									.from(
											"Successfully updated user personal information"));
		} catch (Exception exc) {
			return ErrorOr.fromErrorMessage(exc.getMessage());
		}
	}

	private Boolean isPhoneNumberNewerForTheUser(
			Optional<String> newPhoneNumber,
			User existingUser) {
		return newPhoneNumber
				.map(
						phoneNumber -> existingUser.getPhoneNumber().isEmpty()
								|| !Objects
										.equals(
												existingUser
														.getPhoneNumber()
														.get(),
												phoneNumber))
				.orElse(false);
	}

	private Boolean isAddressNewerForTheUser(
			Optional<Address> newAddress,
			User existingUser) {
		return newAddress
				.map(
						address -> !Objects
								.equals(existingUser.getAddress(), address))
				.orElse(false);
	}

}
