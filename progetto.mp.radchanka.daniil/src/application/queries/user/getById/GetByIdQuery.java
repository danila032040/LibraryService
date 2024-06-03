package application.queries.user.getById;

import java.util.Optional;

import base.mediator.request.Request;
import base.result.ErrorOr;
import domain.user.User;

public class GetByIdQuery implements Request<ErrorOr<Optional<User>>> {
	private final int userId;

	public GetByIdQuery(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}
}
