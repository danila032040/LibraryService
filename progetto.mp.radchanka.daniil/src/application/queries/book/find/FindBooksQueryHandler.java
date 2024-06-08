package application.queries.book.find;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import application.queries.book.common.BookSortByFieldQueryData;
import application.queries.book.common.BookSortCriteriaBuilder;
import application.queries.common.sortData.SortTypeQueryData;
import base.mediator.request.RequestHandler;
import base.repository.Pagination;
import base.repository.SortCriteria;
import base.repository.SortType;
import base.result.ErrorOr;
import base.specification.Specification;
import base.specification.composable.CompositeSpecification;
import base.utils.Mapper;
import base.utils.Pair;
import base.utils.SpecificationUtils;
import domain.author.AuthorId;
import domain.book.Book;
import domain.book.BookRepository;
import domain.library.LibraryId;

public class FindBooksQueryHandler implements RequestHandler<FindBooksQuery, ErrorOr<Collection<Book>>> {
    private final Mapper<SortTypeQueryData, SortType> sortTypeQueryDataToSortTypeMapper;
    private final BookRepository bookRepository;
    
    public FindBooksQueryHandler(
            Mapper<SortTypeQueryData, SortType> sortTypeQueryDataToSortTypeMapper,
            BookRepository bookRepository) {
        this.sortTypeQueryDataToSortTypeMapper = sortTypeQueryDataToSortTypeMapper;
        this.bookRepository = bookRepository;
    }
    
    @Override
    public ErrorOr<Collection<Book>> handle(FindBooksQuery request) {
        Specification<Book> specification = buildBookSpecificationFromRequest(request);
        SortCriteria<Book> sortCriteria = buildBookSortCriteriaFromRequest(request);
        Pagination pagination = Pagination.of(request.getPageIndex(), request.getPageSize());
        
        return ErrorOr.fromResult(bookRepository.get(specification, sortCriteria, pagination));
    }
    
    private Specification<Book> buildBookSpecificationFromRequest(FindBooksQuery request) {
        CompositeSpecification<Book> specification = book -> true;
        specification = request
                .getPublicationYearPeriodStart()
                .map(
                        publicationYearStart -> SpecificationUtils
                                .<Book, Integer>generateFieldSpecification(
                                        publicationYearStart,
                                        Book::getPublicationYear,
                                        (
                                                searchPublicationYearStart,
                                                bookPublicationYear) -> bookPublicationYear >= searchPublicationYearStart))
                .map(specification::and)
                .orElse(specification);
        specification = request
                .getPublicationYearPeriodEnd()
                .map(
                        publicationYearEnd -> SpecificationUtils
                                .<Book, Integer>generateFieldSpecification(
                                        publicationYearEnd,
                                        Book::getPublicationYear,
                                        (
                                                searchPublicationYearEnd,
                                                bookPublicationYear) -> bookPublicationYear <= searchPublicationYearEnd))
                .map(specification::and)
                .orElse(specification);
        specification = request
                .getName()
                .map(
                        name -> SpecificationUtils
                                .<Book, String>generateFieldSpecification(
                                        name,
                                        Book::getName,
                                        (searchName, bookName) -> bookName.contains(searchName)))
                .map(specification::and)
                .orElse(specification);
        specification = request
                .getGenre()
                .map(
                        genre -> SpecificationUtils
                                .<Book, String>generateFieldSpecification(
                                        genre,
                                        Book::getGenre,
                                        (searchGenre, bookGenre) -> bookGenre.contains(searchGenre)))
                .map(specification::and)
                .orElse(specification);
        specification = request
                .getAuthorId()
                .map(
                        authorId -> SpecificationUtils
                                .<Book, AuthorId>generateOptionalFieldSpecification(
                                        authorId,
                                        Book::getAuthorId,
                                        Objects::equals))
                .map(specification::and)
                .orElse(specification);
        specification = request
                .getLibraryId()
                .map(
                        libraryId -> SpecificationUtils
                                .<Book, LibraryId>generateOptionalFieldSpecification(
                                        libraryId,
                                        Book::getLibraryId,
                                        Objects::equals))
                .map(specification::and)
                .orElse(specification);
        return specification;
    }
    
    private SortCriteria<Book> buildBookSortCriteriaFromRequest(FindBooksQuery request) {
        BookSortByFieldQueryData sortByField = request.getSortByField();
        SortType sortType = this.sortTypeQueryDataToSortTypeMapper.map(request.getSortType());
        BookSortCriteriaBuilder builder = BookSortCriteriaBuilder.sortBy(sortByField, sortType);
        
        Optional<Pair<BookSortByFieldQueryData, SortType>> thenSortPair = request
                .getThenSortByField()
                .flatMap(
                        thenSortByField -> request
                                .getThenSortType()
                                .map(sortTypeQueryDataToSortTypeMapper::map)
                                .map(thenSortType -> Pair.of(thenSortByField, thenSortType)));
        thenSortPair.ifPresent(x -> builder.thenSortBy(x.getLeft(), x.getRight()));
        return builder.build();
    }
    
}
