package domain.user.exceptions;

import domain.book.BookId;
import domain.user.User;

public class BookWasAlreadyBorrowedByTheUserDomainEvent extends Exception {
    private static final long serialVersionUID = 1L;
    
    private final User user;
    private final BookId bookIdThatWasAlreadyBorrowed;
    
    public BookWasAlreadyBorrowedByTheUserDomainEvent(User user, BookId bookIdThatWasAlreadyBorrowed) {
        super();
        this.user = user;
        this.bookIdThatWasAlreadyBorrowed = bookIdThatWasAlreadyBorrowed;
    }
    
    public BookId getBookIdThatWasAlreadyBorrowed() {
        return bookIdThatWasAlreadyBorrowed;
    }
    
    public User getUser() {
        return user;
    }
}