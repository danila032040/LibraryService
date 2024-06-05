package tests.domain.user.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Collection;
import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.ddd.DomainEvent;
import domain.book.BookId;
import domain.common.Address;
import domain.user.User;
import domain.user.UserId;
import domain.user.events.UserChangedAddressDomainEvent;
import domain.user.events.UserChangedPhoneNumberDomainEvent;
import domain.user.events.UserCreatedDomainEvent;
import domain.user.events.UserSpecifiedPhoneNumberDomainEvent;
import domain.user.exceptions.BookWasAlreadyBorrowedByTheUserDomainEvent;
import domain.user.exceptions.BookWasNotBorrowedByTheUserDomainEvent;

public class UserUnitTests {
    @Test
    public void addBorrowedBook_ShouldAddACloneOfTheBorrowedBook() throws BookWasAlreadyBorrowedByTheUserDomainEvent {
        BookId bookId = new BookId(0);
        User user = User.createNewUser(new UserId(0), "", "", new Address(), Optional.empty());
        user.addBorrowedBook(bookId);
        
        Collection<BookId> actualBorrowedBookIds = user.getBorrowedBookIds();
        
        assertThat(actualBorrowedBookIds).satisfiesExactly(x -> assertThat(x).isNotSameAs(bookId).isEqualTo(bookId));
    }
    
    @Test
    public void addBorrowedBook_WhenAddingTheBookThatWereAlreadyAdded_ShouldThrowBookWasAlreadyBorrowedByTheUserDomainEvent()
            throws BookWasAlreadyBorrowedByTheUserDomainEvent {
        BookId bookId = new BookId(0);
        User user = User.createNewUser(new UserId(0), "", "", new Address(), Optional.empty());
        user.addBorrowedBook(bookId);
        
        ThrowingCallable actual = () -> user.addBorrowedBook(bookId);
        
        assertThatExceptionOfType(BookWasAlreadyBorrowedByTheUserDomainEvent.class)
                .isThrownBy(actual)
                .satisfies(exception -> {
                    assertThat(exception.getUser()).isSameAs(user);
                    assertThat(exception.getBookIdThatWasAlreadyBorrowed()).isSameAs(bookId);
                });
    }
    
    @Test
    public void addBorrowedBook_WhenBookIdIsNull_ShouldThrowNullPointerException() {
        User user = User.createNewUser(new UserId(0), "", "", new Address(), Optional.empty());
        
        ThrowingCallable actual = () -> user.addBorrowedBook(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void changeAddress_ShouldChangeAddressAndRegisterUserChangedAddressDomainEvent() {
        Address previousAddress = new Address();
        Address currentAddress = new Address();
        User user = User.createNewUser(new UserId(0), "", "", previousAddress, Optional.empty());
        user.extractAllDomainEvents();
        user.changeAddress(currentAddress);
        
        Address actualAddress = user.getAddress();
        Collection<DomainEvent> actualDomainEvents = user.extractAllDomainEvents();
        
        assertThat(actualAddress).isSameAs(currentAddress);
        assertThat(actualDomainEvents)
                .satisfiesExactly(
                        x -> assertThat(x).isInstanceOfSatisfying(UserChangedAddressDomainEvent.class, domainEvent -> {
                            assertThat(domainEvent.getUser()).isSameAs(user);
                            assertThat(domainEvent.getPreviousAddress()).isSameAs(previousAddress);
                            assertThat(domainEvent.getNewAddress()).isSameAs(currentAddress);
                        }));
    }
    
    @Test
    public void changeAddress_WhenAddressIsNull_ShouldThrowNullPointerException() {
        User user = User.createNewUser(new UserId(0), "", "", new Address(), Optional.empty());
        
        ThrowingCallable actual = () -> user.changeAddress(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void changePhoneNumber_WhenPhoneNumbereWasSpecifiedPreviously_ShouldChangePhoneNumberAndRegisterUseChangedPhoneNumberDomainEvent() {
        String previousPhoneNumber = "";
        String currentPhoneNumber = "";
        User user = User.createNewUser(new UserId(0), "", "", new Address(), Optional.of(previousPhoneNumber));
        user.extractAllDomainEvents();
        user.changePhoneNumber(currentPhoneNumber);
        
        Optional<String> actualPhoneNumber = user.getPhoneNumber();
        Collection<DomainEvent> actualDomainEvents = user.extractAllDomainEvents();
        
        assertThat(actualPhoneNumber).hasValue(currentPhoneNumber);
        assertThat(actualDomainEvents)
                .satisfiesExactly(
                        x -> assertThat(x)
                                .isInstanceOfSatisfying(UserChangedPhoneNumberDomainEvent.class, domainEvent -> {
                                    assertThat(domainEvent.getUser()).isSameAs(user);
                                    assertThat(domainEvent.getPreviousPhoneNumber()).isSameAs(previousPhoneNumber);
                                    assertThat(domainEvent.getCurrentPhoneNumber()).isSameAs(currentPhoneNumber);
                                }));
    }
    
    @Test
    public void changePhoneNumber_WhenPhoneNumberIsNull_ShouldThrowNullPointerException() {
        User user = User.createNewUser(new UserId(0), "", "", new Address(), Optional.empty());
        
        ThrowingCallable actual = () -> user.changePhoneNumber(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void changePhoneNumber_WhenPhoneNumberWasNotSpecifiedPreviously_ShouldSetPhoneNumberAndRegisterUserSpecifiedPhoneNumberDomainEvent() {
        String phoneNumber = "";
        User user = User.createNewUser(new UserId(0), "", "", new Address(), Optional.empty());
        user.extractAllDomainEvents();
        user.changePhoneNumber(phoneNumber);
        
        Optional<String> actualPhoneNumber = user.getPhoneNumber();
        Collection<DomainEvent> actualDomainEvents = user.extractAllDomainEvents();
        
        assertThat(actualPhoneNumber).hasValue(phoneNumber);
        assertThat(actualDomainEvents)
                .satisfiesExactly(
                        x -> assertThat(x)
                                .isInstanceOfSatisfying(UserSpecifiedPhoneNumberDomainEvent.class, domainEvent -> {
                                    assertThat(domainEvent.getUser()).isSameAs(user);
                                    assertThat(domainEvent.getSpecifiedPhoneNumber()).isSameAs(phoneNumber);
                                }));
    }
    
    @Test
    public void createClone_ShouldReturnEqualButNotTheSameInstance() {
        User user = User.createNewUser(new UserId(0), "", "", new Address(), Optional.empty());
        
        User actual = user.createClone();
        
        assertThat(actual).isNotSameAs(user).isEqualTo(user);
    }
    
    @Test
    public void createNewUser_ShouldRegisterUserCreatedDomainEvent() {
        User user = User.createNewUser(new UserId(0), "", "", new Address(), Optional.empty());
        
        Collection<DomainEvent> actual = user.extractAllDomainEvents();
        
        assertThat(actual)
                .satisfiesExactly(
                        x -> assertThat(x).isInstanceOfSatisfying(UserCreatedDomainEvent.class, domainEvent -> {
                            assertThat(domainEvent.getUser()).isSameAs(user);
                        }));
    }
    
    @Test
    public void createNewUser_WhenAddressIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> User.createNewUser(new UserId(0), "", "", null, Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createNewUser_WhenNameIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> User.createNewUser(new UserId(0), null, "", new Address(), Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createNewUser_WhenOptionalPhoneNumberIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> User.createNewUser(new UserId(0), "", "", new Address(), null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createNewUser_WhenSurnameIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> User.createNewUser(new UserId(0), "", null, new Address(), Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void returnBorrowedBook_ShouldAddACloneOfTheBorrowedBook()
            throws BookWasNotBorrowedByTheUserDomainEvent,
            BookWasAlreadyBorrowedByTheUserDomainEvent {
        BookId bookId = new BookId(0);
        User user = User.createNewUser(new UserId(0), "", "", new Address(), Optional.empty());
        user.addBorrowedBook(bookId);
        user.returnBorrowedBook(bookId);
        
        Collection<BookId> actualBorrowedBookIds = user.getBorrowedBookIds();
        
        assertThat(actualBorrowedBookIds).isEmpty();
    }
    
    @Test
    public void returnBorrowedBook_ShouldRemoveBorrowedBookCorrectly()
            throws BookWasAlreadyBorrowedByTheUserDomainEvent,
            BookWasNotBorrowedByTheUserDomainEvent {
        BookId bookId = new BookId(0);
        User user = User.createNewUser(new UserId(0), "", "", new Address(), Optional.empty());
        user.addBorrowedBook(bookId);
        user.returnBorrowedBook(bookId);
        
        Collection<BookId> actualBorrowedBookIds = user.getBorrowedBookIds();
        
        assertThat(actualBorrowedBookIds).isEmpty();
    }
    
    @Test
    public void returnBorrowedBook_WhenBookIdIsNull_ShouldThrowNullPointerException() {
        User user = User.createNewUser(new UserId(0), "", "", new Address(), Optional.empty());
        
        ThrowingCallable actual = () -> user.returnBorrowedBook(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void returnBorrowedBook_WhenBookWereNotBorrowedPreviously_ShouldBookWasNotBorrowedByTheUserDomainEvent() {
        BookId bookId = new BookId(0);
        User user = User.createNewUser(new UserId(0), "", "", new Address(), Optional.empty());
        
        ThrowingCallable actual = () -> user.returnBorrowedBook(bookId);
        
        assertThatExceptionOfType(BookWasNotBorrowedByTheUserDomainEvent.class)
                .isThrownBy(actual)
                .satisfies(exception -> {
                    assertThat(exception.getUser()).isSameAs(user);
                    assertThat(exception.getBookIdThatWasNotBorrowed()).isSameAs(bookId);
                });
    }
}
