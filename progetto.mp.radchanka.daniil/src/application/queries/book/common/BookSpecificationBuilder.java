package application.queries.book.common;

import java.util.Objects;

import base.specification.Specification;
import base.specification.composable.CompositeSpecification;
import base.utils.SpecificationUtils;
import domain.author.AuthorId;
import domain.book.Book;
import domain.library.LibraryId;

public class BookSpecificationBuilder {
    private CompositeSpecification<Book> specification;
    
    private BookSpecificationBuilder() {
        specification = book -> true;
    }
    
    public BookSpecificationBuilder andWhereNameIsLike(String name) {
        specification = specification
                .and(
                        SpecificationUtils
                                .<Book, String>generateFieldSpecification(
                                        Book::getName,
                                        (bookName) -> bookName.contains(name)));
        return this;
    }
    
    public BookSpecificationBuilder andWhereGenreIsLike(String genre) {
        specification = specification
                .and(
                        SpecificationUtils
                                .<Book, String>generateFieldSpecification(
                                        Book::getGenre,
                                        (bookGenre) -> bookGenre.contains(genre)));
        return this;
    }
    
    public BookSpecificationBuilder andWhereAuthorIdIs(AuthorId authorId) {
        specification = specification
                .and(
                        SpecificationUtils
                                .<Book, AuthorId>generateOptionalFieldSpecification(
                                        Book::getAuthorId,
                                        (bookAuthorId) -> Objects.equals(authorId, bookAuthorId)));
        return this;
    }
    
    public BookSpecificationBuilder andWhereLibraryIdIs(LibraryId libraryId) {
        specification = specification
                .and(
                        SpecificationUtils
                                .<Book, LibraryId>generateOptionalFieldSpecification(
                                        Book::getLibraryId,
                                        (bookAuthorId) -> Objects.equals(libraryId, bookAuthorId)));
        return this;
    }
    
    public BookSpecificationBuilder andWherePublicationYearIsGreaterThanOrEqualTo(int publicationYear) {
        specification = specification
                .and(
                        SpecificationUtils
                                .<Book, Integer>generateFieldSpecification(
                                        Book::getPublicationYear,
                                        (bookPublicationYear) -> bookPublicationYear >= publicationYear));
        return this;
    }
    
    public BookSpecificationBuilder andWherePublicationYearIsLessThanOrEqualTo(int publicationYear) {
        specification = specification
                .and(
                        SpecificationUtils
                                .<Book, Integer>generateFieldSpecification(
                                        Book::getPublicationYear,
                                        (bookPublicationYear) -> bookPublicationYear <= publicationYear));
        return this;
    }
    
    public static BookSpecificationBuilder createBuilder() {
        return new BookSpecificationBuilder();
    }
    
    public Specification<Book> build() {
        return specification;
    }
}
