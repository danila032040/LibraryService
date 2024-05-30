package application.commands.author.register;

import base.mediator.request.Request;
import base.result.ErrorOr;
import domain.author.AuthorId;

public class RegisterAuthorCommand implements Request<ErrorOr<AuthorId>> {
}
