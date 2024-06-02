package application.commands.library.register;

import base.ddd.DomainEventPublisher;
import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import domain.common.Address;
import domain.common.AddressBuilder;
import domain.library.Library;
import domain.library.LibraryId;
import domain.library.LibraryRepository;

public class RegisterLibraryCommandHandler
		implements
			RequestHandler<RegisterLibraryCommand, ErrorOr<LibraryId>> {

	private final LibraryRepository libraryRepository;
	private final DomainEventPublisher domainEventPublisher;

	public RegisterLibraryCommandHandler(LibraryRepository libraryRepository,
			DomainEventPublisher domainEventPublisher) {
		this.libraryRepository = libraryRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public ErrorOr<LibraryId> handle(RegisterLibraryCommand request) {
		try {
			Address address = BuildAddressFromRequest(request);
			Library libraryToRegister = Library
					.createNewLibrary(
							libraryRepository.generateNewLibraryId(),
							address);

			libraryRepository.add(libraryToRegister);

			domainEventPublisher
					.publishDomainEvents(
							libraryToRegister.extractAllDomainEvents());

			return ErrorOr.fromResult(libraryToRegister.getId());
		} catch (Exception exc) {
			return ErrorOr.fromErrorMessage(exc.getMessage());
		}
	}

	private Address BuildAddressFromRequest(RegisterLibraryCommand request) {
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
