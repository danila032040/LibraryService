package application.queries.book.find;

import java.util.List;
import java.util.Optional;

import application.queries.book.common.BookSortByFieldQueryData;
import application.queries.book.common.BookSortCriteriaBuilder;
import application.queries.book.common.BookSpecificationBuilder;
import application.queries.common.sortData.SortTypeQueryData;
import base.mediator.request.RequestHandler;
import base.repository.Pagination;
import base.repository.SortCriteria;
import base.repository.SortType;
import base.result.ErrorOr;
import base.specification.Specification;
import base.utils.Mapper;
import base.utils.Pair;
import domain.book.Book;
import domain.book.BookRepository;

public class FindBooksQueryHandler implements RequestHandler<FindBooksQuery, ErrorOr<List<Book>>> {
    private final Mapper<SortTypeQueryData, SortType> sortTypeQueryDataToSortTypeMapper;
    private final BookRepository bookRepository;
    
    public FindBooksQueryHandler(
            Mapper<SortTypeQueryData, SortType> sortTypeQueryDataToSortTypeMapper,
            BookRepository bookRepository) {
        this.sortTypeQueryDataToSortTypeMapper = sortTypeQueryDataToSortTypeMapper;
        this.bookRepository = bookRepository;
    }
    
    @Override
    public ErrorOr<List<Book>> handle(FindBooksQuery request) {
        Specification<Book> specification = buildBookSpecificationFromRequest(request);
        SortCriteria<Book> sortCriteria = buildBookSortCriteriaFromRequest(request);
        Pagination pagination = Pagination.of(request.getPageIndex(), request.getPageSize());
        
        return ErrorOr.fromResult(bookRepository.get(specification, sortCriteria, pagination));
    }
    
    private Specification<Book> buildBookSpecificationFromRequest(FindBooksQuery request) {
        BookSpecificationBuilder builder = BookSpecificationBuilder.createBuilder();
        
        request.getName().ifPresent(builder::andWhereNameIsLike);
        request.getGenre().ifPresent(builder::andWhereGenreIsLike);
        request.getAuthorId().ifPresent(builder::andWhereAuthorIdIs);
        request.getLibraryId().ifPresent(builder::andWhereLibraryIdIs);
        request.getPublicationYearPeriodStart().ifPresent(builder::andWherePublicationYearIsGreaterThanOrEqualTo);
        request.getPublicationYearPeriodEnd().ifPresent(builder::andWherePublicationYearIsLessThanOrEqualTo);
        
        return builder.build();
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
