package application.queries.book.common;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

import application.queries.common.sortData.SortTypeQueryData;
import base.repository.SortCriteria;
import base.repository.SortType;
import base.utils.OptionalComparator;
import domain.book.Book;
import domain.book.BookId;

public class BookSortCriteriaBuilderImpl implements BookSortCriteriaBuilder {

	public static BookSortCriteriaBuilderImpl sortBy(
			BookSortByFieldQueryData sortByField,
			SortTypeQueryData sortTypeQueryData) {
		SortType sortType = mapToSortType(sortTypeQueryData);

		switch (sortByField) {
			case authorId :
				return new BookSortCriteriaBuilderImpl(
						SortCriteria
								.<Book>sortUsingComparator(
										createComparatorForOptionalField(
												book -> book.getLibraryId(),
												sortType)));
			case genre :
				return new BookSortCriteriaBuilderImpl(
						SortCriteria
								.<Book, String>sortBy(
										book -> book.getGenre(),
										sortType));
			case id :
				return new BookSortCriteriaBuilderImpl(
						SortCriteria
								.<Book, BookId>sortBy(
										book -> book.getId(),
										sortType));
			case libraryId :
				return new BookSortCriteriaBuilderImpl(
						SortCriteria
								.<Book>sortUsingComparator(
										createComparatorForOptionalField(
												book -> book.getAuthorId(),
												sortType)));
			case name :
				return new BookSortCriteriaBuilderImpl(
						SortCriteria
								.<Book, String>sortBy(
										book -> book.getName(),
										sortType));
			case publicationYear :
				return new BookSortCriteriaBuilderImpl(
						SortCriteria
								.<Book, Integer>sortBy(
										book -> book.getPublicationYear(),
										sortType));
			default :
				throw new IllegalArgumentException(
						"Unknown enum: " + sortByField);
		}
	}

	private static <U extends Comparable<U>> Comparator<Book> createComparatorForOptionalField(
			Function<Book, Optional<U>> keyExtractor,
			SortType sortType) {
		Comparator<Book> comparator = Comparator
				.<Book, Optional<U>>comparing(
						keyExtractor,
						new OptionalComparator<U>());
		return sortType == SortType.Descending
				? comparator.reversed()
				: comparator;
	}

	private static SortType mapToSortType(SortTypeQueryData from) {
		switch (from) {
			case Ascending :
				return SortType.Ascending;
			case Descending :
				return SortType.Descending;
			default :
				throw new IllegalArgumentException("Unknown enum: " + from);
		}
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
		SortType sortType = mapToSortType(sortTypeQueryData);

		switch (sortByField) {
			case authorId :
				return new BookSortCriteriaBuilderImpl(
						sortCriteria
								.thenSortUsingComparator(
										createComparatorForOptionalField(
												book -> book.getLibraryId(),
												sortType)));
			case genre :
				return new BookSortCriteriaBuilderImpl(
						sortCriteria
								.<String>thenSortBy(
										book -> book.getGenre(),
										sortType));
			case id :
				return new BookSortCriteriaBuilderImpl(
						sortCriteria
								.<BookId>thenSortBy(
										book -> book.getId(),
										sortType));
			case libraryId :
				return new BookSortCriteriaBuilderImpl(
						sortCriteria
								.thenSortUsingComparator(
										createComparatorForOptionalField(
												book -> book.getAuthorId(),
												sortType)));
			case name :
				return new BookSortCriteriaBuilderImpl(
						sortCriteria
								.<String>thenSortBy(
										book -> book.getName(),
										sortType));
			case publicationYear :
				return new BookSortCriteriaBuilderImpl(
						sortCriteria
								.<Integer>thenSortBy(
										book -> book.getPublicationYear(),
										sortType));
			default :
				throw new IllegalArgumentException(
						"Unknown enum: " + sortByField);
		}
	}

}
