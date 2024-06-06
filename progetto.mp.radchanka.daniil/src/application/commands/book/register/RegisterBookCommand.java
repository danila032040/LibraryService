package application.commands.book.register;

import java.util.Objects;
import java.util.Optional;

import base.mediator.request.Request;
import base.result.ErrorOr;
import domain.book.BookId;

public class RegisterBookCommand implements Request<ErrorOr<BookId>> {
    private final String name;
    private final String genre;
    private final int publicationYear;
    private final Optional<Integer> authorId;
    private final Optional<Integer> libraryId;
    
    public RegisterBookCommand(
            String name,
            String genre,
            int publicationYear,
            Optional<Integer> authorId,
            Optional<Integer> libraryId) {
        this.name = Objects.requireNonNull(name);
        this.genre = Objects.requireNonNull(genre);
        this.publicationYear = publicationYear;
        this.authorId = Objects.requireNonNull(authorId);
        this.libraryId = Objects.requireNonNull(libraryId);
    }
    
    public String getName() {
        return name;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public int getPublicationYear() {
        return publicationYear;
    }
    
    public Optional<Integer> getAuthorId() {
        return authorId;
    }
    
    public Optional<Integer> getLibraryId() {
        return libraryId;
    }
}
