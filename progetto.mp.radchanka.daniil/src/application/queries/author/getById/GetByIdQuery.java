package application.queries.author.getById;

import java.util.Optional;

import base.mediator.request.Request;
import base.result.ErrorOr;
import domain.author.Author;

public class GetByIdQuery implements Request<ErrorOr<Optional<Author>>> {
    private final int authorId;
    
    public GetByIdQuery(int authorId) {
        this.authorId = authorId;
    }
    
    public int getAuthorId() {
        return authorId;
    }
}
