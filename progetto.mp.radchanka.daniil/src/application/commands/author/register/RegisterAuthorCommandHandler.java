package application.commands.author.register;

import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import base.result.ValidationResult;
import base.utils.Validator;
import domain.author.Author;
import domain.author.AuthorId;
import domain.author.AuthorRepository;

public class RegisterAuthorCommandHandler
		implements
			RequestHandler<RegisterAuthorCommand, ErrorOr<AuthorId>> {

	private final Validator<RegisterAuthorCommand> validator;
	private final AuthorRepository authorRepository;

	public RegisterAuthorCommandHandler(
			Validator<RegisterAuthorCommand> validator,
			AuthorRepository authorRepository) {
		this.validator = validator;
		this.authorRepository = authorRepository;
	}

	@Override
	public ErrorOr<AuthorId> handle(RegisterAuthorCommand request) {
		try {
			ValidationResult validationResult = validator.validate(request);
			if (!validationResult.isValid()) {
				return ErrorOr
						.fromErrorMessage(
								validationResult
										.getErrors()
										.get(0)
										.getErrorMessage());
			}

			Author authorToRegister = Author
					.createNewAuthor(
							authorRepository.generateNewAuthorId(),
							request.getName(),
							request.getSurname(),
							request.getCountry());

			authorRepository.add(authorToRegister);

			return ErrorOr.fromResult(authorToRegister.getId());
		} catch (Exception exc) {
			return ErrorOr.fromErrorMessage(exc.getMessage());
		}
	}

}
