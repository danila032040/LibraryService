package application.commands.author.register;

import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import domain.author.AuthorId;

public class RegisterAuthorCommandHandler implements RequestHandler<RegisterAuthorCommand, ErrorOr<AuthorId>> {

	@Override
	public ErrorOr<AuthorId> handle(RegisterAuthorCommand request) {
		try {
			return ErrorOr.fromErrorMessage("NotFound");
		} catch (Exception exc) {
			return ErrorOr.fromErrorMessage(exc.getMessage());
		}
	}

}
