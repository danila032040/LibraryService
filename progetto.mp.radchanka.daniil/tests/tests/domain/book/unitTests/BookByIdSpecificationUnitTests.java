package tests.domain.book.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import domain.book.Book;
import domain.book.BookId;
import domain.book.specifications.BookByIdSpecification;

public class BookByIdSpecificationUnitTests {
    @Test
    public void constructor_WhenBookIdIsNull_ShouldThrowException() {
        ThrowingCallable actual = () -> new BookByIdSpecification(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void isSatisfiedBy_WhenBookIsNull_ShouldThrowException() {
        BookId bookId = new BookId(1);
        BookByIdSpecification specification = new BookByIdSpecification(bookId);
        
        ThrowingCallable actual = () -> specification.isSatisfiedBy(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void isSatisfiedBy_WhenBookIdMatches_ShouldReturnTrue() {
        BookId bookId = new BookId(1);
        BookByIdSpecification specification = new BookByIdSpecification(bookId);
        Book book = Book.createNewBook(bookId, "", "", 0, Optional.empty(), Optional.empty());
        
        boolean result = specification.isSatisfiedBy(book);
        
        assertThat(result).isTrue();
    }
    
    @Test
    public void isSatisfiedBy_WhenBookIdDoesNotMatch_ShouldReturnFalse() {
        BookId bookId1 = new BookId(1);
        BookId bookId2 = new BookId(2);
        BookByIdSpecification specification = new BookByIdSpecification(bookId1);
        Book book = Book.createNewBook(bookId2, "", "", 0, Optional.empty(), Optional.empty());
        
        boolean result = specification.isSatisfiedBy(book);
        
        assertThat(result).isFalse();
    }
}
