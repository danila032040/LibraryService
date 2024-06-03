package application.queries.library.getById;

import java.util.Optional;

import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import domain.library.Library;
import domain.library.LibraryId;
import domain.library.LibraryRepository;
import domain.library.specifications.LibraryByIdSpecification;

public class GetByIdQueryHandler
		implements
			RequestHandler<GetByIdQuery, ErrorOr<Optional<Library>>> {
	private final LibraryRepository libraryRepository;

	public GetByIdQueryHandler(LibraryRepository libraryRepository) {
		this.libraryRepository = libraryRepository;
	}

	@Override
	public ErrorOr<Optional<Library>> handle(GetByIdQuery request) {
		try {
			return ErrorOr
					.fromResult(
							libraryRepository
									.getFirst(
											new LibraryByIdSpecification(
													new LibraryId(
															request
																	.getLibraryId()))));

		} catch (Exception exc) {
			return ErrorOr.fromErrorMessage(exc.getMessage());
		}
	}

}
