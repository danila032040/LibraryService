package tests.application.queries.book.find.unitTests;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import application.queries.book.common.BookSortByFieldQueryData;
import application.queries.book.find.FindBooksQuery;
import application.queries.common.sortData.SortTypeQueryData;

public class FindBooksQueryUnitTests {
    @Test
    public void constructor_WhenAuthorIdIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new FindBooksQuery(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                null,
                Optional.empty(),
                0,
                0,
                BookSortByFieldQueryData.Id,
                SortTypeQueryData.Ascending,
                Optional.empty(),
                Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void constructor_WhenGenreIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new FindBooksQuery(
                Optional.empty(),
                null,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                0,
                0,
                BookSortByFieldQueryData.Id,
                SortTypeQueryData.Ascending,
                Optional.empty(),
                Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void constructor_WhenLibraryIdIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new FindBooksQuery(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                null,
                0,
                0,
                BookSortByFieldQueryData.Id,
                SortTypeQueryData.Ascending,
                Optional.empty(),
                Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void constructor_WhenNameIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new FindBooksQuery(
                null,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                0,
                0,
                BookSortByFieldQueryData.Id,
                SortTypeQueryData.Ascending,
                Optional.empty(),
                Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void constructor_WhenPublicationYearPeriodEndIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new FindBooksQuery(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                null,
                Optional.empty(),
                Optional.empty(),
                0,
                0,
                BookSortByFieldQueryData.Id,
                SortTypeQueryData.Ascending,
                Optional.empty(),
                Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void constructor_WhenPublicationYearPeriodStartIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new FindBooksQuery(
                Optional.empty(),
                Optional.empty(),
                null,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                0,
                0,
                BookSortByFieldQueryData.Id,
                SortTypeQueryData.Ascending,
                Optional.empty(),
                Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void constructor_WhenThenSortByFieldIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new FindBooksQuery(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                0,
                0,
                BookSortByFieldQueryData.Id,
                SortTypeQueryData.Ascending,
                null,
                Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void constructor_WhenThenSortTypeIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new FindBooksQuery(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                0,
                0,
                BookSortByFieldQueryData.Id,
                SortTypeQueryData.Ascending,
                Optional.empty(),
                null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
}
