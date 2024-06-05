package domain.book.events;

import base.ddd.DomainEvent;
import domain.book.Book;
import domain.user.UserId;

public class BookBorrowedByUserDomainEvent implements DomainEvent {
    private final Book borrowedBook;
    private final UserId userIdThatHadBorrowedTheBook;
    
    public BookBorrowedByUserDomainEvent(Book borrowedBook, UserId userIdThatHadBorrowedTheBook) {
        this.borrowedBook = borrowedBook;
        this.userIdThatHadBorrowedTheBook = userIdThatHadBorrowedTheBook;
    }
    
    public Book getBorrowedBook() {
        return borrowedBook;
    }
    
    public UserId getUserIdThatHadBorrowedTheBook() {
        return userIdThatHadBorrowedTheBook;
    }
}
