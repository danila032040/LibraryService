package application.commands.library.register;

import application.commands.common.data.AddressCommandData;
import base.ddd.DomainEventPublisher;
import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import base.utils.Mapper;
import domain.common.Address;
import domain.library.Library;
import domain.library.LibraryId;
import domain.library.LibraryRepository;

public class RegisterLibraryCommandHandler
		implements
			RequestHandler<RegisterLibraryCommand, ErrorOr<LibraryId>> {

	private final LibraryRepository libraryRepository;
	private final DomainEventPublisher domainEventPublisher;
	private final Mapper<AddressCommandData, Address> addressMapper;

	public RegisterLibraryCommandHandler(LibraryRepository libraryRepository,
			DomainEventPublisher domainEventPublisher,
			Mapper<AddressCommandData, Address> addressMapper) {
		this.libraryRepository = libraryRepository;
		this.domainEventPublisher = domainEventPublisher;
		this.addressMapper = addressMapper;
	}

	@Override
	public ErrorOr<LibraryId> handle(RegisterLibraryCommand request) {
		try {
			Address address = addressMapper.map(request.getAddress());
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
}
