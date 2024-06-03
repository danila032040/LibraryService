package application.commands.library.register;

import application.commands.common.data.AddressCommandData;
import base.ddd.DomainEventPublisher;
import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import base.result.ValidationResult;
import base.utils.Mapper;
import base.utils.Validator;
import domain.common.Address;
import domain.library.Library;
import domain.library.LibraryId;
import domain.library.LibraryRepository;

public class RegisterLibraryCommandHandler implements RequestHandler<RegisterLibraryCommand, ErrorOr<LibraryId>> {
	private final Validator<RegisterLibraryCommand> validator;
	private final LibraryRepository libraryRepository;
	private final DomainEventPublisher domainEventPublisher;
	private final Mapper<AddressCommandData, Address> addressMapper;

	public RegisterLibraryCommandHandler(Validator<RegisterLibraryCommand> validator,
			LibraryRepository libraryRepository, DomainEventPublisher domainEventPublisher,
			Mapper<AddressCommandData, Address> addressMapper) {
		this.validator = validator;
		this.libraryRepository = libraryRepository;
		this.domainEventPublisher = domainEventPublisher;
		this.addressMapper = addressMapper;
	}

	@Override
	public ErrorOr<LibraryId> handle(RegisterLibraryCommand request) {
		try {
			ValidationResult validationResult = validator.validate(request);
			if (!validationResult.isValid()) {
				return ErrorOr.fromErrorMessage(validationResult.getErrors().get(0).getErrorMessage());
			}

			Address address = addressMapper.map(request.getAddress());
			Library libraryToRegister = Library.createNewLibrary(libraryRepository.generateNewLibraryId(), address);

			libraryRepository.add(libraryToRegister);

			domainEventPublisher.publishDomainEvents(libraryToRegister.extractAllDomainEvents());

			return ErrorOr.fromResult(libraryToRegister.getId());
		} catch (Exception exc) {
			return ErrorOr.fromErrorMessage(exc.getMessage());
		}
	}
}
