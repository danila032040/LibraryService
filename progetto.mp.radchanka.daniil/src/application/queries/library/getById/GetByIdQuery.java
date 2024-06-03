package application.queries.library.getById;

import java.util.Optional;

import base.mediator.request.Request;
import base.result.ErrorOr;
import domain.library.Library;

public class GetByIdQuery implements Request<ErrorOr<Optional<Library>>> {
	private final int libraryId;

	public GetByIdQuery(int libraryId) {
		this.libraryId = libraryId;
	}

	public int getLibraryId() {
		return libraryId;
	}
}
