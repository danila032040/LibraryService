package application.queries.book.common;

import application.queries.common.sortData.SortTypeQueryData;
import base.repository.SortCriteria;
import domain.book.Book;

public interface BookSortCriteriaBuilder {
    public static BookSortCriteriaBuilder sortBy(BookSortByFieldQueryData sortByField, SortTypeQueryData sortType) {
        return BookSortCriteriaBuilderImpl.sortBy(sortByField, sortType);
    }
    
    public BookSortCriteriaBuilder thenSortBy(BookSortByFieldQueryData sortByField, SortTypeQueryData sortType);
    
    public SortCriteria<Book> build();
}
