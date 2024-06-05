package tests.domain.book.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Collection;
import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.ddd.DomainEvent;
import domain.book.Book;
import domain.book.BookId;
import domain.book.events.BookBorrowedByUserDomainEvent;
import domain.book.events.BookCreatedDomainEvent;
import domain.book.events.BookReturnedByUserDomainEvent;
import domain.book.exceptions.BookIsAlreadyBorrowedByAnotherUserDomainException;
import domain.book.exceptions.UserTriedToReturnTheBookOfAnotherUserDomainException;
import domain.book.exceptions.UserTriedToReturnTheBookThatWereNotBorrowedDomainException;
import domain.user.UserId;

public class BookUnitTests {
    @Test
    public void borrowByUser_WhenBookWereBorrowedByOtherUser_ShouldThrowBookIsAlreadyBorrowedByAnotherUserDomainException()
            throws BookIsAlreadyBorrowedByAnotherUserDomainException {
        UserId userId1 = new UserId(1);
        UserId userId2 = new UserId(2);
        Book book = Book.createNewBook(new BookId(1), "", "", 2000, Optional.empty(), Optional.empty());
        book.extractAllDomainEvents();
        
        book.borrowByUser(userId1);
        ThrowingCallable actual = () -> book.borrowByUser(userId2);
        
        assertThatExceptionOfType(BookIsAlreadyBorrowedByAnotherUserDomainException.class)
                .isThrownBy(actual)
                .satisfies(exception -> {
                    assertThat(exception.getBook()).isSameAs(book);
                    assertThat(exception.getUserIdThatHadBorrowedTheBook()).isSameAs(userId1);
                    assertThat(exception.getUserIdThatHadTriedToBorrowTheBook()).isSameAs(userId2);
                });
    }
    
    @Test
    public void borrowByUser_WhenBookWereBorrowedByTheSameUser_ShouldThrowBookIsAlreadyBorrowedByAnotherUserDomainException()
            throws BookIsAlreadyBorrowedByAnotherUserDomainException {
        UserId userId = new UserId(1);
        Book book = Book.createNewBook(new BookId(1), "", "", 2000, Optional.empty(), Optional.empty());
        book.extractAllDomainEvents();
        
        book.borrowByUser(userId);
        ThrowingCallable actual = () -> book.borrowByUser(userId);
        
        assertThatExceptionOfType(BookIsAlreadyBorrowedByAnotherUserDomainException.class)
                .isThrownBy(actual)
                .satisfies(exception -> {
                    assertThat(exception.getBook()).isSameAs(book);
                    assertThat(exception.getUserIdThatHadBorrowedTheBook()).isSameAs(userId);
                    assertThat(exception.getUserIdThatHadTriedToBorrowTheBook()).isSameAs(userId);
                });
    }
    
    @Test
    public void borrowByUser_WhenBookWereNotBorrowedByAnyOne_ShouldSetBorrowedByUserAndRegisterBookBorrowedByUserDomainEvent()
            throws BookIsAlreadyBorrowedByAnotherUserDomainException {
        UserId userId = new UserId(0);
        Book book = Book.createNewBook(new BookId(1), "", "", 2000, Optional.empty(), Optional.empty());
        book.extractAllDomainEvents();
        
        book.borrowByUser(userId);
        
        Optional<UserId> actualBorrowedByUserId = book.getBorrowedByUserId();
        Collection<DomainEvent> actualDomainEvents = book.extractAllDomainEvents();
        
        assertThat(actualBorrowedByUserId).hasValue(userId);
        assertThat(actualDomainEvents)
                .satisfiesExactly(
                        x -> assertThat(x).isInstanceOfSatisfying(BookBorrowedByUserDomainEvent.class, domainEvent -> {
                            assertThat(domainEvent.getBorrowedBook()).isSameAs(book);
                            assertThat(domainEvent.getUserIdThatHadBorrowedTheBook()).isSameAs(userId);
                        }));
    }
    
    @Test
    public void createClone_ShouldReturnEqualButNotTheSameInstance() {
        Book book = Book.createNewBook(new BookId(1), "", "", 2000, Optional.empty(), Optional.empty());
        
        Book actual = book.createClone();
        
        assertThat(actual).isNotSameAs(book).isEqualTo(book);
    }
    
    @Test
    public void createNewBook_ShouldRegisterBookCreatedDomainEvent() {
        Book book = Book.createNewBook(new BookId(1), "", "", 2000, Optional.empty(), Optional.empty());
        
        Collection<DomainEvent> actual = book.extractAllDomainEvents();
        
        assertThat(actual)
                .satisfiesExactly(
                        x -> assertThat(x).isInstanceOfSatisfying(BookCreatedDomainEvent.class, domainEvent -> {
                            assertThat(domainEvent.getBook()).isSameAs(book);
                        }));
    }
    
    @Test
    public void createNewBook_WhenGenreIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> Book
                .createNewBook(new BookId(0), "", null, 2000, Optional.empty(), Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createNewBook_WhenNameIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> Book
                .createNewBook(new BookId(0), null, "", 2000, Optional.empty(), Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createNewBook_WhenOptionalAuthorIdIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> Book.createNewBook(new BookId(0), "", "", 2000, null, Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createNewBook_WhenOptionalLibraryIdIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> Book.createNewBook(new BookId(0), "", "", 2000, Optional.empty(), null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createNewBook_WhenPublicationYearIsNegative_ShouldThrowIllegalArgumentException() {
        ThrowingCallable actual = () -> Book
                .createNewBook(new BookId(0), "", "", -1, Optional.empty(), Optional.empty());
        
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(actual);
    }
    
    @Test
    public void returnByUser_WhenBookWereBorrowedByOtherUser_ShouldUserTriedToReturnTheBookOfAnotherUserDomainException()
            throws BookIsAlreadyBorrowedByAnotherUserDomainException {
        UserId userId1 = new UserId(1);
        UserId userId2 = new UserId(2);
        Book book = Book.createNewBook(new BookId(1), "", "", 2000, Optional.empty(), Optional.empty());
        book.extractAllDomainEvents();
        
        book.borrowByUser(userId1);
        ThrowingCallable actual = () -> book.returnByUser(userId2);
        
        assertThatExceptionOfType(UserTriedToReturnTheBookOfAnotherUserDomainException.class)
                .isThrownBy(actual)
                .satisfies(exception -> {
                    assertThat(exception.getBook()).isSameAs(book);
                    assertThat(exception.getUserThatHadBorrowedTheBook()).isSameAs(userId1);
                    assertThat(exception.getUserIdThatHadTriedToReturnTheBook()).isSameAs(userId2);
                });
    }
    
    @Test
    public void returnByUser_WhenBookWereBorrowedByTheSameUser_ShouldResetBorrowedByUserAndRegisterBookReturnedByUserDomainEvent()
            throws BookIsAlreadyBorrowedByAnotherUserDomainException,
            UserTriedToReturnTheBookOfAnotherUserDomainException,
            UserTriedToReturnTheBookThatWereNotBorrowedDomainException {
        UserId userId = new UserId(0);
        Book book = Book.createNewBook(new BookId(1), "", "", 2000, Optional.empty(), Optional.empty());
        book.extractAllDomainEvents();
        
        book.borrowByUser(userId);
        book.extractAllDomainEvents();
        book.returnByUser(userId);
        
        Optional<UserId> actualBorrowedByUserId = book.getBorrowedByUserId();
        Collection<DomainEvent> actualDomainEvents = book.extractAllDomainEvents();
        
        assertThat(actualBorrowedByUserId).isEmpty();
        assertThat(actualDomainEvents)
                .satisfiesExactly(
                        x -> assertThat(x).isInstanceOfSatisfying(BookReturnedByUserDomainEvent.class, domainEvent -> {
                            assertThat(domainEvent.getReturnedBook()).isSameAs(book);
                            assertThat(domainEvent.getUserIdThatHadReturnedTheBook()).isSameAs(userId);
                        }));
    }
    
    @Test
    public void returnByUser_WhenBookWereNotBorrowedByAnyOne_ShouldThrowUserTriedToReturnTheBookThatWereNotBorrowedDomainException()
            throws UserTriedToReturnTheBookOfAnotherUserDomainException,
            UserTriedToReturnTheBookThatWereNotBorrowedDomainException {
        UserId userId = new UserId(0);
        Book book = Book.createNewBook(new BookId(1), "", "", 2000, Optional.empty(), Optional.empty());
        book.extractAllDomainEvents();
        
        ThrowingCallable actual = () -> book.returnByUser(userId);
        
        assertThatExceptionOfType(UserTriedToReturnTheBookThatWereNotBorrowedDomainException.class)
                .isThrownBy(actual)
                .satisfies(exception -> {
                    assertThat(exception.getBook()).isSameAs(book);
                    assertThat(exception.getUserIdThatHadTriedToReturnTheBook()).isSameAs(userId);
                });
    }
    
    @Test
    public void setGenre_WhenGenreIsNull_ShouldThrowNullPointerException() {
        Book book = Book.createNewBook(new BookId(1), "", "", 2000, Optional.empty(), Optional.empty());
        
        ThrowingCallable actual = () -> book.setGenre(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void setName_WhenNameIsNull_ShouldThrowNullPointerException() {
        Book book = Book.createNewBook(new BookId(1), "", "", 2000, Optional.empty(), Optional.empty());
        
        ThrowingCallable actual = () -> book.setName(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void setPublicationYear_WhenPublicationYearIsNegative_ShouldThrowNullPointerException() {
        Book book = Book.createNewBook(new BookId(1), "", "", 2000, Optional.empty(), Optional.empty());
        
        ThrowingCallable actual = () -> book.setPublicationYear(-1);
        
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(actual);
    }
}
