package application.commands.library.changeAddress;

import java.util.Optional;

import base.ddd.DomainEventPublisher;
import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import base.result.Success;
import domain.common.Address;
import domain.common.AddressBuilder;
import domain.library.Library;
import domain.library.LibraryId;
import domain.library.LibraryRepository;
import domain.library.specifications.LibraryByIdSpecification;

public class ChangeAddressCommandHandler
		implements
			RequestHandler<ChangeAddressCommand, ErrorOr<Success>> {

	private final LibraryRepository libraryRepository;
	private final DomainEventPublisher domainEventPublisher;

	public ChangeAddressCommandHandler(LibraryRepository libraryRepository,
			DomainEventPublisher domainEventPublisher) {
		this.libraryRepository = libraryRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public ErrorOr<Success> handle(ChangeAddressCommand request) {
		try {
			Address newAddress = BuildAddressFromRequest(request);
			Optional<Library> optionalExistingLibrary = libraryRepository
					.getFirst(
							new LibraryByIdSpecification(
									new LibraryId(request.getLibraryId())));

			if (optionalExistingLibrary.isEmpty())
				return ErrorOr
						.fromErrorMessage(
								"Library with specified id was not found");

			Library existingLibrary = optionalExistingLibrary.orElseThrow();

			existingLibrary.changeAddress(newAddress);

			libraryRepository.update(existingLibrary);

			domainEventPublisher
					.publishDomainEvents(
							existingLibrary.extractAllDomainEvents());

			return ErrorOr
					.fromResult(
							Success.from("Address were changed successfully"));
		} catch (Exception exc) {
			return ErrorOr.fromErrorMessage(exc.getMessage());
		}
	}

	private Address BuildAddressFromRequest(ChangeAddressCommand request) {
		AddressBuilder addressBuilder = AddressBuilder.createBuilder();

		request.getBuilding().ifPresent(addressBuilder::withBuilding);
		request.getCity().ifPresent(addressBuilder::withCity);
		request.getCountryRegion().ifPresent(addressBuilder::withCountryRegion);
		request.getPostalCode().ifPresent(addressBuilder::withPostalCode);
		request.getStateProvince().ifPresent(addressBuilder::withStateProvince);
		request.getStreet().ifPresent(addressBuilder::withStreet);

		return addressBuilder.build();
	}

}
