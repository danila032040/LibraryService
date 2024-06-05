package application.queries.author.getById;

import java.util.Optional;

import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import domain.author.Author;
import domain.author.AuthorId;
import domain.author.AuthorRepository;
import domain.author.specifications.AuthorByIdSpecification;

public class GetByIdQueryHandler implements RequestHandler<GetByIdQuery, ErrorOr<Optional<Author>>> {
    private final AuthorRepository authorRepository;
    
    public GetByIdQueryHandler(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    @Override
    public ErrorOr<Optional<Author>> handle(GetByIdQuery request) {
        try {
            return ErrorOr
                    .fromResult(
                            authorRepository
                                    .getFirst(new AuthorByIdSpecification(new AuthorId(request.getAuthorId()))));
            
        } catch (Exception exc) {
            return ErrorOr.fromErrorMessage(exc.getMessage());
        }
    }
    
}
