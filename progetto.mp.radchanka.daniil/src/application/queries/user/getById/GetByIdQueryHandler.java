package application.queries.user.getById;

import java.util.Optional;

import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import domain.user.User;
import domain.user.UserId;
import domain.user.UserRepository;
import domain.user.specifications.UserByIdSpecification;

public class GetByIdQueryHandler
		implements
			RequestHandler<GetByIdQuery, ErrorOr<Optional<User>>> {
	private final UserRepository userRepository;

	public GetByIdQueryHandler(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public ErrorOr<Optional<User>> handle(GetByIdQuery request) {
		try {
			return ErrorOr
					.fromResult(
							userRepository
									.getFirst(
											new UserByIdSpecification(
													new UserId(
															request
																	.getUserId()))));

		} catch (Exception exc) {
			return ErrorOr.fromErrorMessage(exc.getMessage());
		}
	}

}
