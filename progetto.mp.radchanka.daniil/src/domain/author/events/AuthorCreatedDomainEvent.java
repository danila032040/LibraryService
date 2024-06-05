package domain.author.events;

import base.ddd.DomainEvent;
import domain.author.Author;

public class AuthorCreatedDomainEvent implements DomainEvent {
    private final Author author;
    
    public AuthorCreatedDomainEvent(Author author) {
        this.author = author;
    }
    
    public Author getAuthor() {
        return author;
    }
}
