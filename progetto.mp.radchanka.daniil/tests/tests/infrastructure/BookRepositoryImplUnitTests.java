package tests.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.util.Lists;
import org.junit.Test;

import domain.book.Book;
import domain.book.BookId;
import infrastructure.book.repositories.BookRepositoryImpl;

public class BookRepositoryImplUnitTests {
    @Test
    public void createInstance_WhenEntityCloneFactoryFactoryIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new BookRepositoryImpl(new ArrayList<>(), ArrayList::new, null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenResultCollectionFactoryIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new BookRepositoryImpl(new ArrayList<>(), null, Book::createClone);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenStorageIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new BookRepositoryImpl(null, ArrayList::new, Book::createClone);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void generateNewBookId_WhenStorageIsEmpty_ShouldReturnBookIdZero() {
        BookRepositoryImpl bookRepository = new BookRepositoryImpl(
                new ArrayList<>(),
                ArrayList::new,
                Book::createClone);
        
        BookId newBookId = bookRepository.generateNewBookId();
        
        assertThat(newBookId.getId()).isEqualTo(0);
    }
    
    @Test
    public void generateNewBookId_WhenStorageIsNotEmpty_ShouldReturnNextBookId() {
        BookRepositoryImpl bookRepository = new BookRepositoryImpl(
                Lists
                        .newArrayList(
                                Book.createNewBook(new BookId(1), "", "", 0, Optional.empty(), Optional.empty()),
                                Book.createNewBook(new BookId(2), "", "", 0, Optional.empty(), Optional.empty()),
                                Book.createNewBook(new BookId(5), "", "", 0, Optional.empty(), Optional.empty())),
                ArrayList::new,
                Book::createClone);
        
        BookId newBookId = bookRepository.generateNewBookId();
        
        assertThat(newBookId.getId()).isEqualTo(6);
    }
    
}
