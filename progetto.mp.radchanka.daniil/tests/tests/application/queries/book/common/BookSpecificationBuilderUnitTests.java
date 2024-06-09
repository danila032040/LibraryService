package tests.application.queries.book.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;

import application.queries.book.common.BookSpecificationBuilder;
import base.specification.Specification;
import domain.author.AuthorId;
import domain.book.Book;
import domain.book.BookId;
import domain.library.LibraryId;

public class BookSpecificationBuilderUnitTests {
    
    @Test
    public void andWhereNameIsLike_ShouldMatchBooksWithNameContainingSubstring() {
        Book matchingBook = createBook(0, "Harry Potter", "Test", 0, Optional.empty(), Optional.empty());
        Book nonMatchingBook = createBook(0, "The Hobbit", "Test", 0, Optional.empty(), Optional.empty());
        
        Specification<Book> specification = BookSpecificationBuilder
                .createBuilder()
                .andWhereNameIsLike("Harry")
                .build();
        
        assertThat(specification.isSatisfiedBy(matchingBook)).isTrue();
        assertThat(specification.isSatisfiedBy(nonMatchingBook)).isFalse();
    }
    
    @Test
    public void andWhereGenreIsLike_ShouldMatchBooksWithGenreContainingSubstring() {
        Book matchingBook = createBook(0, "Test", "Fantasy", 0, Optional.empty(), Optional.empty());
        Book nonMatchingBook = createBook(0, "Test", "Fiction", 0, Optional.empty(), Optional.empty());
        
        Specification<Book> specification = BookSpecificationBuilder
                .createBuilder()
                .andWhereGenreIsLike("Fantasy")
                .build();
        
        assertThat(specification.isSatisfiedBy(matchingBook)).isTrue();
        assertThat(specification.isSatisfiedBy(nonMatchingBook)).isFalse();
    }
    
    @Test
    public void andWhereAuthorIdIs_ShouldMatchBooksWithSpecificAuthorId() {
        Book matchingBook = createBook(0, "Test", "Test", 0, Optional.of(new AuthorId(1)), Optional.empty());
        Book nonMatchingBook = createBook(0, "Test", "Test", 0, Optional.of(new AuthorId(2)), Optional.empty());
        
        Specification<Book> specification = BookSpecificationBuilder
                .createBuilder()
                .andWhereAuthorIdIs(new AuthorId(1))
                .build();
        
        assertThat(specification.isSatisfiedBy(matchingBook)).isTrue();
        assertThat(specification.isSatisfiedBy(nonMatchingBook)).isFalse();
    }
    
    @Test
    public void andWhereLibraryIdIs_ShouldMatchBooksWithSpecificLibraryId() {
        Book matchingBook = createBook(0, "Test", "Test", 0, Optional.empty(), Optional.of(new LibraryId(1)));
        Book nonMatchingBook = createBook(0, "Test", "Test", 0, Optional.empty(), Optional.of(new LibraryId(2)));
        
        Specification<Book> specification = BookSpecificationBuilder
                .createBuilder()
                .andWhereLibraryIdIs(new LibraryId(1))
                .build();
        
        assertThat(specification.isSatisfiedBy(matchingBook)).isTrue();
        assertThat(specification.isSatisfiedBy(nonMatchingBook)).isFalse();
    }
    
    @Test
    public void andWherePublicationYearIsGreaterThanOrEqualTo_ShouldMatchBooksWherePublicationYearIsGreaterThanOrEqualTo() {
        Book matchingBook = createBook(0, "Test", "Test", 2001, Optional.empty(), Optional.empty());
        Book matchingBookWithEqualYear = createBook(0, "Test", "Test", 1950, Optional.empty(), Optional.empty());
        Book nonMatchingBook = createBook(0, "Test", "Test", 1937, Optional.empty(), Optional.empty());
        
        Specification<Book> specification = BookSpecificationBuilder
                .createBuilder()
                .andWherePublicationYearIsGreaterThanOrEqualTo(1950)
                .build();
        
        assertThat(specification.isSatisfiedBy(matchingBook)).isTrue();
        assertThat(specification.isSatisfiedBy(matchingBookWithEqualYear)).isTrue();
        assertThat(specification.isSatisfiedBy(nonMatchingBook)).isFalse();
    }
    
    @Test
    public void andWherePublicationYearIsLessThanOrEqualTo_ShouldMatchBooksWherePublicationYearLessThanOrEqualTo() {
        Book matchingBook = createBook(0, "Test", "Test", 1940, Optional.empty(), Optional.empty());
        Book matchingBookWithEqualYear = createBook(0, "Test", "Test", 1950, Optional.empty(), Optional.empty());
        Book nonMatchingBook = createBook(0, "Test", "Test", 1960, Optional.empty(), Optional.empty());
        
        Specification<Book> specification = BookSpecificationBuilder
                .createBuilder()
                .andWherePublicationYearIsLessThanOrEqualTo(1950)
                .build();
        
        assertThat(specification.isSatisfiedBy(matchingBook)).isTrue();
        assertThat(specification.isSatisfiedBy(matchingBookWithEqualYear)).isTrue();
        assertThat(specification.isSatisfiedBy(nonMatchingBook)).isFalse();
    }
    
    @Test
    public void combinedSpecifications_ShouldMatchBooksWithMultipleCriteria() {
        Book matchingBook = createBook(1, "Test", "Fantasy", 2001, Optional.empty(), Optional.empty());
        Book nonMatchingBook = createBook(2, "Test", "Fantasy", 1990, Optional.empty(), Optional.empty());
        
        Specification<Book> specification = BookSpecificationBuilder
                .createBuilder()
                .andWhereNameIsLike("Test")
                .andWhereGenreIsLike("Fantasy")
                .andWherePublicationYearIsGreaterThanOrEqualTo(2000)
                .build();
        
        assertThat(specification.isSatisfiedBy(matchingBook)).isTrue();
        assertThat(specification.isSatisfiedBy(nonMatchingBook)).isFalse();
    }
    
    @Test
    public void combinedSpecifications_ShouldMatchNoBooksWhenCriteriaNotMet() {
        Book nonMatchingBook1 = createBook(1, "Test", "Test", 20221, Optional.empty(), Optional.empty());
        Book nonMatchingBook2 = createBook(2, "Test", "Test", 1990, Optional.empty(), Optional.empty());
        
        Specification<Book> specification = BookSpecificationBuilder
                .createBuilder()
                .andWhereNameIsLike("Nonexistent")
                .andWhereGenreIsLike("Nonexistent")
                .andWherePublicationYearIsGreaterThanOrEqualTo(2020)
                .build();
        
        assertThat(specification.isSatisfiedBy(nonMatchingBook1)).isFalse();
        assertThat(specification.isSatisfiedBy(nonMatchingBook2)).isFalse();
    }
    
    private Book createBook(
            int id,
            String name,
            String genre,
            int publicationYear,
            Optional<AuthorId> authorId,
            Optional<LibraryId> libraryId) {
        return Book.createNewBook(new BookId(id), name, genre, publicationYear, authorId, libraryId);
    }
}
