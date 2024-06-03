package application.commands.library.changeAddress;

import java.util.Optional;

import application.commands.common.address.AddressCommandData;
import base.ddd.DomainEventPublisher;
import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import base.result.Success;
import base.result.ValidationResult;
import base.utils.Mapper;
import base.utils.Validator;
import domain.common.Address;
import domain.library.Library;
import domain.library.LibraryId;
import domain.library.LibraryRepository;
import domain.library.specifications.LibraryByIdSpecification;

public class ChangeLibraryAddressCommandHandler
		implements RequestHandler<ChangeLibraryAddressCommand, ErrorOr<Success>> {

	private final Validator<ChangeLibraryAddressCommand> validator;
	private final LibraryRepository libraryRepository;
	private final DomainEventPublisher domainEventPublisher;
	private final Mapper<AddressCommandData, Address> addressMapper;

	public ChangeLibraryAddressCommandHandler(
			Validator<ChangeLibraryAddressCommand> validator,
			LibraryRepository libraryRepository,
			DomainEventPublisher domainEventPublisher, Mapper<AddressCommandData, Address> addressMapper) {
		this.validator = validator;
		this.libraryRepository = libraryRepository;
		this.domainEventPublisher = domainEventPublisher;
		this.addressMapper = addressMapper;
	}

	@Override
	public ErrorOr<Success> handle(ChangeLibraryAddressCommand request) {
		try {
			ValidationResult validationResult = validator.validate(request);
			if (!validationResult.isValid()) {
				return ErrorOr.fromErrorMessage(validationResult.getErrors().get(0).getErrorMessage());
			}
			
			Address newAddress = addressMapper.map(request.getAddress());
			Optional<Library> optionalExistingLibrary = libraryRepository
					.getFirst(new LibraryByIdSpecification(new LibraryId(request.getLibraryId())));

			if (optionalExistingLibrary.isEmpty())
				return ErrorOr.fromErrorMessage("Library with specified id was not found");

			Library existingLibrary = optionalExistingLibrary.orElseThrow();

			existingLibrary.changeAddress(newAddress);

			libraryRepository.update(existingLibrary);

			domainEventPublisher.publishDomainEvents(existingLibrary.extractAllDomainEvents());

			return ErrorOr.fromResult(Success.from("Address were changed successfully"));
		} catch (Exception exc) {
			return ErrorOr.fromErrorMessage(exc.getMessage());
		}
	}

}
