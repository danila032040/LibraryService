package application.queries.book.common;

import application.queries.common.sortData.SortTypeQueryData;
import base.repository.SortCriteria;
import domain.book.Book;

public class BookSortCriteriaBuilderImpl implements BookSortCriteriaBuilder {

	public static BookSortCriteriaBuilderImpl sortBy(
			BookSortByFieldQueryData sortByField,
			SortTypeQueryData sortTypeQueryData) {
		return new BookSortCriteriaBuilderImpl(
				sortByField
						.createSortCriteria(sortTypeQueryData.mapToSortType()));
	}

	private final SortCriteria<Book> sortCriteria;

	private BookSortCriteriaBuilderImpl(SortCriteria<Book> sortCriteria) {
		this.sortCriteria = sortCriteria;
	}

	@Override
	public SortCriteria<Book> build() {
		return sortCriteria;
	}

	@Override
	public BookSortCriteriaBuilder thenSortBy(
			BookSortByFieldQueryData sortByField,
			SortTypeQueryData sortTypeQueryData) {
		sortByField
				.applyThenSort(sortCriteria, sortTypeQueryData.mapToSortType());
		return this;
	}

}
